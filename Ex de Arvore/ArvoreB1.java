class Pagina {
    int[] s;       // Array de chaves
    Pagina[] p;    // Array de ponteiros para as páginas filhas
    int n;         // Quantidade atual de chaves na página
    boolean folha; 
    int d;         // Ordem (número máximo de chaves)

    public Pagina(int d, boolean folha) {
        this.d = d;
        this.folha = folha;
        this.s = new int[d];       // Capacidade máxima de d chaves
        this.p = new Pagina[d + 1]; // Capacidade máxima de d+1 ponteiros
        this.n = 0;
    }

    // Busca binária para achar o índice de inserção/descida
    public int buscaBinariaPosicao(int chaveAlvo) {
        int inicio = 0;
        int fim = this.n - 1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;
            if (this.s[meio] == chaveAlvo) {
                return meio; // Chave já existe (se a árvore não permitir duplicatas, você trataria aqui)
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

    // Ponto de entrada da inserção
    public void inserir(int chave) {
        if (raiz == null) {
            raiz = new Pagina(d, true);
            raiz.s[0] = chave;
            raiz.n = 1;
            return;
        }

        // Se a página raiz estiver cheia, ela sofre split e a árvore cresce para cima
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

    // Método que garante a inserção na posição ordenada dentro do vetor s[]
    private void inserirInOrdem(Pagina pag, int chave) {
        int i = pag.buscaBinariaPosicao(chave);

        // Se chegou na folha, faz a inserção mantendo a ordem do vetor s[]
        if (pag.folha) {
            // Desloca as chaves maiores para a direita
            for (int j = pag.n - 1; j >= i; j--) {
                pag.s[j + 1] = pag.s[j];
            }
            // Insere a nova chave
            pag.s[i] = chave;
            pag.n++;
        } 
        // Se for página interna, verifica se a próxima página destino está cheia
        else {
            if (pag.p[i].n == d) {
                dividirPagina(pag, i, pag.p[i]);
                
                // Recalcula o caminho após a chave do meio ter subido
                if (pag.s[i] < chave) {
                    i++;
                }
            }
            // Desce recursivamente para a página filha
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
