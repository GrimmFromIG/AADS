package Lab1_6;
import java.util.Random;

public class Main1_6 {

    // Пірамідальне сортування (Heap Sort)
    public static void heapSort(double[] arr) {
        int n = arr.length;
        // Побудова купи (перегрупування масиву)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }
        // Один за одним витягуємо елементи з купи
        for (int i = n - 1; i > 0; i--) {
            double temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    private static void heapify(double[] arr, int n, int i) {
        int largest = i; 
        int l = 2 * i + 1; 
        int r = 2 * i + 2; 

        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;

        if (largest != i) {
            double swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    // Генерація масиву випадкових чисел
    private static double[] generateArray(int size) {
        double[] arr = new double[size];
        Random rand = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextDouble() * 1000;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.6 (Аналіз Пірамідального сортування) ===");

        int[] sizes = {100, 10000, 1000000};

        for (int size : sizes) {
            double[] arr = generateArray(size);
            
            long startTime = System.nanoTime();
            heapSort(arr);
            long endTime = System.nanoTime();
            
            long duration = endTime - startTime;
            System.out.printf("Розмір масиву: %-8d | Час виконання: %d нс (%.2f мс)%n", 
                    size, duration, duration / 1_000_000.0);
        }
    }
}