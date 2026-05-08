package HomeTask;
import java.util.Scanner;

public class HomeTask1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[][] A = {
            {-2, -2, 9, 6},
            {6, -6, -10, -9},
            {6, 1, -2, 4},
            {9, 2, -10, -1}
        };
        double[] b = {49, -82, -29, -79};
        solveLUP(A, b);
        solveRungeKutta3();
        scanner.close();
    }

    public static void solveLUP(double[][] A, double[] b) {
        int n = A.length;
        System.out.println("===============================================================");
        System.out.println("   ЗАВДАННЯ 1: РОЗВ'ЯЗАННЯ СЛАР МЕТОДОМ LUP-РОЗКЛАДАННЯ");
        System.out.println("===============================================================");
        
        System.out.println("\n[0] ВИХІДНА МАТРИЦЯ А:");
        for (int i = 0; i < n; i++) {
            System.out.print("  | ");
            for (int j = 0; j < n; j++) {
                System.out.printf("%7.2f ", A[i][j]);
            }
            System.out.println(" |");
        }

        System.out.println("\n[1] ПОЧАТКОВА СИСТЕМА РІВНЯНЬ (Ax = b):");
        for (int i = 0; i < n; i++) {
            System.out.printf("  %7.2fx1 + %7.2fx2 + %7.2fx3 + %7.2fx4  =  %7.2f \n", 
                A[i][0], A[i][1], A[i][2], A[i][3], b[i]);
        }

        double[][] LU = new double[n][n];
        for (int i = 0; i < n; i++) System.arraycopy(A[i], 0, LU[i], 0, n);
        int[] P = new int[n];
        for (int i = 0; i < n; i++) P[i] = i;

        for (int i = 0; i < n; i++) {
            double maxVal = 0;
            int pivot = i;
            for (int j = i; j < n; j++) {
                if (Math.abs(LU[j][i]) > maxVal) {
                    maxVal = Math.abs(LU[j][i]);
                    pivot = j;
                }
            }
            int tempP = P[i]; P[i] = P[pivot]; P[pivot] = tempP;
            double[] tempRow = LU[i]; LU[i] = LU[pivot]; LU[pivot] = tempRow;
            for (int j = i + 1; j < n; j++) {
                LU[j][i] /= LU[i][i];
                for (int k = i + 1; k < n; k++) LU[j][k] -= LU[j][i] * LU[i][k];
            }
        }

        System.out.println("\n[2] МАТРИЦЯ L (Нижня трикутна):");
        printMatrixL(LU);
        System.out.println("\n[3] МАТРИЦЯ U (Верхня трикутна):");
        printMatrixU(LU);

        double[] Pb = new double[n];
        for (int i = 0; i < n; i++) Pb[i] = b[P[i]];
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < i; j++) sum += LU[i][j] * y[j];
            y[i] = Pb[i] - sum;
        }
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) sum += LU[i][j] * x[j];
            x[i] = (y[i] - sum) / LU[i][i];
        }

        System.out.println("\n[4] ВЕКТОР НЕВІДОМИХ X (Корені системи):");
        for (int i = 0; i < n; i++) System.out.printf("  x%d = %8.4f\n", (i+1), x[i]);
        System.out.println("---------------------------------------------------------------");
    }

    public static void solveRungeKutta3() {
        double t = 0, h = 0.1;
        double z1 = 1, z2 = 0, z3 = 0;
        String equationDesc = "y''' = 5y'' - 2y + sin(t)";

        System.out.println("\n\n===============================================================");
        System.out.println("   ЗАВДАННЯ 2: РОЗВ'ЯЗАННЯ ДИФЕРЕНЦІЙНОГО РІВНЯННЯ");
        System.out.println("===============================================================");
        System.out.println("МЕТОД: Рунге-Кутта 3-го порядку");
        System.out.println("РІВНЯННЯ: " + equationDesc);
        System.out.println("---------------------------------------------------------------");
        System.out.println("  t  |    y (z1)    |    y' (z2)   |    y'' (z3)");
        System.out.println("---------------------------------------------------------------");

        for (int step = 0; step <= 10; step++) {
            System.out.printf("%.1f  |  %12.6f  |  %12.6f  |  %12.6f\n", t, z1, z2, z3);
            double k1_1 = h * z2;
            double k1_2 = h * z3;
            double k1_3 = h * f(t, z1, z2, z3);
            double k2_1 = h * (z2 + k1_2 / 2);
            double k2_2 = h * (z3 + k1_3 / 2);
            double k2_3 = h * f(t + h / 2, z1 + k1_1 / 2, z2 + k1_2 / 2, z3 + k1_3 / 2);
            double k3_1 = h * (z2 - k1_2 + 2 * k2_2);
            double k3_2 = h * (z3 - k1_3 + 2 * k2_3);
            double k3_3 = h * f(t + h, z1 - k1_1 + 2 * k2_1, z2 - k1_2 + 2 * k2_2, z3 - k1_3 + 2 * k2_3);
            z1 += (k1_1 + 4 * k2_1 + k3_1) / 6;
            z2 += (k1_2 + 4 * k2_2 + k3_2) / 6;
            z3 += (k1_3 + 4 * k2_3 + k3_3) / 6;
            t += h;
        }
        System.out.println("---------------------------------------------------------------");
    }

    public static double f(double t, double z1, double z2, double z3) {
        return 5 * z3 - 2 * z1 + Math.sin(t);
    }

    private static void printMatrixL(double[][] LU) {
        for (int i = 0; i < LU.length; i++) {
            System.out.print("  | ");
            for (int j = 0; j < LU.length; j++) {
                if (i == j) System.out.print(" 1.00 ");
                else if (i > j) System.out.printf("%5.2f ", LU[i][j]);
                else System.out.print(" 0.00 ");
            }
            System.out.println(" |");
        }
    }

    private static void printMatrixU(double[][] LU) {
        for (int i = 0; i < LU.length; i++) {
            System.out.print("  | ");
            for (int j = 0; j < LU.length; j++) {
                if (i <= j) System.out.printf("%5.2f ", LU[i][j]);
                else System.out.print(" 0.00 ");
            }
            System.out.println(" |");
        }
    }
}