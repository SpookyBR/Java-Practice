public class Ex2 {
    public static int contarFolhas(No p) {
        if (p == null) return 0;

        if (p.esq == null && p.dir == null) return 1;

        return contarFolhas(p.esq) + contarFolhas(p.dir);
    }
}