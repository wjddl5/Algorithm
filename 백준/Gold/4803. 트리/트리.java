import java.io.*;
import java.util.*;

public class Main {

    static int[] node;
    static boolean[] tree;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = 1;
        while(true) {
            st = new StringTokenizer(br.readLine());
            
            int cnt = 0;
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            sb.append("Case ").append(tc).append(": ");

            node = new int[n+1];
            tree = new boolean[n+1];

            for(int i=1; i<=n; i++)
                node[i] = i;
            
            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }
          
            for(int i=1; i<=n; i++) {
                int root = find(i);
                if(tree[root]) continue;
                tree[root] = true;
                cnt++;
            }

            if(cnt == 0) sb.append("No trees.").append("\n");
            else if(cnt == 1) sb.append("There is one tree.").append("\n");
            else sb.append("A forest of ").append(cnt).append(" trees.").append("\n");
            
            tc++;
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 == n2) {
            tree[n1] = true;
            return;
        }
        if(tree[n1] || tree[n2]) tree[Math.min(n1, n2)] = true;
        node[Math.max(n1, n2)] = Math.min(n1, n2);
    }

    static int find(int n) {
        if(node[n] == n) return n;
        return node[n] = find(node[n]);
    }

}