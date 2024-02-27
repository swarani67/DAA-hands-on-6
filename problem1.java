import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void RQS(int[] A, int s, int b) {
        if (s<b) {
            int n=partition_of_RQS(A, s, b);
            RQS(A, s, n-1);
            RQS(A, n+1, b);
        }
    }
    public static int partition_of_RQS(int[] A, int s, int b) {
        int idx=ThreadLocalRandom.current().nextInt(s, b+1);
        int temp=A[idx];
        A[idx]=A[b];
        A[b]=temp;
        return partition_of_NR(A, s, b);
    }
    public static void N_RQS(int[] A, int s, int b) {
        if (s<b) {
            int n=partition_of_NR(A, s, b);
            N_RQS(A, s, n-1);
            N_RQS(A, n+1, b);
        }
    }
    public static int partition_of_NR(int[] A, int s, int b) {
        int p=A[b];
        int k=s-1;
        for (int l=s;l<b;l++) {
            if (A[l]<p) {
                k++;
                int temp=A[k];
                A[k]=A[l];
                A[l]=temp;
            }
        }
        int temp=A[k + 1];
        A[k+1]=A[b];
        A[b]=temp;
        return k+1;
    }
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Array size:");
        int len=scanner.nextInt();
        int[] A_1=new int[len];
        int[] A_2=new int[len];
        System.out.println("Elements in the array:");
        for (int k=0;k<len;k++) {
            A_1[k]=scanner.nextInt();
        }
        System.arraycopy(A_1, 0, A_2, 0, len);
        System.out.println("Array of unsorted nonrandom pivot " + Arrays.toString(A_1));
        N_RQS(A_1, 0, A_1.length - 1);
        System.out.println("Sorted array using non-random pivot element: " + Arrays.toString(A_1));
        System.out.println("\nUnsorted array of random pivot: " + Arrays.toString(A_2));
        RQS(A_2, 0, A_2.length - 1);
        System.out.println("Sorted array using random pivot element: " + Arrays.toString(A_2));
        scanner.close();
    }
}
