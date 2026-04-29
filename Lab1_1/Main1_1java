package Lab1_1;
// Клас, що описує чергу з векторним способом розміщення в пам'яті
class VectorQueue {
    private int[] queue;
    private int count;
    private int capacity;

    public VectorQueue(int size) {
        capacity = size;
        queue = new int[capacity];
        count = 0;
    }

    public boolean isFull() {
        return count == capacity; 
    }

    public boolean isEmpty() {
        return count == 0; 
    }

    public boolean enqueue(int element) {
        if (isFull()) return false; 
        
        queue[count] = element;
        count++;
        return true;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Черга порожня, видалення неможливе!");
        }
        
        int element = queue[0];
        
        // Зсув ліворуч
        for (int i = 0; i < count - 1; i++) {
            queue[i] = queue[i + 1];
        }
        count--;
        
        return element;
    }

    public void printQueue() {
        System.out.print("Вміст черги: ");
        if (isEmpty()) System.out.print("порожньо");
        for (int i = 0; i < count; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }
}

public class Main1_1 {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна 1.1 (Векторна черга) ===");
        VectorQueue queue = new VectorQueue(5);

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.printQueue();

        System.out.println("Видалено: " + queue.dequeue());
        System.out.println("Видалено: " + queue.dequeue());
        queue.printQueue();
        
        queue.enqueue(50);
        queue.enqueue(60);
        queue.printQueue();
    }
}