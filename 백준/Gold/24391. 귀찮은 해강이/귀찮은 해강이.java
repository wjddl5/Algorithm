import java.io.*;
import java.util.*;

public class Main {

    static int N, count;
    static int[] node;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        node = new int[N+1];

        for(int i=1; i<=N; i++)
            node[i] = i;

        int m = Integer.parseInt(st.nextToken());

        while(m-- >0) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b);
        }

        st = new StringTokenizer(br.readLine());

        int root = find(Integer.parseInt(st.nextToken()));
        for(int i=1; i<N; i++) {
            int nRoot = find(Integer.parseInt(st.nextToken()));
            if(root != nRoot) count++;
            root = nRoot;
        }

        System.out.println(count);
    }

    static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 == n2) return;
        node[Math.min(n1, n2)] = Math.max(n1, n2);
    }

    static int find(int n) {
        if(node[n] == n) return n;
        return node[n] = find(node[n]);
    }

}