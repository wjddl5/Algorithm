import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        graph = new ArrayList[n+1];
        dist = new int[n+1];
        
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Node node = new Node(to, cost);
            graph[from].add(node);
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        find(start, end);
        System.out.println(dist[end]);
    }

    static void find(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.idx == end) return;

            for(Node n : graph[node.idx]) {
                if(dist[n.idx] > node.cost + n.cost) {
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