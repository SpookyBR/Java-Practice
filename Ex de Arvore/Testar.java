import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Testar {

    // Ex 1: Contar Total de Nós
    public static int contarTotalNos(No p) {
        if (p == null) return 0;
        return 1 + contarTotalNos(p.esq) + contarTotalNos(p.dir);
    }

    // Ex 2: Contar Folhas
    public static int contarFolhas(No p) {
        if (p == null) return 0;
        if (p.esq == null && p.dir == null) return 1;
        return contarFolhas(p.esq) + contarFolhas(p.dir);
    }

    // Ex 3: Contar Nós Internos
    public static int contarInternos(No p) {
        if (p == null) return 0;
        if (p.esq == null && p.dir == null) return 0;
        return 1 + contarInternos(p.esq) + contarInternos(p.dir);
    }

    // Ex 4: Pré-Ordem Iterativo
    public static void preOrdemIterativo(No p) {
        if (p == null) return;
        Stack<No> pilha = new Stack<>();
        pilha.push(p);
        while (!pilha.isEmpty()) {
            No atual = pilha.pop();
            System.out.print(atual.chave + " ");
            if (atual.dir != null) pilha.push(atual.dir);
            if (atual.esq != null) pilha.push(atual.esq);
        }
        System.out.println();
    }

    // Ex 5: Percurso por Nível
    public static void percursoPorNivel(No p) {
        if (p == null) return;
        Queue<No> fila = new LinkedList<>();
        fila.add(p);
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.chave + " ");
            if (atual.esq != null) fila.add(atual.esq);
            if (atual.dir != null) fila.add(atual.dir);
        }
        System.out.println();
    }

    // Ex 6: Busca e Inserção Recursiva
    public static No buscaRecursiva(No p, int chave) {
        if (p == null) return null;
        if (chave == p.chave) return p;
        if (chave < p.chave) return buscaRecursiva(p.esq, chave);
        return buscaRecursiva(p.dir, chave);
    }

    public static No insereRecursivo(No p, int valor) {
        if (p == null) return new No(valor);
        if (valor < p.chave) {
            p.esq = insereRecursivo(p.esq, valor);
        } else if (valor > p.chave) {
            p.dir = insereRecursivo(p.dir, valor);
        }
        return p;
    }

    // Ex 7: Calcular Altura
    public static int calcularAltura(No p) {
        if (p == null) return 0;
        return 1 + Math.max(calcularAltura(p.esq), calcularAltura(p.dir));
    }

    // Método principal que roda tudo
    public static void main(String[] args) {
        // Montando a árvore de teste dos slides (raiz 8)
        No raiz = new No(8);
        raiz.esq = new No(3);
        raiz.dir = new No(10);
        raiz.esq.esq = new No(1);
        raiz.esq.dir = new No(6);
        raiz.esq.dir.esq = new No(4);
        raiz.esq.dir.dir = new No(7);
        raiz.dir.dir = new No(14);

        System.out.println("Ex 1) Total de nos na arvore: " + contarTotalNos(raiz)); 
        System.out.println("Ex 2) Total de folhas: " + contarFolhas(raiz)); 
        System.out.println("Ex 3) Total de nos internos: " + contarInternos(raiz)); 
        
        System.out.print("Ex 4) Percurso Pre-Ordem (Iterativo): ");
        preOrdemIterativo(raiz); 

        System.out.print("Ex 5) Percurso por Nivel (Largura): ");
        percursoPorNivel(raiz); 

        System.out.println("\nEx 6) Testando Busca e Insercao Recursiva:");
        No buscado = buscaRecursiva(raiz, 6);
        System.out.println("   -> Buscando no 6: " + (buscado != null ? "Encontrado!" : "Nao encontrado"));
        System.out.println("   -> Inserindo o valor 5 na arvore...");
        raiz = insereRecursivo(raiz, 5);
        System.out.print("   -> Arvore apos insercao (Por Nivel): ");
        percursoPorNivel(raiz); 

        System.out.println("\nEx 7) Altura da arvore apos insercao: " + calcularAltura(raiz)); 
    }
}
