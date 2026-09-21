private int somaFolhas(No p) {
    if (p == null) {
        return 0;
    }
    if (p.esquerda == null && p.direita == null) {
        return p.valor;
    }
    
    return somaFolhas(p.esquerda) + somaFolhas(p.direita);
}