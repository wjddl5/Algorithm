import java.io.*;
import java.util.*;

public class Main {

    static int N, K, INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static int[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1][K+2];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            graph[Integer.parseInt(st.nextToken())].add(
                new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
           sb.append(dist[i][K] < INF ? dist[i][K] : -1).append("\n");
        }
        System.out.println(sb);
    }

    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, 0));
        dist[1][1] = 0;       

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            
            for(Node n : graph[node.idx]) {
                int nCost = node.cost + n.cost;

                for(int i=1; i<=K; i++) {
                    if(dist[n.idx][i] > nCost) {

                        for(int j=K; j>i; j--) {
                            dist[n.idx][j] = dist[n.idx][j-1];
                        }

                        dist[n.idx][i] = nCost;
                        pq.offer(new Node(n.idx, nCost));
                        break;
                    }
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
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}