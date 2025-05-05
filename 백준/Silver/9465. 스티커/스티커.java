import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] ar, memo;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            ar = new int[2][N];
            memo = new int[2][N];
            for(int i=0; i<2; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    ar[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dp();
        }
        System.out.println(sb);
    }

    static void dp() {
        memo[0][0] = ar[0][0];
        memo[1][0] = ar[1][0];

        if(N > 1) {
            memo[0][1] = memo[1][0] + ar[0][1];
            memo[1][1] = memo[0][0] + ar[1][1];
        }

        for(int i=2; i<N; i++) {
            memo[0][i] = Math.max( memo[1][i-1], memo[1][i-2] ) + ar[0][i];
            memo[1][i] = Math.max( memo[0][i-1], memo[0][i-2] ) + ar[1][i];
        }

        sb.append(Math.max(memo[0][N-1], memo[1][N-1])+"\n");
    }

}