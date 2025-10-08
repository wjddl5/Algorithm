import java.io.*;
import java.util.*;

public class Main {

    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        long answer = find();

        System.out.println(answer);
    }

    static long find() {
        long left = 1;
        long right = K;
        
        while(left < right) {
            long mid = (left + right) / 2;
            long count = 0;

            for(int i=1; i<=N; i++) {
                count += Math.min(mid / i, N);
            }

            if(count >= K) {
                right = mid;
            } else {
                left = mid + 1;
            }
            
        }

        return left;
    }
}