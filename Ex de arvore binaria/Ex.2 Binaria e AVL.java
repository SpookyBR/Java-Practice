public Integer buscarMaiorMenorOuIgual(int n) {
    return buscarMaiorMenorOuIgual(this.raiz, n);
}

private Integer buscarMaiorMenorOuIgual(No no, int n) {
    Integer melhorCandidato = null;
    while (no != null) {
        if (no.valor == n) {
            return no.valor;
        } else if (no.valor > n ) {
            no = no.esquerda;
        } else {
            melhorCandidato = no.valor;
            no = no.direita;
        }
    }
    return melhorCandidato;
}