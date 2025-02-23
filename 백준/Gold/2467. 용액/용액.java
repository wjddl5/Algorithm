import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] ar;
    static int low, high, answerLow, answerHigh;
    static int sum = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        ar = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }

        twoPoint();
        System.out.println(ar[answerLow] + " " + ar[answerHigh]);
    }

    public static void twoPoint() {
        low = 0; high = N-1;

        while (low < high) {
            int newSum = ar[low] + ar[high];

            if (Math.abs(newSum) < Math.abs(sum)) {
                sum = newSum;
                answerLow = low;
                answerHigh = high;
            }

            if (newSum < 0) low++;
            else high--;
            
        }
    }
}
