import java.io.*;
import java.util.*;

public class Main {

    static int N, INF = 1_000_000_000;
    static int[][] dist, next;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        dist = new int[N+1][N+1];
        next = new int[N+1][N+1];

        for(int i=1; i<=N; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
            for(int j=1; j<=N; j++) {
                next[i][j] = j;
            }
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[n1][n2] = cost;
            dist[n2][n1] = cost;
        }

        floyd();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=N; j++) {
                sb.append(i == j ? "-" : next[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void floyd() {  
        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
    }
 
}