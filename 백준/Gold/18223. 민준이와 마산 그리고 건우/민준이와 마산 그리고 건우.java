import java.io.*;
import java.util.*;

class Main {

    static class Node {
        int idx, cost;
        boolean chk;
        Node(int idx, int cost, boolean chk) {
            this.idx = idx;
            this.cost = cost;
            this.chk = chk;
        }
    }
    
    static boolean answer;
    static int N, P;
    static ArrayList<Node>[] graph;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        dist = new int[N+1];

        for(int i=1; i<=N; i++)
            graph[i] = new ArrayList<>();
        Arrays.fill(dist, Integer.MAX_VALUE);

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[u].add(new Node(v, c, false));
            graph[v].add(new Node(u, c, false));
        }

        find();
        System.out.println(answer ? "SAVE HIM" : "GOOD BYE");
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Node(1, 0, false));
        dist[1] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            int nodeCost = node.cost;
            if(node.idx == P) node.chk = true;
            if(node.idx == N && node.chk) answer = true;

            for(Node n : graph[node.idx]) {
                int nCost = nodeCost + n.cost;
                if(dist[n.idx] >= nCost) {
                    dist[n.idx] = nCost;
                    pq.offer(new Node(n.idx, nCost, node.chk));
                }
            }
        }
    }

}