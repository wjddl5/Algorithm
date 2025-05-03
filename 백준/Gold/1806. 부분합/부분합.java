import java.io.*;
import java.util.*;

public class Main {
    static int N, S, minLength=100_001;
    static int[] ar;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());   
        ar = new int[N];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        
        find();
        System.out.println(minLength == 100_001 ?  0 : minLength);
    }

    static void find() {
        int left = 0, right = 0;
        int sum = ar[0];
        while(left < N) {
            if(sum >= S) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= ar[left];
                left ++;
                continue;
            }
            else if(sum < S) {
                right ++;
            }
            if(right < N) sum += ar[right];
            else break;
        }
    }

}