import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("female_names.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        HashTable table1 = new HashFunction1();
        HashTable table2 = new HashFunction2();

        long start = System.nanoTime();
        for (String name : names) {
            table1.insert(name);
        }
        long end = System.nanoTime();
        table1.insertionTime = end - start;
        start = System.nanoTime();
        for (String name : names) {
            table2.insert(name);
        }
        end = System.nanoTime();
        table2.insertionTime = end - start;


        start = System.nanoTime();
        for (String name : names) {
            table1.search(name);
        }
        end = System.nanoTime();
        table1.searchTime = end - start;
        start = System.nanoTime();
        for (String name : names) {
            table2.search(name);
        }
        end = System.nanoTime();
        table2.searchTime = end - start;

        printReport("Hash de soma de caractéres simples:", table1);
        printReport("Hash Multiplicativo:", table2);
    }

    private static void printReport(String title, HashTable table) {
        System.out.println("\n" + title);
        System.out.println("Colisões: " + table.getCollisionCount());
        System.out.println("Tempo de inserção: " + String.format("%.3f", table.getInsertionTime() / 1_000_000.0) + " ms");
        System.out.println("Tempo de busca: " + String.format("%.3f", table.getSearchTime() / 1_000_000.0) + "  ms");
        System.out.println("Distribuição:");
        int[] dist = table.getDistribution();
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Posição " + i + ": " + dist[i] + " elementos");
        }
    }
}
