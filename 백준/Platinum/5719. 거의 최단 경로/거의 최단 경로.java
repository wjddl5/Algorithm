import java.io.*;
import java.util.*;

public class Main {
    static final int INF=Integer.MAX_VALUE;
    static ArrayList<Node>[] graph;
    static HashSet<Integer>[] prev;
    static int[] dist, dist2;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        while(true) {
            String str = br.readLine();
            if(str.equals("0 0")) break;

            st = new StringTokenizer(str);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[n];
            prev = new HashSet[n];
            dist = new int[n];
            dist2 = new int[n];
            
            for(int i=0; i<n; i++) {
                graph[i] = new ArrayList<>();
                prev[i] = new HashSet<>();
                dist[i] = INF;
                dist2[i] = INF;
            }

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            dist[start] = 0;
            dist2[start] = 0;

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph[u].add(new Node(v, c, true));
            }

            find(start, end);
            removeMin(start, end);
            find2(start);

            sb.append(dist2[end] == INF ? -1 : dist2[end]).append("\n");
        }
        System.out.println(sb);
    }

    static void find(int start, int end) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0, true));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.cost > dist[node.idx]) continue;

            for(Node n : graph[node.idx]) {
                int nCost = node.cost + n.cost;

                if(dist[n.idx] > nCost) {
                    dist[n.idx] = nCost;
                    queue.offer(new Node(n.idx, nCost, true));

                    prev[n.idx].clear();
                    prev[n.idx].add(node.idx);

                } else if(dist[n.idx] == nCost) {
                    prev[n.idx].add(node.idx);
                }
            }
        }
    }

    static void removeMin(int start, int end) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(end);

        while (!queue.isEmpty()) {
            int to = queue.poll();

            for (int from : prev[to]) {
                Iterator<Node> iter = graph[from].iterator();
                while (iter.hasNext()) {
                    Node n = iter.next();
                    if (n.idx == to && n.isGo) {
                        n.isGo = false;
                        queue.offer(from);
                    }
                }
            }
        }
    }

    static void find2(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        queue.offer(new Node(start, 0, true));

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(node.cost > dist2[node.idx]) continue;

            for(Node n : graph[node.idx]) {
                if(n == null) continue;

                int nCost = node.cost + n.cost;

                if(n.isGo && dist2[n.idx] > nCost) {
                    dist2[n.idx] = nCost;
                    queue.offer(new Node(n.idx, nCost, true));
                }
            }
        }
    }

}

class Node implements Comparable<Node>{
    int idx, cost;
    boolean isGo;
    Node(int i, int c, boolean g) {
        idx = i;
        cost = c;
        isGo = g;
    }
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}
