import java.io.*;
import java.util.*;

public class Main {

    static final long INF = Long.MAX_VALUE;
    static int N;
    static ArrayList<Node>[] graph;
    static long[] dist;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        dist = new long[N];

        st = new StringTokenizer(br.readLine());
        
        boolean[] isBreak = new boolean[N];
        for(int i=0; i<N-1; i++) {
            if(st.nextToken().equals("1")) isBreak[i] = true;
        }

        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<Node>();
            dist[i] = INF;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(isBreak[u] || isBreak[v]) continue;

            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }
        //input

        find();

        System.out.println(dist[N-1] < INF ? dist[N-1] : -1);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {
                if(dist[n.idx] > dist[node.idx] + n.cost) {
                    dist[n.idx] = dist[node.idx] + n.cost;
                    pq.offer(new Node(n.idx, (int) dist[node.idx] + n.cost));
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