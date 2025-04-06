import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ar = new int[n+1];
        int[] maxAr = new int[n+1];
        for(int i=1; i<=n; i++) {
            ar[i] = Integer.parseInt(br.readLine());
        }
        if(n > 0) maxAr[1] = ar[1];
        if(n > 1) maxAr[2] = ar[1] + ar[2];
        if(n > 2) maxAr[3] = Math.max(ar[1], ar[2]) + ar[3];

        for(int i=4; i<=n; i++) {
            int r1 = ar[i] + maxAr[i-2];
            int r2 = ar[i] + ar[i-1] + maxAr[i-3];
            maxAr[i] = Math.max(r1, r2);
        }
        System.out.println(maxAr[n]);
    }
        
}