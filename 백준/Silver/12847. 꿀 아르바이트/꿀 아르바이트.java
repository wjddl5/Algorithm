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
        for(int i=1; i<=n-m; i++) {
            sum = sum + ar[i+m-1] - ar[i-1];
            if(answer < sum) answer = sum;
        }

        br.close();
        System.out.println(answer);
    }

}