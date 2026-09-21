public No buscarPai(No y) {
    if (this.raiz == null || y == null || this.raiz == y) {
        return null;
    }

    No atual = this.raiz;
    No pai = null;

    while (atual != null && atual != y) {
        pai = atual;
        
        if (y.valor < atual.valor) {
            atual = atual.esquerda;
        } else {
            atual = atual.direita;
        }
    }
    return (atual == y) ? pai : null;
}