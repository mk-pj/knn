import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class KNN {

    private static class PointDistance {

        double distance;
        DataPoint dataPoint;

        public PointDistance(double distance, DataPoint dataPoint) {
            this.distance = distance;
            this.dataPoint = dataPoint;
        }
    }

    private static double distance(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++)
            sum += Math.pow(a[i] - b[i], 2);
        return Math.sqrt(sum);
    }

    public static void classify(List<DataPoint> data, List<DataPoint> dataToClassify, int k) {
        for(DataPoint pointToClassify : dataToClassify) {
            List<PointDistance> distances = new ArrayList<>();
            for(DataPoint dataPoint : data)
                distances.add(new PointDistance(distance(pointToClassify.x, dataPoint.x), dataPoint));
            distances.sort(Comparator.comparingDouble(p -> p.distance));
            Map<Kl, Integer> count = new EnumMap<>(Kl.class);
            for(int i = 0; i < k; ++i) {
                PointDistance pointDistance = distances.get(i);
                switch (pointDistance.dataPoint.dataClass) {
                    case SETOSA -> count.merge(Kl.SETOSA, 1, Integer::sum);
                    case VERSICOLOR -> count.merge(Kl.VERSICOLOR, 1, Integer::sum);
                    case VIRGINICA -> count.merge(Kl.VIRGINICA, 1, Integer::sum);
                }
            }
            count.entrySet()
                 .stream()
                 .max(Map.Entry.comparingByValue())
                 .ifPresent(e -> pointToClassify.dataClass = e.getKey());
        }
    }

    public static double testResult(List<DataPoint> predictions, List<DataPoint> answers) {
        int rightAnswers = 0;
        for(int i = 0; i < predictions.size(); ++i) {
            System.out.println(predictions.get(i));
            System.out.println(answers.get(i));
            System.out.println("======================");
            if (predictions.get(i).dataClass == answers.get(i).dataClass)
                rightAnswers++;
        }
        return rightAnswers / (double) predictions.size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File("iris.txt"));
        ReadData readData = new ReadData(sc);
        Map<Kl, Data> inputData = readData.readData(30);

        inputData.forEach((key, value) -> {
            System.out.println(key);
            System.out.println("train: " + value.getTraining().size());
            System.out.println("testing: " + value.getTesting().size());
        });

        List<DataPoint> dataToClassify = new ArrayList<>();
        List<DataPoint> data = new ArrayList<>();
        List<DataPoint> testAnswers = new ArrayList<>();

        for (Kl kl : new Kl[]{Kl.SETOSA, Kl.VERSICOLOR, Kl.VIRGINICA}) {
            Data val = inputData.get(kl);

            data.addAll(val.getTraining());
            testAnswers.addAll(val.getTesting());

            List<DataPoint> test = val.getTesting()
                    .stream()
                    .map(dp -> new DataPoint(dp.x, Kl.UNDETERMINED))
                    .toList();
            dataToClassify.addAll(test);
        }

        for(int i = 0; i < dataToClassify.size(); ++i) {
            System.out.println(dataToClassify.get(i));
            System.out.println(testAnswers.get(i));
            System.out.println("======================");
        }

        System.out.println("======================");
        System.out.println("======================");

        classify(data, dataToClassify, 1);

        System.out.println(testResult(dataToClassify, testAnswers));
    }
}
