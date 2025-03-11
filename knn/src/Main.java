import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ReadData readData = new ReadData(new Scanner(new File("iris.txt")));

            int testingSetSize = 10;

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter k value (1-" + String.valueOf(50 - testingSetSize) + "): ");
            int k = sc.nextInt();

            System.out.println("Last 10 rows per class selected:");
            KNN.launch(k, testingSetSize, readData::readData);

            System.out.println("\n=================================");
            System.out.println("=================================");
            System.out.println("=================================\n");

            System.out.println("Random test data selection:");
            readData.setSc(new Scanner(new File("iris.txt")));
            KNN.launch(k, testingSetSize, readData::readDataRandom);
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        }
    }
}
