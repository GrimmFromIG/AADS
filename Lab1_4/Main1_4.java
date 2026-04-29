package Lab1_4;
class StudentDebt {
    String surname, name, group;
    int debts; // Ключ

    public StudentDebt(String surname, String name, String group, int debts) {
        this.surname = surname;
        this.name = name;
        this.group = group;
        this.debts = debts;
    }

    public void print() {
        System.out.printf("Студент: %-12s %-10s | Група: %-6s | Заборгованості: %d%n",
                surname, name, group, debts);
    }
}

public class Main1_4 {
    // Сортування розподіленим підрахунком ЗА СПАДАННЯМ
    public static void countingSortDescending(StudentDebt[] arr) {
        if (arr == null || arr.length <= 1) return;

        int maxDebts = arr[0].debts;
        for (StudentDebt s : arr) {
            if (s.debts > maxDebts) maxDebts = s.debts;
        }

        int[] count = new int[maxDebts + 1];
        StudentDebt[] output = new StudentDebt[arr.length];

        for (StudentDebt s : arr) {
            count[s.debts]++;
        }

        // Для спадання: накопичуємо суму справа наліво
        for (int i = maxDebts - 1; i >= 0; i--) {
            count[i] += count[i + 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int debtVal = arr[i].debts;
            output[count[debtVal] - 1] = arr[i];
            count[debtVal]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.4 (Сортування підрахунком) ===");

        StudentDebt[] students = {
            new StudentDebt("Шевченко", "Андрій", "ПІ-21", 2),
            new StudentDebt("Коваленко", "Олена", "ПІ-21", 0),
            new StudentDebt("Бойко", "Іван", "ПІ-22", 5),
            new StudentDebt("Ткаченко", "Марія", "ПІ-22", 2),
            new StudentDebt("Петренко", "Василь", "ПІ-23", 1)
        };

        System.out.println("\n--- До сортування ---");
        for (StudentDebt s : students) s.print();

        countingSortDescending(students);

        System.out.println("\n--- Після сортування (за спаданням заборгованостей) ---");
        for (StudentDebt s : students) s.print();
    }
}