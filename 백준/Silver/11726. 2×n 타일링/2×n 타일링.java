import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] ar = new int[n+1];
        ar[0] = 1;
        ar[1] = 1;

        for(int i=2; i<=n; i++) {
            ar[i] = ar[i-1]%10007 + ar[i-2]%10007;
        }
        System.out.println(ar[n]%10007);
    }
}