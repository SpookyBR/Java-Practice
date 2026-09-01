public class Ex7 {
        public static int calcularAltura(No p) {
        if (p == null) return 0;
        return 1 + Math.max(calcularAltura(p.esq), calcularAltura(p.dir));
    }
}