import java.io.*;
import java.text.Normalizer;
import java.util.*;

public class SistemaBusca {
    private ArvoreTernaria indice;
    private Set<String> todosDocumentos;

    public SistemaBusca() {
        this.indice = new ArvoreTernaria();
        this.todosDocumentos = new HashSet<>();
    }

    // Normalização: Maiúsculas, minúsculas e pontuação[cite: 1]
    public static String normalizar(String palavra) {
        String semAcento = Normalizer.normalize(palavra, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return semAcento.toLowerCase().replaceAll("[^a-z0-9]", "");
    }

    public void indexarDiretorio(String caminhoDiretorio) {
        System.out.println("Construindo indice...");
        File diretorio = new File(caminhoDiretorio);
        File[] arquivos = diretorio.listFiles((dir, name) -> name.endsWith(".txt"));

        if (arquivos == null || arquivos.length == 0) {
            System.out.println("Nenhum arquivo .txt encontrado no diretório.");
            return;
        }

        for (File arquivo : arquivos) {
            String nomeArquivo = arquivo.getName();
            todosDocumentos.add(nomeArquivo);
            
            try (Scanner scanner = new Scanner(arquivo)) {
                while (scanner.hasNext()) {
                    String palavraRaw = scanner.next();
                    String palavraNormalizada = normalizar(palavraRaw);
                    if (!palavraNormalizada.isEmpty()) {
                        indice.inserir(palavraNormalizada, nomeArquivo);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Erro ao ler o arquivo: " + nomeArquivo);
            }
        }
        System.out.println("Indice construido com sucesso.\n");
    }

    // Define a precedência de operadores: NAO (3), E (2), OU (1)[cite: 1]
    private int precedencia(String operador) {
        switch (operador) {
            case "NAO": return 3;
            case "E": return 2;
            case "OU": return 1;
            default: return 0;
        }
    }

    private boolean isOperador(String token) {
        return token.equals("E") || token.equals("OU") || token.equals("NAO");
    }

    public Set<String> avaliarConsulta(String consulta) {
        consulta = consulta.replace("(", " ( ").replace(")", " ) ");
        String[] tokens = consulta.trim().split("\\s+");

        List<String> posfixa = new ArrayList<>();
        Stack<String> operadores = new Stack<>();

        // Infixa para Posfixa (Shunting Yard)
        for (String token : tokens) {
            if (token.isEmpty()) continue;

            if (token.equals("(")) {
                operadores.push(token);
            } else if (token.equals(")")) {
                while (!operadores.isEmpty() && !operadores.peek().equals("(")) {
                    posfixa.add(operadores.pop());
                }
                if (!operadores.isEmpty()) operadores.pop();
            } else if (isOperador(token)) {
                while (!operadores.isEmpty() && precedencia(operadores.peek()) >= precedencia(token)) {
                    posfixa.add(operadores.pop());
                }
                operadores.push(token);
            } else {
                posfixa.add(token);
            }
        }
        while (!operadores.isEmpty()) {
            posfixa.add(operadores.pop());
        }

        // Avaliação da Expressão Posfixa usando Conjuntos
        Stack<Set<String>> pilhaExecucao = new Stack<>();
        for (String token : posfixa) {
            if (token.equals("NAO")) {
                if (pilhaExecucao.isEmpty()) continue;
                Set<String> operando = pilhaExecucao.pop();
                Set<String> resultado = new HashSet<>(todosDocumentos);
                resultado.removeAll(operando);
                pilhaExecucao.push(resultado);
            } else if (token.equals("E")) {
                if (pilhaExecucao.size() < 2) continue;
                Set<String> b = pilhaExecucao.pop();
                Set<String> a = pilhaExecucao.pop();
                Set<String> resultado = new HashSet<>(a);
                resultado.retainAll(b);
                pilhaExecucao.push(resultado);
            } else if (token.equals("OU")) {
                if (pilhaExecucao.size() < 2) continue;
                Set<String> b = pilhaExecucao.pop();
                Set<String> a = pilhaExecucao.pop();
                Set<String> resultado = new HashSet<>(a);
                resultado.addAll(b);
                pilhaExecucao.push(resultado);
            } else {
                String palavraNorm = normalizar(token);
                pilhaExecucao.push(new HashSet<>(indice.buscar(palavraNorm)));
            }
        }

        return pilhaExecucao.isEmpty() ? new HashSet<>() : pilhaExecucao.pop();
    }

    public static void main(String[] args) {
        SistemaBusca sistema = new SistemaBusca();
        Scanner input = new Scanner(System.in);

        System.out.println("INDICE INVERTIDO");
        sistema.indexarDiretorio("./documentos"); 

        while (true) {
            System.out.println("Digite uma consulta:");
            System.out.print("> ");
            String consulta = input.nextLine();

            if (consulta.equalsIgnoreCase("sair")) {
                System.out.println("Programa encerrado.");
                break;
            }

            Set<String> resultados = sistema.avaliarConsulta(consulta);

            // Apresentação dos resultados de forma organizada[cite: 1]
            if (resultados.isEmpty()) {
                System.out.println("Nenhum arquivo encontrado.");
            } else {
                System.out.println("Arquivos encontrados:");
                List<String> resultadosOrdenados = new ArrayList<>(resultados);
                Collections.sort(resultadosOrdenados);
                for (String doc : resultadosOrdenados) {
                    System.out.println(doc);
                }
            }
            System.out.println();
        }
        input.close();
    }
}