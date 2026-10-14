import java.util.ArrayList;
import java.util.List;

public class ArvoreBMais {
    private int ordem;
    private No raiz;
    
    public ArvoreBMais(int ordem) {
        this.ordem = ordem;
        this.raiz = new NoFolha();
    }
    
    private abstract class No {
        List<Integer> chaves;
        
        No() {
            chaves = new ArrayList<>();
        }
    }
    
    private class NoInterno extends No {
        List<No> filhos;
        
        NoInterno() {
            super();
            filhos = new ArrayList<>();
        }
    }
    
    private class NoFolha extends No {
        NoFolha proxima;
        
        NoFolha() {
            super();
            proxima = null;
        }
    }

    public void inserir(int chave) {
        No novoNo = inserirRecursivo(raiz, chave);
        
        if (novoNo != null) {
            NoInterno novaRaiz = new NoInterno();
            novaRaiz.chaves.add(novoNo.chaves.get(0));
            novaRaiz.filhos.add(raiz);
            novaRaiz.filhos.add(novoNo);
            
            if (novoNo instanceof NoInterno) {
                novoNo.chaves.remove(0);
            }
            raiz = novaRaiz;
        }
    }

    private No inserirRecursivo(No atual, int chave) {
        if (atual instanceof NoFolha) {
            NoFolha folha = (NoFolha) atual;
            inserirOrdenado(folha.chaves, chave);
            
            if (folha.chaves.size() > ordem - 1) {
                return dividirFolha(folha);
            }
            return null;
        
        } else {
            NoInterno interno = (NoInterno) atual;
            int i = 0;
            while (i < interno.chaves.size() && chave >= interno.chaves.get(i)) {
                i++;
            }
            
            No novoFilho = inserirRecursivo(interno.filhos.get(i), chave);
            
            if (novoFilho != null) {
                interno.chaves.add(i, novoFilho.chaves.get(0));
                interno.filhos.add(i + 1, novoFilho);
                
                if (novoFilho instanceof NoInterno) {
                    novoFilho.chaves.remove(0);
                }
                
                if (interno.chaves.size() > ordem - 1) {
                    return dividirInterno(interno);
                }
            }
            return null;
        }
    }

    private void inserirOrdenado(List<Integer> chaves, int chave) {
        int i = 0;
        while (i < chaves.size() && chave > chaves.get(i)) {
            i++;
        }
        chaves.add(i, chave);
    }

    private NoFolha dividirFolha(NoFolha folha) {
        NoFolha novaFolha = new NoFolha();
        int meio = folha.chaves.size() / 2;
        
        while (folha.chaves.size() > meio) {
            novaFolha.chaves.add(folha.chaves.remove(meio));
        }
        
        novaFolha.proxima = folha.proxima;
        folha.proxima = novaFolha;
        
        return novaFolha;
    }

    private NoInterno dividirInterno(NoInterno interno) {
        NoInterno novoInterno = new NoInterno();
        int meio = interno.chaves.size() / 2;
        
        while (interno.chaves.size() > meio) {
            novoInterno.chaves.add(interno.chaves.remove(meio));
            novoInterno.filhos.add(interno.filhos.remove(meio + 1));
        }
        return novoInterno;
    }
}