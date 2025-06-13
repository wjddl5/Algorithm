import java.io.*;
import java.util.*;

public class Main {
    static final int INF=100_000_000;
    static ArrayList<Node>[] graph;
    static int[][] dist;
    static HashSet<Integer> target;
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 노드
            int m = Integer.parseInt(st.nextToken()); // 간선
            int t = Integer.parseInt(st.nextToken()); // 목적지

            graph = new ArrayList[n+1];
            dist = new int[n+1][2];
            target = new HashSet<>();

            for(int i=1; i<=n; i++) {
                graph[i] = new ArrayList<>();
                dist[i][0] = INF; // 필수 통과 x
                dist[i][1] = INF; // 필수 통과 o
            }

            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작
            int g = Integer.parseInt(st.nextToken()); // 필수1
            int h = Integer.parseInt(st.nextToken()); // 필수2

            dist[s][0] = 0;
       
            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int k = Integer.parseInt(st.nextToken());

                graph[u].add(new Node(v, k, false));
                graph[v].add(new Node(u, k, false));
            }

            while(t-- > 0) {
                target.add(Integer.parseInt(br.readLine()));
            }

            find(s, g, h);
            findSuccess();   
        }
        System.out.println(sb);
    }

    static void find(int start, int s1, int s2) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0, false));

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            
            if(node.isPass) {
                if(dist[node.idx][1] < node.cost) continue;
            } else {
                if(dist[node.idx][0] < node.cost) continue;
            }

            for(Node n : graph[node.idx]) {

                int nextCost = node.cost + n.cost;
                boolean nextIsPass = node.isPass || 
                             (node.idx == s1 && n.idx == s2) || 
                             (node.idx == s2 && n.idx == s1);

                if(nextIsPass) {
                    if(dist[n.idx][1] > nextCost) {
                        dist[n.idx][1] = nextCost;
                        queue.offer(new Node(n.idx, nextCost, nextIsPass));
                    } 
                } else {
                    if(dist[n.idx][0] > nextCost) {
                        dist[n.idx][0] = nextCost;
                        queue.offer(new Node(n.idx, nextCost, nextIsPass));
                    } 
                }
            }
        }       
    }

    static void findSuccess() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : target) {
            if(dist[i][1] != INF && dist[i][1] <= dist[i][0]) {
                list.add(i);
            }
        }
        list.sort(null);

        for(int i : list) {
            sb.append(i).append(" ");
        }
        sb.append("\n");
    }
    
}

class Node implements Comparable<Node>{
    int idx, cost;
    boolean isPass;
    Node(int i, int c, boolean chk) {
        idx = i;
        cost = c;
        isPass = chk;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
