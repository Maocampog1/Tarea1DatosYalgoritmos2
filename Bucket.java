import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Bucket {
    
    // Método para realizar Bucket Sort
    public static void bucketSort(String[] arr) {
        if (arr.length == 0) {
            return;
        }
        
        // Determinar el número de buckets
        int numberOfBuckets = (int) Math.sqrt(arr.length);
        List<List<String>> buckets = new ArrayList<>(numberOfBuckets);
        
        // Inicializar los buckets
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets.add(new ArrayList<>());
        }

        // Distribuir las palabras en los buckets
        for (String word : arr) {
            int bucketIndex = (word.hashCode() & 0x7fffffff) % numberOfBuckets;
            buckets.get(bucketIndex).add(word);
        }

        // Ordenar individualmente cada bucket y concatenar los resultados
        int index = 0;
        for (List<String> bucket : buckets) {
            Collections.sort(bucket);
            for (String word : bucket) {
                arr[index++] = word;
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo .es: ");
        String fileName = scanner.nextLine();
        
        List<String> words = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                words.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return;
        }

        // Arreglos para almacenar los tamaños y tiempos
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000,
                       110000, 120000, 130000, 140000, 150000, 160000, 170000, 180000, 190000,
                       200000, 210000, 220000, 230000, 240000, 247047};
        long[] times = new long[sizes.length];
        
        // Realizar el ordenamiento y medir el tiempo
        for (int i = 0; i < sizes.length; i++) {
            String[] dataSet = words.subList(0, sizes[i]).toArray(new String[0]);
            long startTime = System.currentTimeMillis();
            bucketSort(dataSet);
            long endTime = System.currentTimeMillis();
            times[i] = endTime - startTime;
        }
        
        // Imprimir la tabla de resultados
        System.out.println("Número de datos | Tiempo Bucket Sort (ms)");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%-15d | %-15d\n", sizes[i], times[i]);
        }
        
        scanner.close();
    }
}
