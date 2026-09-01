public class ContaBancaria {
    String titular;
    double saldo;

    public void Depositar(double Valor) {
        if(Valor <= 0) {
            System.out.println("Valor de depósito inválido.");
            return;
        }
        saldo += Valor;
    }

    public void Sacar(double Valor) {
        if(Valor <= 0) {
            System.out.println("Valor de saque inválido.");
            return;
        }
        if (Valor <= saldo) {
            saldo -= Valor;
        } else {
            System.out.println("Saldo insuficiente para saque.");
        }
    }

    public void ConsultarSaldo() {
        System.out.println("Saldo atual: " + saldo);
    }
}