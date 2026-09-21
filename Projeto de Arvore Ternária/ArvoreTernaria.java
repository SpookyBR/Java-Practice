import java.util.ArrayList;
import java.util.List;

public class ArvoreTernaria {
    private No raiz;

    public void inserir(String palavra, String arquivo) {
        if (palavra == null || palavra.isEmpty()) return;
        raiz = inserir(raiz, palavra.toCharArray(), 0, arquivo);
    }

    private No inserir(No no, char[] palavra, int pos, String arquivo) {
        char c = palavra[pos];
        
        if (no == null) {
            no = new No(c);
        }
        
        if (c < no.caractere) {
            no.esquerda = inserir(no.esquerda, palavra, pos, arquivo);
        } else if (c > no.caractere) {
            no.direita = inserir(no.direita, palavra, pos, arquivo);
        } else if (pos < palavra.length - 1) {
            no.meio = inserir(no.meio, palavra, pos + 1, arquivo);
        } else {
            no.fimDaPalavra = true;
            // Evita arquivos duplicados na associação de uma palavra[cite: 1]
            if (!no.arquivosAssociados.contains(arquivo)) {
                no.arquivosAssociados.add(arquivo);
            }
        }
        return no;
    }

    public List<String> buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) return new ArrayList<>();
        No no = buscar(raiz, palavra.toCharArray(), 0);
        if (no != null && no.fimDaPalavra) {
            return no.arquivosAssociados;
        }
        return new ArrayList<>(); // Tratamento de palavras inexistentes[cite: 1]
    }

    private No buscar(No no, char[] palavra, int pos) {
        if (no == null) return null;
        char c = palavra[pos];
        
        if (c < no.caractere) {
            return buscar(no.esquerda, palavra, pos);
        } else if (c > no.caractere) {
            return buscar(no.direita, palavra, pos);
        } else if (pos < palavra.length - 1) {
            return buscar(no.meio, palavra, pos + 1);
        } else {
            return no;
        }
    }
}