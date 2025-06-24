import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<Node>[] graph;
    static int[] dist, path;
    static int N, max;
    static final int INF=Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];
        path = new int[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = INF;
        }

        while(m-- >0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, c));
            graph[v].add(new Node(u, c));
        }

        getMin();
        for(int i=1; i<=N; i++) {
            block(i);
            if(max == INF) break; 
        }
        System.out.println(max == INF ? -1 : max - dist[N]);
    }

    static void getMin() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0));
        dist[1] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(dist[node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {
                if(dist[n.idx] > node.cost + n.cost) {
                    dist[n.idx] = node.cost + n.cost;
                    path[n.idx] = node.idx;
                    queue.offer(new Node(n.idx, node.cost + n.cost));
                }
            }
        }
    }

    static void block(int num) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] dis = new int[N+1];
        queue.offer(new Node(1, 0));
        for(int i=2; i<=N; i++) {
            dis[i] = INF;
        }

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(dis[node.idx] < node.cost) continue;

            for(Node n :graph[node.idx]) {
                if(node.idx == num && n.idx == path[num] ||
                node.idx == path[num] && n.idx == num) continue;

                if(dis[n.idx] > node.cost + n.cost) {
                    dis[n.idx] = node.cost + n.cost;
                    queue.offer(new Node(n.idx, node.cost + n.cost));
                }
            }
        }
        max = Math.max(max, dis[N]);
    }

}

class Node implements Comparable<Node>{
    int idx, cost;
    public Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}