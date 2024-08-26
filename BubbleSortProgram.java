import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BubbleSortProgram {
    
    // Método para realizar Bubble Sort
    public static void bubbleSort(String[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    // Intercambiar arr[j] y arr[j+1]
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    // Método principal
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo txt: ");
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
            bubbleSort(dataSet);
            long endTime = System.currentTimeMillis();
            times[i] = endTime - startTime;
        }
        
        // Imprimir la tabla de resultados
        System.out.println("Número de datos | Tiempo Sort Burbuja (ms)");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < sizes.length; i++) {
            System.out.printf("%-15d | %-15d\n", sizes[i], times[i]);
        }
        
        scanner.close();
    }
}
