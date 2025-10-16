import java.io.*;
import java.util.*;

public class Main {

    static int N, minIdx;
    static long[] dist;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        minIdx = N;
        graph = new ArrayList[N+1];
        dist = new long[N+1];

        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = Long.MAX_VALUE;
        }

    
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            graph[v].add(new Node(u, c));
        }

        st = new StringTokenizer(br.readLine());
        while(k-- > 0) {
            int start = Integer.parseInt(st.nextToken());
            find(start);
        }



        for(int i=N; i>0; i--) {
            if(dist[minIdx] <= dist[i]) {
                minIdx = i;
            }
        }

        System.out.println(minIdx);
        System.out.println(dist[minIdx]);
    }

    static void find(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.cost > dist[node.idx]) continue;

            for(Node n : graph[node.idx]) {
                long nCost = node.cost + n.cost;
                if(dist[n.idx] > nCost) {
                    dist[n.idx] = nCost;
                    pq.offer(new Node(n.idx, nCost));
                }
            }
        }
    }


}

class Node implements Comparable<Node> {
    int idx;
    long cost;
    Node(int idx, long cost) {
        this.idx = idx;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node o) {
        return Long.compare(this.cost, o.cost);
    }
}