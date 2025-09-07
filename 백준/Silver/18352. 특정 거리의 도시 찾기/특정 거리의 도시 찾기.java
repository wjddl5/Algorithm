import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        dist = new int[N+1];
        
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, 1));
        }

        find(start);

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=N; i++) {
            if(dist[i] == K) sb.append(i).append("\n");
        }
        System.out.println(sb.length() == 0 ? -1 : sb);
    }

    static void find(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.cost > K) break;

            for (Node n : graph[node.idx]) {
                if (dist[n.idx] > node.cost + n.cost) {
                    dist[n.idx] = node.cost + n.cost;
                    pq.offer(new Node(n.idx, node.cost + n.cost));
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