import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }

        StringBuilder sb = new StringBuilder();
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            
            int res = find(s, e);
            sb.append(res).append("\n");
        }

        System.out.println(sb);
    }

    static int find (int s, int e) {
        int res = 0;

        Queue<Node> q = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];

        q.offer(new Node(s, 0));
        v[s] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();

            if(node.idx == e) {
                res = node.cost;
                break;
            }

            for(Node n : graph[node.idx]) {
                if(v[n.idx]) continue;

                q.offer(new Node(n.idx, node.cost + n.cost));
                v[n.idx] = true;
            }
        }

        return res;
    }

}

class Node {
    int idx, cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}