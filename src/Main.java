import algorithms.divideandconquer.*;
import algorithms.greedy.*;

import java.awt.*;
import java.util.Scanner;
import java.util.Arrays;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Select an algorithm to test:");
            System.out.println("1. QuickSort");
            System.out.println("2. MergeSort");
            System.out.println("3. Closest Pair of Points");
            System.out.println("4. Strassen's Matrix Multiplication");
            System.out.println("5. Quickhull (Convex Hull)");
            System.out.println("6. Prim's MST");
            System.out.println("7. Kruskal's MST");
            System.out.println("8. Dijkstra's Shortest Path");
            System.out.println("9. Huffman Coding");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            if (choice == 0) break;

            long startTime = 0, endTime = 0;
            switch (choice) {
                case 1:
                    int[] array1 = inputArray();
                    startTime = System.nanoTime();
                    QuickSort.quickSort(array1, 0, array1.length - 1);
                    endTime = System.nanoTime();
                    System.out.println("Sorted Array: " + Arrays.toString(array1));
                    break;
                case 2:
                    int[] array2 = inputArray();
                    startTime = System.nanoTime();
                    MergeSort.mergeSort(array2, 0, array2.length - 1);
                    endTime = System.nanoTime();
                    System.out.println("Sorted Array: " + Arrays.toString(array2));
                    break;
                case 3:
                    ClosestPair closestPair = new ClosestPair();
                    closestPair.findClosestPair(inputPoints());
                    break;
                case 4:
                    int[][] matrix1 = inputMatrix("first");
                    int[][] matrix2 = inputMatrix("second");
                    startTime = System.nanoTime();
                    int[][] result = StrassenMatrixMultiplication.multiply(matrix1, matrix2);
                    endTime = System.nanoTime();
                    System.out.println("Resultant Matrix: ");
                    printMatrix(result);
                    break;
                case 5:
                    Quickhull quickhull = new Quickhull();
                    quickhull.quickHull(inputPoints());
                    break;
                case 6:
                    PrimMST primMST = new PrimsMST();
                    primMST.primMST(inputGraph());
                    break;
                case 7:
                    KruskalMST kruskalMST = new KruskalsMST(inputGraph(), inputSourceNode());
                    kruskalMST.kruskalMST();
                    break;
                case 8:
                    DijkstraSP dijkstraSP = new Di();
                    dijkstraSP.dijkstra(inputGraph(), inputSourceNode());
                    break;
                case 9:
                    HuffmanCoding huffmanCoding = new HuffmanCoding();
                    huffmanCoding.huffmanCoding(inputCharArray(), inputFrequencies());
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    continue;
            }

            // Display time taken for execution
            System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds.");
        }
    }

    private static int[] inputArray() {
        System.out.print("Enter array size: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Enter array elements:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }
        return array;
    }

    private static int[][] inputMatrix(String name) {
        System.out.print("Enter number of rows and columns for " + name + " matrix: ");
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        int[][] matrix = new int[rows][cols];
        System.out.println("Enter matrix elements:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }

    private static Point[] inputPoints() {
        System.out.print("Enter number of points: ");
        int n = scanner.nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            System.out.print("Enter x and y for point " + (i + 1) + ": ");
            int x = scanner.nextInt();
            int y = scanner.nextInt();
            points[i] = new Point(x, y);
        }
        return points;
    }

    private static int[][] inputGraph() {
        System.out.print("Enter number of vertices: ");
        int V = scanner.nextInt();
        int[][] graph = new int[V][V];
        System.out.println("Enter adjacency matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                graph[i][j] = scanner.nextInt();
            }
        }
        return graph;
    }

    private static int inputSourceNode() {
        System.out.print("Enter the source node: ");
        return scanner.nextInt();
    }

    private static char[] inputCharArray() {
        System.out.print("Enter the characters: ");
        return scanner.next().toCharArray();
    }

    private static int[] inputFrequencies() {
        System.out.print("Enter the frequencies: ");
        int n = scanner.nextInt();
        int[] freq = new int[n];
        for (int i = 0; i < n; i++) {
            freq[i] = scanner.nextInt();
        }
        return freq;
    }
}
