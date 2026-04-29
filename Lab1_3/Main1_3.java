package Lab1_3;

class Student {
    String surname, name, residence;
    int course;
    long studentId; 
    double averageGrade;

    public Student(String surname, String name, int course, long studentId, double averageGrade, String residence) {
        this.surname = surname;
        this.name = name;
        this.course = course;
        this.studentId = studentId;
        this.averageGrade = averageGrade;
        this.residence = residence;
    }

    public void printTableRow() {
        System.out.printf("| %-12s | %-10s | %-4d | %-12d | %-12.2f | %-17s |%n",
                surname, name, course, studentId, averageGrade, residence);
    }
}

class TreeNode {
    Student data;
    TreeNode left, right;

    public TreeNode(Student data) {
        this.data = data;
    }
}

class BinaryTree {
    private TreeNode root;

    public void insert(Student student) {
        root = insertRecursive(root, student);
    }

    private TreeNode insertRecursive(TreeNode current, Student student) {
        if (current == null) return new TreeNode(student);

        if (student.studentId < current.data.studentId) {
            current.left = insertRecursive(current.left, student);
        } else if (student.studentId > current.data.studentId) {
            current.right = insertRecursive(current.right, student);
        }
        return current;
    }

    // Послідовний обхід
    public void printInOrder() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.printf("| %-12s | %-10s | %-4s | %-12s | %-12s | %-17s |%n",
                "Прізвище", "Ім'я", "Курс", "Студ. квиток", "Середній бал", "Місце проживання");
        System.out.println("-------------------------------------------------------------------------------------");
        traverseInOrder(root);
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private void traverseInOrder(TreeNode node) {
        if (node != null) {
            traverseInOrder(node.left);
            node.data.printTableRow(); 
            traverseInOrder(node.right);
        }
    }
}

public class Main1_3 {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.3 (Бінарне дерево) ===");
        BinaryTree tree = new BinaryTree();

        tree.insert(new Student("Шевченко", "Андрій", 3, 10050, 4.8, "Київ"));
        tree.insert(new Student("Коваленко", "Олена", 2, 10020, 4.2, "Гуртожиток №3"));
        tree.insert(new Student("Бойко", "Іван", 1, 10080, 3.9, "Львів"));
        tree.insert(new Student("Ткаченко", "Марія", 4, 10010, 5.0, "Гуртожиток №1"));
        tree.insert(new Student("Петренко", "Василь", 3, 10060, 4.5, "Одеса"));

        tree.printInOrder();
    }
}