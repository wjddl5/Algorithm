import java.io.*;
import java.util.*;

public class Main {
    static Long[][] ar;
    static long mod = 1000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long total = 0;
        ar = new Long[N+1][10];

        for(int i=0; i<10; i++) {
            ar[1][i] = 1L;
        }
        for(int i=1; i<10; i++) {
            total += dp(N, i);
        }
        System.out.println(total % mod);
    }

    static long dp(int depth, int num) {
        if(depth == 1) return ar[1][num];   
        if(ar[depth][num] != null) return ar[depth][num] % mod;

        if(num == 0) ar[depth][0] = dp(depth-1, 1);    
        else if(num == 9) ar[depth][9] = dp(depth-1, 8);    
        else ar[depth][num] = dp(depth-1, num-1) + dp(depth-1, num+1);
        
        return ar[depth][num] % mod;
    }

}