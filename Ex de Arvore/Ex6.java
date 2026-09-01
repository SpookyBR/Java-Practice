public class Ex6 {
    public static No buscaRecursiva(No p, int chave) {
        if (p == null) return null;
        if (chave == p.chave) return p;
        if (chave < p.chave) return buscaRecursiva(p.esq, chave);
        return buscaRecursiva(p.dir, chave);
    }


    public static No insereRecursivo(No p, int valor) {

        if (p == null) {
            return new No(valor);
        }
        
        if (valor < p.chave) {
            p.esq = insereRecursivo(p.esq, valor);
        } else if (valor > p.chave) {
            p.dir = insereRecursivo(p.dir, valor);
        } else {
            System.out.println("O elemento " + valor + " já existe na árvore!");
        }
        return p;
    }
}