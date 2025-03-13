import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] group, plan;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        group = new int[n+1];
        plan = new int[m];
        for(int i=0; i<=n; i++) {
            group[i] = i;
        }

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                if(Integer.parseInt(st.nextToken()) == 0) continue;
                int root = Math.min(i, j);
                int node = Math.max(i, j);
                union(root, node);
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            plan[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(check() ? "YES" : "NO");
    }

    public static void union(int root, int node) {
        int n1 = find(root);
        int n2 = find(node);
        if(n1 != n2) group[n2] = n1;
    }

    public static int find(int node) {
        if(group[node] == node) return node;
        else return group[node] = find(group[node]);
    }

    public static boolean check() {
        int start = plan[0];
        for(int node : plan) {
            if(find(start) != find(node)) return false;
        }
        return true;
    }

}