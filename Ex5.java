import java.util.LinkedList;
import java.util.Queue;

public class Ex5 {
    public static void percursoPorNivel(No p) {
        if (p == null) return;
        
        Queue<No> fila = new LinkedList<>();
        fila.add(p);
        
        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.chave + " ");
            

            if (atual.esq != null) {
                fila.add(atual.esq);
            }
            if (atual.dir != null) {
                fila.add(atual.dir);
            }
        }
        System.out.println();
    }
}
    