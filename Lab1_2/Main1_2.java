package Lab1_2;
// Елемент хеш-таблиці
class MyString {
    private final String text;

    public MyString(String text) {
        this.text = text;
    }

    // Ключ
    public int getLength() {
        return text.length();
    }

    public int countDigits() {
        int count = 0;
        for (char c : text.toCharArray()) {
            if (Character.isDigit(c)) count++;
        }
        return count;
    }

    public void print() {
        System.out.printf("Рядок: '%-7s' | Ключ(Довжина): %d | Цифр: %d", text, getLength(), countDigits());
    }
}

// Хеш-таблиця
class HashTable {
    private final MyString[] table;
    private final int size;
    private static final double A = 0.6180339887; // Константа для множення

    public HashTable(int size) {
        this.size = size;
        this.table = new MyString[size];
    }

    private int hashFunction(int key) {
        double fractionalPart = (key * A) % 1.0; 
        return (int) Math.floor(size * fractionalPart);
    }

    public boolean insert(MyString item) {
        int hashIndex = hashFunction(item.getLength());

        if (table[hashIndex] == null) {
            table[hashIndex] = item;
            return true;
        } else {
            System.out.printf("Колізія для '%s' у позиції %d%n", item, hashIndex);
            return false;
        }
    }

    public void printTable() {
        System.out.println("=== Вміст хеш-таблиці ===");
        for (int i = 0; i < size; i++) {
            System.out.printf("[%d] ", i);
            if (table[i] != null) {
                table[i].print();
                System.out.println();
            } else {
                System.out.println("порожньо");
            }
        }
    }
}

public class Main1_2 {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.2 (Хеш-таблиця) ===");
        HashTable hashTable = new HashTable(5);
        
        // Вставка без колізій
        hashTable.insert(new MyString("A"));      // Довжина 1 -> 3
        hashTable.insert(new MyString("B1"));     // Довжина 2 -> 1
        hashTable.insert(new MyString("C23"));    // Довжина 3 -> 4
        hashTable.insert(new MyString("D456"));   // Довжина 4 -> 2
        hashTable.insert(new MyString("E7890"));  // Довжина 5 -> 0

        hashTable.printTable();
    }
}