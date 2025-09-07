import java.io.*;
import java.util.*;

public class Main {

    static int N, T1, T2, INF = 500_000_000;
    static ArrayList<Node>[] graph;
    static int[][] dist;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        dist = new int[N+1][4];
        
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], INF);
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, cost, 0));
            graph[to].add(new Node(from, cost, 0));
        }

        st = new StringTokenizer(br.readLine());
        T1 = Integer.parseInt(st.nextToken());
        T2 = Integer.parseInt(st.nextToken());

        find();
        System.out.println(dist[N][3] < INF ? dist[N][3] : -1);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));

        int chk = 0;
        if (1 == T1) chk |= 1;

        dist[1][chk] = 0;
        pq.offer(new Node(1, 0, chk));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (node.cost > dist[node.idx][node.chk]) continue;
            if (node.idx == N && node.chk == 3) break;

            for (Node n : graph[node.idx]) {

                int nchk = node.chk;
                if (n.idx == T1) nchk |= 1;
                if (n.idx == T2) nchk |= 2;

                if (dist[n.idx][nchk] > node.cost + n.cost) {
                    dist[n.idx][nchk] = node.cost + n.cost;
                    pq.offer(new Node(n.idx, node.cost + n.cost, nchk));
                }
            }
        }
    }

}

class Node implements Comparable<Node>{
    int idx;
    int cost;
    int chk;
    public Node(int idx, int cost, int chk) {
        this.idx = idx;
        this.cost = cost;
        this.chk = chk;
    }
    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}