public class Ex3 {
    public static void main(String[] args) {
        Retangulo retangulo1 = new Retangulo(5.0, 3.0);
        double area1 = retangulo1.calcularArea();
        double perimetro1 = retangulo1.calcularPerimetro();
        System.out.println("Retângulo 1 - Área: " + area1 + ", Perímetro: " + perimetro1);

        Retangulo retangulo2 = new Retangulo(4.0, 6.0);
        double area2 = retangulo2.calcularArea();
        double perimetro2 = retangulo2.calcularPerimetro();
        System.out.println("Retângulo 2 - Área: " + area2 + ", Perímetro: " + perimetro2);
    }
}
