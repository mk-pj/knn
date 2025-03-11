import java.util.Map;
import java.util.Scanner;

public class ReadData {

    private final Scanner sc;

    public ReadData(Scanner sc) {
        this.sc = sc;
    }

    private DataPoint processLine(String line) {
        String[] col = line.split(",");
        double[] values = new double[col.length - 1];
        for(int i = 0; i < values.length; i++)
            values[i] = Double.parseDouble(col[i]);
        return new DataPoint(values);
    }

    public Map<Kl, Data> readData() {
        int rowCounter = 0;
        Data setosa = new Data();
        Data versicolot = new Data();
        Data virginica = new Data();
        sc.nextLine();
        while(this.sc.hasNext()) {
            DataPoint point = processLine(sc.nextLine());
            if(rowCounter < 50) {
                point.dataClass = Kl.SETOSA;
                if(rowCounter < 40) {
                    setosa.addTraining(point);
                } else {
                    setosa.addTesting(point);
                }
            } else if(rowCounter < 100) {
                point.dataClass = Kl.VERSICOLOR;
                if(rowCounter < 90) {
                    versicolot.addTraining(point);
                } else {
                    versicolot.addTesting(point);
                }
            } else {
                point.dataClass = Kl.VIRGINICA;
                if(rowCounter < 140) {
                    virginica.addTraining(point);
                } else {
                    virginica.addTesting(point);
                }
            }
            rowCounter++;
        }
        return Map.of(
            Kl.SETOSA, setosa,
            Kl.VERSICOLOR, versicolot,
            Kl.VIRGINICA, virginica
        );
    }


    public Map<Kl, Data> readData(int numberOfTesting) {
        int rowCounter = 0;
        Data setosa = new Data();
        Data versicolot = new Data();
        Data virginica = new Data();
        sc.nextLine();
        int setosaCutOff = 50 - numberOfTesting;
        int versicolotCutOff = 100 - numberOfTesting;
        int virginicaCutOff = 150 - numberOfTesting;
        while(this.sc.hasNext()) {
            DataPoint point = processLine(sc.nextLine());
            if(rowCounter < 50) {
                point.dataClass = Kl.SETOSA;
                if(rowCounter < setosaCutOff) {
                    setosa.addTraining(point);
                } else {
                    setosa.addTesting(point);
                }
            } else if(rowCounter < 100) {
                point.dataClass = Kl.VERSICOLOR;
                if(rowCounter < versicolotCutOff) {
                    versicolot.addTraining(point);
                } else {
                    versicolot.addTesting(point);
                }
            } else {
                point.dataClass = Kl.VIRGINICA;
                if(rowCounter < virginicaCutOff) {
                    virginica.addTraining(point);
                } else {
                    virginica.addTesting(point);
                }
            }
            rowCounter++;
        }
        return Map.of(
                Kl.SETOSA, setosa,
                Kl.VERSICOLOR, versicolot,
                Kl.VIRGINICA, virginica
        );
    }
}
