import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> tree = new ArrayList<>();
    static int v, startRoot, max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        v = Integer.parseInt(br.readLine());

        tree.add(new ArrayList<>());
        for (int i = 0; i < v; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());

            int root = Integer.parseInt(st.nextToken());
            while (st.hasMoreTokens()) {
                int num = Integer.parseInt(st.nextToken());
                if (num == -1)
                    break;

                int cost = Integer.parseInt(st.nextToken());
                tree.get(root).add(new Node(num, cost));
            }
        }
        
        bfs(1);
        bfs(startRoot);

        System.out.println(max);
    }

    static void bfs(int root) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[] visit = new boolean[v + 1];
        q.offer(new Node(root, 0));
        visit[root] = true;
        while(!q.isEmpty()) {
            Node n = q.poll();
            int num = n.num;
            int cost = n.cost;
            
            if(max < cost) {
                max = cost;
                startRoot = n.num;
            }

            for (Node node : tree.get(num)) {
                if (visit[node.num]) continue;
                q.offer(new Node(node.num, node.cost+cost));
                visit[node.num] = true;
            }
        }
    }

}

class Node {
    int num;
    int cost;

    Node(int num, int cost) {
        this.num = num;
        this.cost = cost;
    }
}