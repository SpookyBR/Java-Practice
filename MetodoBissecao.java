public class MetodoBissecao {

    public static double f(double x) {
        return Math.pow(x, 3) - 9 * x + 3;
    }

    public static double Bissecao(double A, double B, double E) {
        
        if (f(A) * f(B) >= 0) {
            System.out.println("Erro: O intervalo [A, B] não garante a existência de uma raiz (f(A) e f(B) tem o mesmo sinal).");
            return Double.NaN;
        }

        double X = A;
        double erroCalculado = Math.abs(B - A);

        while (erroCalculado > E) {
            X = (A + B) / 2.0;

            if (f(X) == 0.0) {
                erroCalculado = 0.0;
                break; 
            }

            if (f(A) * f(X) < 0) {
                B = X;
            } else {
                A = X;
            }

            erroCalculado = Math.abs(B - A);
        }

        System.out.println("Erro calculado: " + erroCalculado);

        return (A + B) / 2.0; 
    }

    public static void main(String[] args) {
        double A = 0.0;
        double B = 1.0;
        double E = 0.001;
        
        System.out.println("Iniciando o método da Bisseção...");
        double raiz = Bissecao(A, B, E);
        
        System.out.println("Raiz encontrada: " + raiz);
    }
}