import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] tree;
    static int[] size;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N+1];
        size = new int[N+1];
        for(int i=1; i<=N; i++) {
            tree[i] = new ArrayList<>();
        }
            
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            tree[u].add(v);
            tree[v].add(u);
        }

        setTree(root, -1);

        while(q-- > 0) {
            int n = Integer.parseInt(br.readLine());
            sb.append(size[n]).append("\n");
        }

        System.out.println(sb);
    }

    static void setTree(int node, int parent) {
        size[node] = 1;

        for(int n : tree[node]) {
            if(n == parent) continue;
            setTree(n, node);
            size[node] += size[n];
        }
    }

}