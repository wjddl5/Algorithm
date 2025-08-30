import java.io.*;
import java.util.*;

public class Main {

    static int K, N;
    static long maxLength;
    static int[] lines;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        long sum = 0;
        lines = new int[K];

        for(int i=0; i<K; i++) {
            int n = Integer.parseInt(br.readLine());
            lines[i] = n;
            sum += n;
        }

        binarySearch(1L, (long) sum / N);

        System.out.println(maxLength);
    }

    static void binarySearch(long left, long right) {
        while(left <= right) {   
            long mid = (left + right) / 2;
            if(isCount(mid)) {
                maxLength = Math.max(maxLength, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    static boolean isCount(long len) {
        int cnt = 0;
        for(int line : lines) {
            cnt += line / len;
        }
        return cnt >= N;
    }
}