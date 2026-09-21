public class Ex1 {
    public static void main(String[] args) {
        Pessoa pessoa1 = new Pessoa();
        String nome = "João";
        String idade = "25";
        String apresentacao = pessoa1.apresentar(nome, idade);
        System.out.println(apresentacao);

        Pessoa pessoa2 = new Pessoa();
        String nome2 = "Maria";
        String idade2 = "30";
        String apresentacao2 = pessoa2.apresentar(nome2, idade2);
        System.out.println(apresentacao2);
    }
}