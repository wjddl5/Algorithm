import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static ArrayList<Node>[] graph;
    static long[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new long[N+1][K+1];
 
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], 100_000_000_000L);
        }
        Arrays.fill(dist[1], 0);

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w, K));
            graph[v].add(new Node(u, w, K));
        }

        find();

        long min = 100_000_000_000L;
        for(long l : dist[N]) {
            if(min > l) min = l;
        }
        System.out.println(min);
    }

    static void find() {
        boolean[][] visit = new boolean[N+1][K+1];
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, K));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(visit[node.idx][node.cnt]) continue;
            visit[node.idx][node.cnt] = true;

            for(Node n : graph[node.idx]) {

                if(node.cnt > 0) {
                    if(dist[n.idx][node.cnt-1] > node.cost) {
                        dist[n.idx][node.cnt-1] = node.cost;
                        queue.offer(new Node(n.idx, dist[n.idx][node.cnt-1], node.cnt-1));
                    }
                }

                if(dist[n.idx][node.cnt] > node.cost + n.cost) {
                    dist[n.idx][node.cnt] = node.cost + n.cost;
                    queue.offer(new Node(n.idx, dist[n.idx][node.cnt], node.cnt));
                }   
            }
        }
    }
    
}

class Node implements Comparable<Node> {
    int idx, cnt;
    long cost;
    Node(int idx, long cost, int cnt) {
        this.idx = idx;
        this.cost = cost;
        this.cnt = cnt;
    }
    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}
