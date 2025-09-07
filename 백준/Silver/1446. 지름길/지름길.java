import java.io.*;
import java.util.*;

public class Main {

    static int D;
    static ArrayList<Node>[] road;
    static int[] dist;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        road = new ArrayList[D+1];
        dist = new int[D+1];

        for(int i=0; i<=D; i++) {
            road[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            
            if(from > D || to > D) continue;
            road[from].add(new Node(to, cost)); 
        }

        find();
        System.out.println(dist[D]);
    }

    static void find() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.offer(new Node(0, 0));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            Node node = pq.poll();
            if(node.idx >= D) break;
            
            if(dist[node.idx + 1] > node.cost + 1) {
                dist[node.idx + 1] = node.cost + 1;
                pq.offer(new Node(node.idx + 1, node.cost + 1));
            }
            

            for(Node n : road[node.idx]) {
                if(n.idx > D) continue;

                if(dist[n.idx] > node.cost + n.cost) {
                    dist[n.idx] = node.cost + n.cost;
                    pq.offer(new Node(n.idx, (node.cost + n.cost)));
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