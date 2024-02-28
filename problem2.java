import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import org.knowm.xchart.*;
public class Main {
      private static int[] bestcase(int len) {
        int[] A=new int[len];
        for (int k=0;k<len;k++) {
            A[k]=k+1;
        }
        return A;
    }
    private static int[] worstcase(int len) {
        int[] A = new int[len];
        for (int k=0;k<len;k++) {
            A[k]=len-k;
        }
        return A;
    }
    private static int[] averagecase(int len) {
        Random rand=new Random();
        int[] A=new int[len];
        for (int k=0;k<len;k++) {
            A[k] = rand.nextInt(len);
        }
        return A;
    }
    private static void N_RQS(int[] A, int s, int b) {
        if (s<b) {
            int pivotIndex = partition_of_NR(A, s, b);
            N_RQS(A, s, pivotIndex - 1);
            N_RQS(A, pivotIndex + 1, b);
        }
    }
    private static int partition_of_NR(int[] A, int s, int b) {
        int pivot=A[b];
        int k=s-1;
        for (int l=s;l<b;l++) {
            if (A[l]<pivot) {
                k++;
                swap(A, k, l);
            }
        }
        swap(A, k+1, b);
        return k+1;
    }
    private static void swap(int[] A, int k, int l) {
        int temp=A[k];
        A[k]=A[l];
        A[l] = temp;
    }
    public static void main(String[] args) {
        int[] A_S = {10, 100, 300, 400, 600};
        List<Integer> inputSizes = new ArrayList<>();
        List<Double> bestCaseTimes = new ArrayList<>();
        List<Double> worstCaseTimes = new ArrayList<>();
        List<Double> averageCaseTimes = new ArrayList<>();
        for (int length : A_S) {
            inputSizes.add(length);
            int[] bestCaseInput = bestcase(length);
            long startTime = System.nanoTime();
            N_RQS(bestCaseInput, 0, length - 1);
            long endTime = System.nanoTime();
            double bestCaseTime = (endTime - startTime) / 1e6;
            int[] worstCaseInput = worstcase(length);
            startTime = System.nanoTime();
            N_RQS(worstCaseInput, 0, length - 1);
            endTime = System.nanoTime();
            double worstCaseTime = (endTime - startTime) / 1e6;
            int[] averageCaseInput = averagecase(length);
            startTime = System.nanoTime();
            N_RQS(averageCaseInput, 0, length- 1);
            endTime = System.nanoTime();
            double averageCaseTime = (endTime - startTime) / 1e6;
            bestCaseTimes.add(bestCaseTime);
            worstCaseTimes.add(worstCaseTime);
            averageCaseTimes.add(averageCaseTime);
        }
        XYChart chart = new XYChartBuilder().width(600).height(400).title("Benchmarks").xAxisTitle("size of an array").yAxisTitle("runtime in ms").build();
        chart.addSeries("BESTCASE", inputSizes, bestCaseTimes);
        chart.addSeries("WORSTCASE", inputSizes, worstCaseTimes);
        chart.addSeries("AVERAGECASE", inputSizes, averageCaseTimes);
        new SwingWrapper<>(chart).displayChart();
    }
}
