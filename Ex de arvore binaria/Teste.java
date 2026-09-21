public class Teste {
    private No raiz;

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }
        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }
        return atual;
    }

    // 1. REMOÇÃO RECURSIVA

    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);
    }

    private No removerRecursivo(No atual, int valor) {
        if (atual == null) return null;

        if (valor < atual.valor) {
            atual.esquerda = removerRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = removerRecursivo(atual.direita, valor);
        } else {
            // Nó com apenas um filho ou nenhum
            if (atual.esquerda == null) return atual.direita;
            else if (atual.direita == null) return atual.esquerda;

            // Nó com dois filhos
            atual.valor = menorValor(atual.direita);
            atual.direita = removerRecursivo(atual.direita, atual.valor);
        }
        return atual;
    }

    private int menorValor(No raiz) {
        int min = raiz.valor;
        while (raiz.esquerda != null) {
            min = raiz.esquerda.valor;
            raiz = raiz.esquerda;
        }
        return min;
    }

    // 2. REMOÇÃO ITERATIVA
    public void removerIterativo(int valor) {
        No atual = raiz;
        No pai = null;
        boolean filhoEsquerda = false;

        // Busca o nó e seu pai
        while (atual != null && atual.valor != valor) {
            pai = atual;
            if (valor < atual.valor) {
                atual = atual.esquerda;
                filhoEsquerda = true;
            } else {
                atual = atual.direita;
                filhoEsquerda = false;
            }
        }

        if (atual == null) return; // Valor não encontrado

        //Nó folha
        if (atual.esquerda == null && atual.direita == null) {
            if (atual == raiz) raiz = null;
            else if (filhoEsquerda) pai.esquerda = null;
            else pai.direita = null;
        }
        // filho à direita
        else if (atual.esquerda == null) {
            if (atual == raiz) raiz = atual.direita;
            else if (filhoEsquerda) pai.esquerda = atual.direita;
            else pai.direita = atual.direita;
        }
        //filho à esquerda
        else if (atual.direita == null) {
            if (atual == raiz) raiz = atual.esquerda;
            else if (filhoEsquerda) pai.esquerda = atual.esquerda;
            else pai.direita = atual.esquerda;
        }

        else {
            No sucessorPai = atual;
            No sucessor = atual.direita;

            while (sucessor.esquerda != null) {
                sucessorPai = sucessor;
                sucessor = sucessor.esquerda;
            }

            atual.valor = sucessor.valor;

            if (sucessorPai != atual) {
                sucessorPai.esquerda = sucessor.direita;
            } else {
                sucessorPai.direita = sucessor.direita;
            }
        }
    }

    // 3. Diagrama de Barras
    public void desenharDiagramaBarras() {
        desenharRecursivo(raiz, 0);
    }

    private void desenharRecursivo(No atual, int nivel) {
        if (atual == null) return;

        desenharRecursivo(atual.direita, nivel + 1);

        for (int i = 0; i < nivel; i++) {
            System.out.print("---- ");
        }
        System.out.println(atual.valor);

        desenharRecursivo(atual.esquerda, nivel + 1);
    }

    public static void main(String[] args) {
        Teste arvore = new Teste();
        
        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);

        System.out.println("Árvore original:");
        arvore.desenharDiagramaBarras();

        System.out.println("\nRemovendo o 20 (Recursivo):");
        arvore.remover(20);
        arvore.desenharDiagramaBarras();

        System.out.println("\nRemovendo o 30 (Iterativo):");
        arvore.removerIterativo(30);
        arvore.desenharDiagramaBarras();
    }
}