import java.io.*;
import java.util.*;

public class Main {
    static int[] ar1 = new int[41];
    static int[] ar2 = new int[41];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(); 
        int T = Integer.parseInt(br.readLine());

        ar1[0] = 1;
        ar1[1] = 0;
        ar2[0] = 0;
        ar2[1] = 1;

        for(int tc=0; tc<T; tc++) {
            int n = Integer.parseInt(br.readLine());
            fibo(n);
            sb.append(ar1[n] + " " + ar2[n] + "\n");
        }
        System.out.println(sb);  
    }

    static void fibo(int n) {
        for(int i=2; i<=n; i++) {
            ar1[i] = ar1[i-1] + ar1[i-2];
            ar2[i] = ar2[i-1] + ar2[i-2];
        }
    }
}