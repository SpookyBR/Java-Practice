public class Ex3 {
    public static int contarInternos(No p) {
        if (p == null) return 0;

        if (p.esq == null && p.dir == null) return 0;
        
        return 1 + contarInternos(p.esq) + contarInternos(p.dir);
    }
}