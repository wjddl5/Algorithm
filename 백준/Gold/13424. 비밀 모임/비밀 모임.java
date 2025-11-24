import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Node>[] graph;
    static int[] distSum;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N+1];
            distSum = new int[N+1];
            
            for(int i=1; i<=N; i++) {
                graph[i] = new ArrayList<>();
            }

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                graph[u].add(new Node(v, c));
                graph[v].add(new Node(u, c));
            }

            int f = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<f; i++) {
                int start = Integer.parseInt(st.nextToken());
                find(start);
            }

            int min = Integer.MAX_VALUE;
            int ans = 1;
            for(int i=1; i<=N; i++) {
                if(min > distSum[i]) {
                    min = distSum[i];
                    ans = i;
                }
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static void find(int start) {
        Queue<Node> queue = new ArrayDeque<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        queue.offer(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            for(Node n : graph[node.idx]) {
                if(dist[n.idx] > node.cost + n.cost) {
                    dist[n.idx] = node.cost + n.cost;
                    queue.offer(new Node(n.idx, node.cost + n.cost));
                }
            }
        }

        for(int i=1; i<=N; i++) {
            distSum[i] += dist[i];
        }
    }

}

class Node {
    int idx, cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}