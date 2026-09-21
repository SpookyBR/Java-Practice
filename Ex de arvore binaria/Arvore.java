public class Arvore {

    private No raiz;

    public Arvore() {
        this.raiz = null;
    }

    // --- MÉTODO PEDIDO NO EXERCÍCIO ---

    public Integer buscarMaiorMenorOuIgual(int n) {
        return buscarMaiorMenorOuIgual(this.raiz, n);
    }

    private Integer buscarMaiorMenorOuIgual(No no, int n) {
        Integer melhorCandidato = null;
        
        while (no != null) {
            if (no.valor == n) {
                return no.valor;
            } else if (no.valor > n) {
                // Estourou o limite, busca menores à esquerda
                no = no.esquerda;
            } else {
                // É menor! Salva como candidato e tenta achar um maior à direita
                melhorCandidato = no.valor;
                no = no.direita;
            }
        }
        
        return melhorCandidato;
    }

    // --- MÉTODOS AUXILIARES PARA INSERÇÃO E TESTES ---

    public void inserir(int valor) {
        this.raiz = inserirRecursivo(this.raiz, valor);
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

    // --- MAIN DE TESTE ---
    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        /*
                  10
                 /  \
                5    15
               / \   / \
              2   7 12  20
        */
        arvore.inserir(10);
        arvore.inserir(5);
        arvore.inserir(15);
        arvore.inserir(2);
        arvore.inserir(7);
        arvore.inserir(12);
        arvore.inserir(20);

        System.out.println("Busca por 13: " + arvore.buscarMaiorMenorOuIgual(13)); // Retorna 12
        System.out.println("Busca por 7: "  + arvore.buscarMaiorMenorOuIgual(7));  // Retorna 7
        System.out.println("Busca por 1: "  + arvore.buscarMaiorMenorOuIgual(1));  // Retorna null
        System.out.println("Busca por 25: " + arvore.buscarMaiorMenorOuIgual(25)); // Retorna 20
    }
}