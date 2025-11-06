import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }

        int cycle = 0;
        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(find(u) == find(v)) cycle++;
            union(u, v);
        }   

        for(int i=1; i<=N; i++) {

        }

        int groupCnt = getGroupCount();

        System.out.println(cycle + groupCnt - 1);
    }

    static int find(int n) {
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

    static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 < n2) parent[n2] = n1; 
        if(n1 > n2) parent[n1] = n2; 
    }

    static int getGroupCount() {
        int cnt = 0;
        boolean[] v = new boolean[N+1];
        for(int i=1; i<=N; i++) {
            int p = find(i);
            if(v[p]) continue;
            v[p] = true;
            cnt++;
        }
        return cnt;
    }
}