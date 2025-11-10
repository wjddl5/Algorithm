import java.io.*;
import java.util.*;

public class Main {

    static int N, col, maxWeight, minLevel;
    static Node[] graph;
    static int[][] weight;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        graph = new Node[N+1];
        weight = new int[N+1][2];
        for(int i=1; i<=N; i++) {
            weight[i][0] = 100_000;
        }

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            Node node = new Node(l, r);
            graph[n] = node;
        }

        int root = getRoot();
        find(root, 1);

        for(int i=1; i<=N; i++) {
            int w = weight[i][1] - weight[i][0] + 1;
            if(maxWeight < w) {
                maxWeight = w;
                minLevel = i;
            }
        }
        
        System.out.println(minLevel + " " + maxWeight);
    }

    static int getRoot() {
        int root = 1;
        boolean[] isChild = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            Node node = graph[i];
            if(node.left > 0) isChild[node.left] = true;
            if(node.right > 0) isChild[node.right] = true;
        }
        for(int i=1; i<=N; i++) {
            if(isChild[i]) continue;
            root = i;
            break;
        }
        return root;
    }

    static void find(int idx, int level) {
        Node node = graph[idx];
        if(node.left > 0) {
            find(node.left, level + 1);
        }
        
        col++;
        weight[level][0] = Math.min(weight[level][0], col);
        weight[level][1] = Math.max(weight[level][1], col);

        if(node.right > 0) {
            find(node.right, level + 1);
        }

    }
}

class Node {
    int left;
    int right;
    Node(int left, int right) {
        this.left = left;
        this.right = right;
    } 
}