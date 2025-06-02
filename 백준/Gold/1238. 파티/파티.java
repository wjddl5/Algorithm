import java.io.*;
import java.util.*;

public class Main {
    static final int INF=Integer.MAX_VALUE;
    static int N, X, max;
    static int[][] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) -1;

        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }

        while(m-- > 0) {
            st  = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) -1;
            int to = Integer.parseInt(st.nextToken()) -1;
            int cost = Integer.parseInt(st.nextToken());

            graph[from][to] = cost;
        }

        find();
        System.out.println(max);
    }

    static void find() {
        for(int k=0; k<N; k++) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(graph[i][k] == INF || graph[k][j] == INF) continue;
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);             
                }
            }
        }

        for(int i=0; i<N; i++) {
            if(i == X) continue;
            max = Math.max(max, graph[i][X] + graph[X][i]);
        }
    }

}
