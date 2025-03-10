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
        Data setosa = new Data(Kl.SETOSA);
        Data versicolot = new Data(Kl.VERSICOLOR);
        Data virginica = new Data(Kl.VIRGINICA);
        sc.nextLine();
        while(this.sc.hasNext()) {
            DataPoint point = processLine(sc.nextLine());
            if(rowCounter < 50) {
                if(rowCounter < 40) {
                    setosa.addTraining(point);
                } else {
                    setosa.addTesting(point);
                }
            } else if(rowCounter < 100) {
                if(rowCounter < 90) {
                    versicolot.addTraining(point);
                } else {
                    versicolot.addTesting(point);
                }
            } else {
                if(rowCounter < 140) {
                    virginica.addTraining(point);
                } else {
                    virginica.addTesting(point);
                }
            }
            rowCounter++;
        }
        return Map.of(Kl.SETOSA, setosa, Kl.VERSICOLOR, versicolot, Kl.VIRGINICA, virginica);
    }
}
