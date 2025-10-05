import java.io.*;
import java.util.*;

public class Main {

    static int N, resCount;
    static ArrayList<Node>[] graph;
    static int[] dist, res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        res = new int[N+1];
        dist = new int[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<Node>();
            dist[i] = Integer.MAX_VALUE;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }
        //input

        find();

        // answer
        sb.append(N-1).append("\n");
        for(int i=2; i<=N; i++) {
            sb.append(i).append(" ").append(res[i]).append("\n"); 
        }

        System.out.println(sb);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {
                if(dist[n.idx] > dist[node.idx] + n.cost) {
                    dist[n.idx] = dist[node.idx] + n.cost;
                    pq.offer(new Node(n.idx, dist[node.idx] + n.cost));
                    res[n.idx] = node.idx;
                }
            }
        }

    }


}

class Node implements Comparable<Node>{
    int idx;
    int cost;

    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }   

    @Override
    public int compareTo(Node o1) {
        return Integer.compare(this.cost, o1.cost);
    }
}
