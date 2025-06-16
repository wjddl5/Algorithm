import java.io.*;
import java.util.*;

public class Main {
    static final int INF = Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static float[] fox;
    static float[][] wolf;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n+1];
        fox = new float[n+1];
        wolf = new float[2][n+1];

        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
            fox[i] = INF;
            wolf[0][i] = INF;
            wolf[1][i] = INF;
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, c, -1));
            graph[v].add(new Node(u, c, -1));
        }

        getFox();
        getWolf();
        System.out.println(getCount(n));
    }

    static void getFox() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, -1));
        fox[1] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(fox[node.idx] < node.cost) continue;
            
            for(Node n : graph[node.idx]) {
                if(fox[n.idx] > node.cost + n.cost) {
                    fox[n.idx] = node.cost + n.cost;
                    queue.offer(new Node(n.idx, fox[n.idx], -1));
                }
            }
        }
    }

    static void getWolf() {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(1, 0, 1));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(wolf[(node.isHalf+1) % 2][node.idx] < node.cost) continue;

            for(Node n : graph[node.idx]) {

                if(node.isHalf == 1) {
                    if(wolf[1][n.idx] > node.cost + (n.cost/2)) {
                        wolf[1][n.idx] = node.cost + (n.cost/2);
                        queue.offer(new Node(n.idx, wolf[1][n.idx], 0));
                    }
                } else {
                    if(wolf[0][n.idx] > node.cost + (n.cost*2)) {
                        wolf[0][n.idx] = node.cost + (n.cost*2);
                        queue.offer(new Node(n.idx, wolf[0][n.idx], 1));
                    }
                }  
            }
        }
    }

    static int getCount(int n) {
        int cnt = 0;
        for(int i=2; i<=n; i++) {
            if(fox[i] < wolf[0][i] && fox[i] < wolf[1][i]) cnt++;
        }
        return cnt;
    }
}

class Node implements Comparable<Node>{
    int idx, isHalf;
    float cost;
    Node(int idx, float cost, int isHalf) {
        this.idx = idx;
        this.cost = cost;
        this.isHalf = isHalf;
    }
    @Override
    public int compareTo(Node o) {
        return Float.compare(this.cost, o.cost);
    }
}
