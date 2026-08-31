public class Ex1 {
    
    public static int contarTotalNos(No p) {
        if(p == null) {
            return 0;
        }
            
        return 1 + contarTotalNos(p.esq) + contarTotalNos(p.dir);
    }
}