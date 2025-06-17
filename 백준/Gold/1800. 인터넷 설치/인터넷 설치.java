import java.io.*;
import java.util.*;

public class Main {
    static final long INF=Long.MAX_VALUE;
    static int N, K, answer=-1;
    static ArrayList<Node>[] graph;
    static long[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int right = 0;

        graph = new ArrayList[N+1];
        dist = new long[N+1];

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
            right = Math.max(right, c);
        }

        parametricSearch(0, right);

        System.out.println(answer);
    }

    static void parametricSearch(int left, int right) {
        while(left <= right) {
            int mid = (left + right) / 2;
            if(isConnect(mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }

        }
    }

    static boolean isConnect(int x) {
        Deque<Integer> deque = new ArrayDeque<>();
        Arrays.fill(dist, INF);
        deque.offerFirst(1);
        dist[1] = 0;

        while(!deque.isEmpty()) {
            int now = deque.poll();

            for(Node n : graph[now]) {
                int add = n.cost > x ? 1 : 0;
                if(dist[n.idx] > dist[now] + add) {
                    dist[n.idx] = dist[now] + add;
                    if(n.cost > x) {
                        deque.offerLast(n.idx);
                    } else {
                        deque.offerFirst(n.idx);
                    }
                }
            }
        }

        return dist[N] <= K;
    }

}

class Node{
    int idx;
    long cost;
    Node(int i, long c) {
        idx = i;
        cost = c;
    }
}
