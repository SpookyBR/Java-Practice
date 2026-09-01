public class Ex5 {
    public static void main(String[] args) {
        ContaBancaria conta = new ContaBancaria();
        conta.titular = "João";
        conta.saldo = 1000.0;

        System.out.println("Titular: " + conta.titular);
        conta.ConsultarSaldo();

        conta.Depositar(500.0);
        System.out.println("Após depósito de 500.0:");
        conta.ConsultarSaldo();

        conta.Sacar(200.0);
        System.out.println("Após saque de 200.0:");
        conta.ConsultarSaldo();

        conta.Sacar(1500.0); // Tentativa de saque maior que o saldo
    }
}