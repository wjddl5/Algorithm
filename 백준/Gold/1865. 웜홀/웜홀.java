import java.io.*;
import java.util.*;

public class Main {
    static int N, M, W, INF=Integer.MAX_VALUE;
    static int[] dist;
    static ArrayList<Node> graph;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb =  new StringBuilder();

        int T =  Integer.parseInt(br.readLine());
        while(T-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();
            dist = new int[N];

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) -1;
                int to = Integer.parseInt(st.nextToken()) -1;
                int cost = Integer.parseInt(st.nextToken());
                graph.add(new Node(from, to, cost));
                graph.add(new Node(to, from, cost));
            }
            
            for(int i=0; i<W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken()) -1;
                int to = Integer.parseInt(st.nextToken()) -1;
                int cost = Integer.parseInt(st.nextToken()) * -1;
                Node node = new Node(from, to, cost);
                graph.add(node);
            }
            
            sb.append(bellmanFord()).append("\n");
        }
        System.out.println(sb);                      
    }

    static String bellmanFord() {
        for(int s=0; s<N; s++) {
            Arrays.fill(dist, INF);
            dist[s] = 0;

            for(int i=0; i<N; i++) {
                boolean chk = false;
                for(Node node : graph) {
                    if(dist[node.from] == INF) continue;
                    if(dist[node.to] > dist[node.from] + node.cost){
                        dist[node.to] = dist[node.from] + node.cost;
                        chk = true;
                        if(i == N-1) return "YES";
                    } 
                }
                if(!chk && i<N-1) break;
            }
        }
        return "NO";
    }

}

class Node {
    int from;
    int to;
    int cost;

    Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}