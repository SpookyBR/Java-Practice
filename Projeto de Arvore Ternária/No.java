import java.util.ArrayList;
import java.util.List;

// Estrutura mínima do nó conforme exigido
public class No {
    public char caractere;
    public No esquerda, meio, direita;
    public boolean fimDaPalavra;
    public List<String> arquivosAssociados;

    public No(char caractere) {
        this.caractere = caractere;
        this.fimDaPalavra = false;
        // Estrutura auxiliar justificada para armazenar a lista de arquivos
        this.arquivosAssociados = new ArrayList<>(); 
    }
}