import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, cost));
            graph[n2].add(new Node(n1, cost));
        }

        find();

        System.out.println(dist[N]);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(1, 0));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {

                if(dist[n.idx] > node.cost + n.cost) {
                    pq.offer(new Node(n.idx, node.cost + n.cost));
                    dist[n.idx] = node.cost + n.cost;
                }
            }
        }
    }

 
}

class Node implements Comparable<Node>{
    int idx;
    int cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;

    }
    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}