import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] ar;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        ar = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(i==j) ar[i][j] = 0;
                else ar[i][j] = 10000001;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            if(ar[from][to] > value) ar[from][to] = value;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    ar[i][j] = Math.min(ar[i][j], ar[i][k] + ar[k][j]);
                }
            }
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                if(ar[i][j] == 10000001) sb.append(0 + " ");
                else sb.append(ar[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
