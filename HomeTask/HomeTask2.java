package HomeTask;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;

public class HomeTask2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        Map<Integer, List<Integer>> adjList = new HashMap<Integer, List<Integer>>();
        
        int totalShots = 25;
        int initialShots = 5;
        int hitsNeeded = (totalShots - initialShots) / 2;

        for (int i = 0; i <= hitsNeeded; i++) {
            adjList.put(i, new ArrayList<Integer>());
            if (i < hitsNeeded) {
                adjList.get(i).add(i + 1);
            }
        }

        System.out.println("Представлення графа (Список суміжності):");
        
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.println("Влучання " + entry.getKey() + " -> наступний стан: " + entry.getValue());
        }

        System.out.print("\nВведіть початкову вершину для обходу (0-" + hitsNeeded + "): ");
        if (sc.hasNextInt()) {
            int startNode = sc.nextInt();

            System.out.println("Обхід графа в ширину (BFS):");
            bfs(adjList, startNode);
            
            System.out.println("\n\nРезультат: Кількість влучань для досягнення 25 пострілів = " + hitsNeeded);
        } else {
            System.out.println("Помилка: введено не число.");
        }
        sc.close();
    }

    public static void bfs(Map<Integer, List<Integer>> adjList, int startNode) {
        if (!adjList.containsKey(startNode)) {
            System.out.println("Вершини " + startNode + " не існує в графі.");
            return;
        }

        Set<Integer> visited = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();

        visited.add(startNode);
        queue.add(startNode);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");

            List<Integer> neighbors = adjList.get(node);
            if (neighbors != null) {
                for (Integer neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
    }
}