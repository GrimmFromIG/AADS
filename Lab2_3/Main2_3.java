package Lab2_3; 
public class Main2_3 {
    
    // Метод для обчислення факторіала (використовуємо long, оскільки 12! велике число)
    public static long factorial(int n) {
        if (n <= 1) return 1;
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Лабораторна 2.3 (Комбінаторні алгоритми) ===");
        System.out.println("Варіант 20: 15 студентів, 15 баз практики. 3 бази забирають 3 найкращих студентів.");
        
        System.out.println("\nТип вибірки: Перестановки без повторень (Permutation).");
        
        // 3 топ-студенти на 3 елітні бази: 3! способів
        long topStudentsWays = factorial(3);
        
        // Решта 12 студентів на решту 12 баз: 12! способів
        long otherStudentsWays = factorial(12);
        
        // Загальна кількість способів (добуток, за правилом комбінаторики)
        long totalWays = topStudentsWays * otherStudentsWays;
        
        System.out.println("Кількість способів розподілити топ-3 студентів (3!): " + topStudentsWays);
        System.out.println("Кількість способів розподілити решту 12 студентів (12!): " + otherStudentsWays);
        System.out.println("Загальна кількість можливих варіантів розподілу: " + totalWays);
    }
}