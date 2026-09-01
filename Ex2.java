public class Ex2 {
    public static void main(String[] args) {
        Produto produto1 = new Produto();
        String nome = "Notebook";
        Float preço = 300.00f;
        int quantidadeEmEstoque = 10;
        String resumo = produto1.ExibirResumo(nome, preço, quantidadeEmEstoque);
        System.out.println(resumo);

        Produto produto2 = new Produto();
        String nome2 = "Smartphone";
        Float preço2 = 1500.00f;
        int quantidadeEmEstoque2 = 5;
        String resumo2 = produto2.ExibirResumo(nome2, preço2, quantidadeEmEstoque2);
        System.out.println(resumo2);

        Produto produto3 = new Produto();
        String nome3 = "Fone de ouvido";
        Float preço3 = 200.00f;
        int quantidadeEmEstoque3 = 20;
        String resumo3 = produto3.ExibirResumo(nome3, preço3, quantidadeEmEstoque3);
        System.out.println(resumo3);
    }
}