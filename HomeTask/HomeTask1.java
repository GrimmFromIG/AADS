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
    }

    public static void solveLUP(double[][] A, double[] b) {
        int n = A.length;
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
                for (int k = i + 1; k < n; k++) {
                    LU[j][k] -= LU[j][i] * LU[i][k];
                }
            }
        }

        printMatrixL(LU);
        printMatrixU(LU);

        double[] Pb = new double[n];
        for (int i = 0; i < n; i++) Pb[i] = b[P[i]];

        double[] y = new double[n];
        for (int i = 0; i < i; i++) {
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

        for (int i = 0; i < n; i++) System.out.printf("x%d = %.4f\n", (i+1), x[i]);
    }

    public static void solveRungeKutta3() {
        double t = 0, h = 0.1;
        double z1 = 1, z2 = 0, z3 = 0; 

        for (int step = 0; step <= 10; step++) {
            System.out.printf("%.1f\t|\t%.4f\n", t, z1);

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
    }

    public static double f(double t, double z1, double z2, double z3) {
        return -z3 - z2 - z1 + t;
    }

    private static void printMatrixL(double[][] LU) {
        for (int i = 0; i < LU.length; i++) {
            for (int j = 0; j < LU.length; j++) {
                if (i == j) System.out.print("1.0 ");
                else if (i > j) System.out.printf("%.1f ", LU[i][j]);
                else System.out.print("0.0 ");
            }
            System.out.println();
        }
    }

    private static void printMatrixU(double[][] LU) {
        for (int i = 0; i < LU.length; i++) {
            for (int j = 0; j < LU.length; j++) {
                if (i <= j) System.out.printf("%.1f ", LU[i][j]);
                else System.out.print("0.0 ");
            }
            System.out.println();
        }
    }
}