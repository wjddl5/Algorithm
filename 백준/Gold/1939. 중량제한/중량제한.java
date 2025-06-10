import java.io.*;
import java.util.*;

public class Main {
    static final int INF=Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static int[] maxWeight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        maxWeight = new int[n+1];

        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, t));
            graph[v].add(new Node(u, t));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        maxWeight[start] = INF;

        find(start, end);
        System.out.println(maxWeight[end]);
    }

    static void find(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, INF));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int idx = node.idx;
            int cost = node.cost;

            if(maxWeight[idx] > cost) continue;

            for(Node n : graph[idx]) {
                int maxCost = Math.min(cost, n.cost);
                if(maxWeight[n.idx] < maxCost) {
                    maxWeight[n.idx] = maxCost;
                    queue.offer(new Node(n.idx, maxWeight[n.idx]));
                }
            }
        }
    }

}

class Node implements Comparable<Node>{
    int idx, cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
    @Override
    public int compareTo(Node o) {
        return o.cost - this.cost;
    }
}
