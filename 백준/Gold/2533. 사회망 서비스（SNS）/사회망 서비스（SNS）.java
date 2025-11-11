import java.io.*;
import java.util.*;

public class Main {

    static int N, INF = Integer.MAX_VALUE;
    static ArrayList<Integer>[] graph;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        dp = new int[N+1][2];
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }


        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u].add(v);
            graph[v].add(u);
        }

        int root = getRoot();

        dfs(root, -1);

        System.out.println(Math.min(dp[root][0], dp[root][1]));
    }

    static void dfs(int node, int parent) {
        dp[node][0] = 0; // 논 얼리
        dp[node][1] = 1; // 얼리

        for(int child : graph[node]) {
            if(child == parent) continue;
            dfs(child, node);
       
            dp[node][0] += dp[child][1]; // 논 얼리 -> child 얼리
            dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 얼리 -> child 자유 
        }
    }

    static int getRoot() {
        boolean[] v = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            for(int n : graph[i]) {
                v[n] = true;
            } 
        }

        for(int i=1; i<=N; i++) {
            if(v[i]) continue;
            return i;
        }
        return 1;
    }
}