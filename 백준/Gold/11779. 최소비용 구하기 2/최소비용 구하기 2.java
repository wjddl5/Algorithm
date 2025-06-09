import java.io.*;
import java.util.*;

public class Main {
    static Node endNode;
    static int N, start, end, count, INF=Integer.MAX_VALUE;
    static ArrayList<ArrayList<Node>> graph;
    static int[] dist;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;     

        N = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        
        graph = new ArrayList<>();
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }

        dist = new int[N+1];
        Arrays.fill(dist, INF);

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            Node node = new Node(null, to, cost, 1);
            graph.get(from).add(node);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        dijkstra();
        sb.append(dist[end]).append("\n");    
        sb.append(count).append("\n");     
        getOrder();
        System.out.println(sb);
    }

    static void getOrder() {
        Node node = endNode;
        ArrayList<Node> list = new ArrayList<>();
        while(node != null) {
            list.add(node);
            node = node.next;
        }

        for(int i=list.size()-1; i>=0; i--) {
            int idx = list.get(i).idx;
            sb.append(idx).append(" ");
        }
    }

    static void dijkstra() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        boolean[] visit = new boolean[N+1];

        queue.offer(new Node(null, start, 0, 1));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node now = queue.poll();
            
            if(visit[now.idx]) continue;
            
            visit[now.idx] = true;

            if(now.idx == end) {
                count = now.cnt;
                endNode = now;
            }

            for(Node next : graph.get(now.idx)) {
                if(dist[next.idx] <= now.cost + next.cost) continue;
                dist[next.idx] = now.cost + next.cost;
                queue.offer(new Node(now, next.idx, dist[next.idx], now.cnt+1));
            }
        }
    }

}

class Node {
    Node next;
    int idx, cost, cnt;
    Node(Node next, int idx, int cost, int cnt) {
        this.next = next;
        this.idx = idx;
        this.cost = cost;
        this.cnt = cnt;
    }
}
