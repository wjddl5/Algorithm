import java.io.*;
import java.util.*;

public class Main {

    static int N, M, answer;
    static ArrayList<Node>[] graph;
    static int[] items, dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        items = new int[N+1];
        dist = new int[N+1];

        st = new StringTokenizer(br.readLine());

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            items[i] = Integer.parseInt(st.nextToken());
        }

        while(r-- > 0) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[n1].add(new Node(n2, cost));
            graph[n2].add(new Node(n1, cost));
        }

        for(int i=1; i<=N; i++) {
            find(i);
        }

        System.out.println(answer);
    }

    static void find(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Arrays.fill(dist, 100_000_000);

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(dist[node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {

                if(dist[n.idx] > node.cost + n.cost && node.cost + n.cost <= M) {
                    pq.offer(new Node(n.idx, node.cost + n.cost));
                    dist[n.idx] = node.cost + n.cost;
                }
            }
        }

        int cnt = 0;
        for(int i=1; i<=N; i++) {
            if(dist[i] < 100_000_000) {
                cnt += items[i];
            }
        }
        answer = Math.max(answer, cnt);
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