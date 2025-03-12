import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        int m  = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] ar = new int[n];
        for(int i=0; i<n; i++) {
            ar[i] = Integer.parseInt(st.nextToken());
        }
        
        long answer = 0;
        long sum = 0;
        for(int i=0; i<m; i++) {
            answer += ar[i];
        }

        sum = answer;
        int idx = 0;

        for(int i=m; i<n; i++) {
            sum = sum + ar[i] - ar[idx++];
            if(answer < sum) answer = sum;
        }

        System.out.println(answer);
    }

}