package Lab2_2;
import java.io.*;
import java.util.regex.*;

public class Main2_2 {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна 2.2 (Регулярні вирази) ===");
        
        String filename = "test_regex.txt";
        
        // 1. Створення тестового файлу програмно (для зручності)
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("+133A^K&");   // Правильне
            writer.println("+9B&");       // Правильне
            writer.println("-123A&");     // Неправильне (починається з -)
            writer.println("+123Z&");     // Неправильне (Z не входить у A-K)
            writer.println("+555^^&");    // Правильне
            writer.println("123A&");      // Неправильне (немає + на початку)
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Регулярний вираз за 20-м варіантом
        String regex = "^\\+[0-9]+[A-K\\^]+&$";
        Pattern pattern = Pattern.compile(regex);

        System.out.println("Читання файлу та пошук збігів...");
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.matches()) {
                    System.out.println("Знайдено відповідність: " + line);
                } else {
                    System.out.println("НЕ відповідає: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}