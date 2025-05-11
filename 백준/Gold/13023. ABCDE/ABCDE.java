import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] graph;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        visit = new boolean[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            graph[n1].add(n2);
            graph[n2].add(n1);
        }
        
        for(int i=0; i<N; i++) {
            Arrays.fill(visit, false);
            dfs(i, 1);
        }
        System.out.println(0);
    }

    static void dfs(int node, int depth){
        if(depth == 5) {
            System.out.println(1);
            System.exit(0);
        }
        visit[node] = true;
        List<Integer> list = graph[node];
        for(int n : list) {
            if(!visit[n]) dfs(n, depth + 1);
        }
        visit[node] = false;
        
    }

}