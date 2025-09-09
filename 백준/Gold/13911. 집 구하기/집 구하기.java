import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 1_000_000_000;
    static int N, answer = INF;
    static ArrayList<Node>[] graph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        for(int i=1; i<=N; i++) 
            graph[i] = new ArrayList<>();       
        
        while(e-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            graph[u].add(new Node(v, cost));
            graph[v].add(new Node(u, cost));
        }


        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        int[] mcdonald = new int[n];
     
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++)
            mcdonald[i] = Integer.parseInt(st.nextToken());

        int[] dist_1 = find(mcdonald, limit);


        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        int[] starbucks = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) 
            starbucks[i] = Integer.parseInt(st.nextToken());
        
        int[] dist_2 = find(starbucks, limit);

        for(int i=1; i<=N; i++) {
            if(dist_1[i] == 0 || dist_2[i] == 0) continue;
            answer = Math.min(answer, dist_1[i] + dist_2[i]);
        }
        
        System.out.println(answer < INF ? answer : -1);
    }

    static int[] find(int[] store, int limit) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, INF);

        for(int n : store) {      
            pq.offer(new Node(n, 0));
            dist[n] = 0;
        }

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.cost > limit) continue;

            for(Node nNode : graph[node.idx]) {
                int nCost = node.cost + nNode.cost;

                if(dist[nNode.idx] > nCost && nCost <= limit) {
                    dist[nNode.idx] = nCost;
                    pq.offer(new Node(nNode.idx, nCost));
                }
            }
        }
        
        return dist;
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