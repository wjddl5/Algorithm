import java.io.*;
import java.util.*;

public class Main {
    static final int INF=Integer.MAX_VALUE;
    static int V, E, start;
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static int[] costAr;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine()) -1;
        for(int i=0; i<V; i++) graph.add(new ArrayList<>());
        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int root = Integer.parseInt(st.nextToken()) -1;
            int node = Integer.parseInt(st.nextToken()) -1;
            int cost = Integer.parseInt(st.nextToken());
            graph.get(root).add(new Node(node, cost));
        }
        costAr = new int[V];
        Arrays.fill(costAr, INF);
        costAr[start] = 0;

        find();
        print();
    }

    static void find() {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.cost - o2.cost);
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.cost > costAr[node.idx]) continue;

            for(int i=0; i<graph.get(node.idx).size(); i++) {
                Node tmp = graph.get(node.idx).get(i);
                int next = tmp.idx;
                int nCost = tmp.cost;
                if(costAr[next] > costAr[node.idx] + nCost) {
                    costAr[next] = costAr[node.idx] + nCost;
                    queue.offer(new Node(next, costAr[next]));
                }
            }
        }
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for(int cost : costAr) {
            if(cost==INF) sb.append("INF").append("\n");
            else sb.append(cost).append("\n");
        }
        System.out.println(sb);
    }

}

class Node {
    int idx;
    int cost;
    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}
