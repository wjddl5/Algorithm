import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int v;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        v = Integer.parseInt(br.readLine());

        for (int i = 0; i <= v; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1)
                    break;

                int dist = Integer.parseInt(st.nextToken());
                tree.get(root).add(new Node(num, dist));
            }
        }
        
        Node start = bfs(1);
        Node end = bfs(start.num);

        System.out.println(end.dist);
    }

    static Node bfs(int root) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visit = new boolean[v + 1];

        Node reNode = new Node(root, 0);
        q.offer(reNode);
        visit[root] = true;

        while(!q.isEmpty()) {
            Node node = q.poll();
            int num = node.num;
            int dist = node.dist;

            if(reNode.dist < dist) {
                reNode = node;
            }

            for (Node nextNode : tree.get(num)) {
                if (visit[nextNode.num]) continue;
                q.offer(new Node(nextNode.num, nextNode.dist + dist));
                visit[nextNode.num] = true;
            }
        }

        return reNode;
    }

}

class Node {
    int num;
    int dist;

    Node(int num, int dist) {
        this.num = num;
        this.dist = dist;
    }
}