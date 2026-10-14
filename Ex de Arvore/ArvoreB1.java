class Pagina {
    int[] s;
    Pagina[] p;
    int n;  
    boolean folha; 
    int d;

    public Pagina(int d, boolean folha) {
        this.d = d;
        this.folha = folha;
        this.s = new int[d];
        this.p = new Pagina[d + 1];
        this.n = 0;
    }

    public int buscaBinariaPosicao(int chaveAlvo) {
        int inicio = 0;
        int fim = this.n - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (this.s[meio] == chaveAlvo) {
                return meio;
            } else if (this.s[meio] < chaveAlvo) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        return inicio;
    }
}

public class ArvoreB {
    Pagina raiz;
    int d;

    public ArvoreB(int d) {
        this.raiz = null;
        this.d = d;
    }

    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new Pagina(d, true);
            raiz.s[0] = chave;
            raiz.n = 1;
            return;
        }

        if (raiz.n == d) {
            Pagina novaRaiz = new Pagina(d, false);
            novaRaiz.p[0] = raiz;
            
            dividirPagina(novaRaiz, 0, raiz);

            int i = 0;
            if (novaRaiz.s[0] < chave) {
                i = 1;
            }
            
            this.raiz = novaRaiz;
            inserirInOrdem(this.raiz.p[i], chave);
        } else {
            inserirInOrdem(raiz, chave);
        }
    }

    private void inserirInOrdem(Pagina pag, int chave) {
        int i = pag.buscaBinariaPosicao(chave);

        if (pag.folha) {
            for (int j = pag.n - 1; j >= i; j--) {
                pag.s[j + 1] = pag.s[j];
            }
            pag.s[i] = chave;
            pag.n++;
        } 
        else {
            if (pag.p[i].n == d) {
                dividirPagina(pag, i, pag.p[i]);
                
                if (pag.s[i] < chave) {
                    i++;
                }
            }

            inserirInOrdem(pag.p[i], chave);
        }
    }

    // Método de quebra (split) de uma página cheia
    private void dividirPagina(Pagina pai, int indiceAbertura, Pagina paginaCheia) {
        // Encontra o elemento do meio
        int meio = d / 2;
        
        Pagina novaPagina = new Pagina(d, paginaCheia.folha);
        // A nova página recebe a metade final das chaves
        novaPagina.n = d - 1 - meio; 

        // Transfere as chaves para a nova página
        for (int j = 0; j < novaPagina.n; j++) {
            novaPagina.s[j] = paginaCheia.s[j + meio + 1];
        }

        // Se não for folha, transfere os ponteiros 'p[]' também
        if (!paginaCheia.folha) {
            for (int j = 0; j <= novaPagina.n; j++) {
                novaPagina.p[j] = paginaCheia.p[j + meio + 1];
            }
        }

        // Atualiza a quantidade de chaves da página original
        paginaCheia.n = meio;

        // Abre espaço no vetor de ponteiros da página PAI para a nova página
        for (int j = pai.n; j >= indiceAbertura + 1; j--) {
            pai.p[j + 1] = pai.p[j];
        }
        pai.p[indiceAbertura + 1] = novaPagina;

        // Abre espaço no vetor 's' da página PAI para a chave mediana que vai subir
        for (int j = pai.n - 1; j >= indiceAbertura; j--) {
            pai.s[j + 1] = pai.s[j];
        }
        pai.s[indiceAbertura] = paginaCheia.s[meio];
        pai.n++;
    }
}
