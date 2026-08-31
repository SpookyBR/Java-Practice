import java.util.Stack;

public class Ex4 {
    public static void preOrdemRecursivo(No p) {
        if (p == null) return;
        
        System.out.print(p.chave + " ");
        preOrdemRecursivo(p.esq); 
        preOrdemRecursivo(p.dir);
    }
    
    public static void inOrdemRecursivo(No p) {
        if (p == null) return;
        
        inOrdemRecursivo(p.esq);
        System.out.print(p.chave + " ");
        inOrdemRecursivo(p.dir);
    }
    
    public static void posOrdemRecursivo(No p) {
        if (p == null) return;
        
        posOrdemRecursivo(p.esq);
        posOrdemRecursivo(p.dir);
        System.out.print(p.chave + " ");
    }

    public static void preOrdemIterativo(No p) {
        if (p == null) return;
    
        Stack<No> pilha = new Stack<>();
        pilha.push(p);
    
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.print(atual.chave + " ");
    
            if (atual.dir != null) {
                pilha.push(atual.dir);
            }

            if (atual.esq != null) {
                pilha.push(atual.esq);
            }
        }
        System.out.println();
    } 
}