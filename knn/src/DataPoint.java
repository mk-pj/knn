import java.util.Arrays;

public class DataPoint {

    public double[] x;

    public DataPoint(double[] x) {
        this.x = x;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "x=" + Arrays.toString(x) +
                '}';
    }
}
