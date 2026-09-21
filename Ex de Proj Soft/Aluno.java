public class Aluno {
    String nome;
    int matricula;
    double notaFinal;

    public boolean foiAprovado() {
        if(notaFinal >= 7) {
            return true;
        } else {
            return false;
        }
    }
}