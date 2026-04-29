package Lab1_5;
class Student1_5 {
    String surname;
    int course;
    String gender;
    boolean playsKVK;

    public Student1_5(String surname, int course, String gender, boolean playsKVK) {
        this.surname = surname;
        this.course = course;
        this.gender = gender;
        this.playsKVK = playsKVK;
    }

    public void print() {
        System.out.printf("Прізвище: %-10s | Курс: %d | Стать: %-7s | КВК: %b%n", 
                surname, course, gender, playsKVK);
    }
}

public class Main1_5 {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.5 (Послідовний пошук) ===");

        // Масив на 20 елементів, упорядкований за КУРСОМ (від 1 до 5)
        Student1_5[] students = new Student1_5[20];
        int count = 20;

        // Ініціалізація відсортованого за курсом масиву
        students[0] = new Student1_5("Іванов", 1, "Чоловік", false);
        students[1] = new Student1_5("Петров", 1, "Чоловік", true);
        students[2] = new Student1_5("Зуб", 1, "Жінка", false);
        students[3] = new Student1_5("Коваленко", 2, "Жінка", true);
        students[4] = new Student1_5("Сидоренко", 2, "Чоловік", false);
        students[5] = new Student1_5("Шевченко", 2, "Чоловік", false);
        students[6] = new Student1_5("Ткач", 2, "Жінка", false);
        students[7] = new Student1_5("Бойко", 3, "Чоловік", true);
        students[8] = new Student1_5("Мельник", 3, "Жінка", false);
        students[9] = new Student1_5("Кравченко", 3, "Чоловік", false);
        students[10] = new Student1_5("Лисенко", 3, "Жінка", true);
        
        // Студенти 4 курсу (цільові для пошуку та видалення)
        students[11] = new Student1_5("Савченко", 4, "Чоловік", true);  // Ціль!
        students[12] = new Student1_5("Козак", 4, "Чоловік", false);
        students[13] = new Student1_5("Гриценко", 4, "Жінка", true);
        students[14] = new Student1_5("Романенко", 4, "Чоловік", true); // Ціль!
        students[15] = new Student1_5("Павленко", 4, "Жінка", false);
        
        students[16] = new Student1_5("Макаренко", 5, "Чоловік", true);
        students[17] = new Student1_5("Юрченко", 5, "Жінка", false);
        students[18] = new Student1_5("Марченко", 5, "Чоловік", false);
        students[19] = new Student1_5("Олійник", 5, "Жінка", true);

        System.out.println("--- Масив ДО видалення ---");
        for (int i = 0; i < count; i++) students[i].print();

        // Послідовний пошук та видалення
        for (int i = 0; i < count; i++) {
            if (students[i].course == 4 && students[i].gender.equals("Чоловік") && students[i].playsKVK) {
                System.out.println("\nЗнайдено на видалення: " + students[i].surname);
                
                // Зсув масиву ліворуч для видалення
                for (int j = i; j < count - 1; j++) {
                    students[j] = students[j + 1];
                }
                students[count - 1] = null;
                count--; 
                i--; // Перевіряємо поточний індекс знову, бо елементи зсунулися
            }
        }

        System.out.println("\n--- Масив ПІСЛЯ видалення ---");
        for (int i = 0; i < count; i++) students[i].print();
    }
}