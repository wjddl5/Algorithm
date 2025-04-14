import java.io.*;
import java.util.*;

public class Main {
    static int[] ar = new int[1000001];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ar[1] = 0;
        ar[2] = ar[3] = 1;
        find(n);
        System.out.println(ar[n]);
    }

    static void find(int n) {
        for(int i=4; i<=n; i++) {
            int cnt = Integer.MAX_VALUE;
            if(i%3 == 0) {
                if(cnt > ar[i/3] +1) cnt = ar[i/3] +1; 
            }
            if(i%2 == 0)  {
                if(cnt > ar[i/2] +1) cnt = ar[i/2] +1;
            }
            if(cnt > ar[i-1] +1) cnt = ar[i-1] +1;
            ar[i] = cnt;
        }
    }
}