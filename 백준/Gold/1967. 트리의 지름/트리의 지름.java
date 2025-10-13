import java.io.*;
import java.util.*;

public class Main {

    static int N, answer;
    static ArrayList<Node>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, cost));
            tree[b].add(new Node(a, cost));
        }

        NodeInfo startNode = bfs(1);

        NodeInfo endNode = bfs(startNode.idx);

        System.out.println(endNode.dist);
    }

    static NodeInfo bfs(int start) {
        Queue<NodeInfo> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 1];

        queue.offer(new NodeInfo(start, 0));
        visit[start] = true;

        int max = 0;
        int endNode = start;

        while (!queue.isEmpty()) {
            NodeInfo nodeInfo = queue.poll();

            if (max < nodeInfo.dist) {
                max = nodeInfo.dist;
                endNode = nodeInfo.idx;
            }

            for (Node next : tree[nodeInfo.idx]) {
                if (!visit[next.to]) {
                    visit[next.to] = true;
                    queue.offer(new NodeInfo(next.to, nodeInfo.dist + next.cost));
                }
            }
        }

        return new NodeInfo(endNode, max);
    }
}

class Node {
    int to;
    int cost;
    Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class NodeInfo {
    int idx;
    int dist;
    NodeInfo(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }
}
