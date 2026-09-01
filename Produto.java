public class Produto {
    String nome;
    Float preço;
    int quantidadeEmEstoque;

    public String ExibirResumo(String nome, Float preço, int quantidadeEmEstoque) {
        return "Produto: " + nome + ", Preço: R$" + preço + ", Quantidade em estoque: " + quantidadeEmEstoque;
    }
}