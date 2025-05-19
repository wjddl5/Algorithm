import java.io.*;
import java.util.*;

public class Main {
    static int V, total;
    static ArrayList<Edge>[] tree;
    static boolean[] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        tree = new ArrayList[V + 1];
        visit = new boolean[V + 1];
        for(int i=1; i<=V; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<w; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            tree[n1].add(new Edge(n2, cost));
            tree[n2].add(new Edge(n1, cost));
        }

        prim();
        System.out.println(total);
    }

    static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(1, 0));

        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.node;

            if(visit[node]) continue;

            visit[node] = true;
            total += edge.cost;

            for(Edge e : tree[node]) {
                if(!visit[e.node]) pq.offer(e);
            }
        }
    }

}

class Edge implements Comparable<Edge> {
    int node;
    int cost;
    Edge(int node, int cost) {
        this.node = node;
        this.cost = cost;
    }

    @Override
    public int compareTo(Edge o) {
        if(this.cost > o.cost) return 1;
        if(this.cost < o.cost) return -1;
        return 0;
    }

}