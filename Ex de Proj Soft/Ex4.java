public class Ex4 {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno();
        aluno1.nome = "João";
        aluno1.matricula = 12345;
        aluno1.notaFinal = 8.5;

        Aluno aluno2 = new Aluno();
        aluno2.nome = "Maria";
        aluno2.matricula = 67890;
        aluno2.notaFinal = 6.0;

        System.out.println("Aluno: " + aluno1.nome + ", Aprovado: " + aluno1.foiAprovado());
        System.out.println("Aluno: " + aluno2.nome + ", Aprovado: " + aluno2.foiAprovado());
    }
}