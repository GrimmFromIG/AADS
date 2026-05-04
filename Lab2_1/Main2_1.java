package Lab2_1; 
import java.util.Scanner;

public class Main2_1 {
    // Підінтегральна функція з 20 варіанта
    public static double f(double x) {
        return Math.pow(x, 3) / (Math.pow(x, 8) + 1);
    }

    public static void main(String[] args) {
        System.out.println("=== Лабораторна 2.1 (Чисельне інтегрування) ===");
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введіть початок інтервалу a (за варіантом 0): ");
        double a = scanner.nextDouble();
        System.out.print("Введіть кінець інтервалу b (за варіантом 1): ");
        double b = scanner.nextDouble();
        System.out.print("Введіть крок h (за варіантом 0,1): ");
        double h = scanner.nextDouble();
        
        int n = (int) Math.round((b - a) / h);

        // 1. Метод прямокутників (середніх)
        double sumRect = 0;
        for (int i = 0; i < n; i++) {
            double x_mid = a + h * (i + 0.5);
            sumRect += f(x_mid);
        }
        double resRect = h * sumRect;

        // 2. Метод трапецій
        double sumTrap = (f(a) + f(b)) / 2.0;
        for (int i = 1; i < n; i++) {
            double x_i = a + i * h;
            sumTrap += f(x_i);
        }
        double resTrap = h * sumTrap;

        // 3. Метод Сімпсона
        double sumSimp = f(a) + f(b);
        for (int i = 1; i < n; i++) {
            double x_i = a + i * h;
            if (i % 2 == 0) {
                sumSimp += 2 * f(x_i);
            } else {
                sumSimp += 4 * f(x_i);
            }
        }
        double resSimp = (h / 3.0) * sumSimp;

        System.out.println("\nРезультати обчислення інтеграла:");
        System.out.printf("Метод прямокутників: %.6f%n", resRect);
        System.out.printf("Метод трапецій:      %.6f%n", resTrap);
        System.out.printf("Метод Сімпсона:      %.6f%n", resSimp);
        
        scanner.close();
    }
}