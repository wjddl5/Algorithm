import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] group;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        group = new int[n+1];

        for(int i=0; i<=n; i++) {
            group[i] = i;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int check = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int root = Math.min(a, b);
            int node = Math.max(a, b);

            if(check == 0) {
                connect(root, node);
            }else {
                System.out.println(getGroupOf(root) == getGroupOf(node)? "YES" : "NO");
            }
        }
    }

    public static int getGroupOf(int node) {
        if(group[node] == node) return node;     
        else return group[node] = getGroupOf(group[node]);    
    }

    public static void connect(int root, int node) {
        int n1 = getGroupOf(root);
        int n2 = getGroupOf(node);
        if(n1 != n2) group[n2] = n1;
    }

}