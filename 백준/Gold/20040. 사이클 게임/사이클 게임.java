import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] node;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        node = new int[N];

        for(int i=0; i<N; i++)
            node[i] = i; 

        int i = 1;
        boolean chk = false;

        for(; i<=m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(union(a, b)) {
                chk = true;
                break;
            }
        }

        System.out.println(chk ? i : 0);
    }

    static boolean union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 == n2) return true;
        node[Math.min(n1, n2)] = Math.max(n1, n2);
        return false;
    }

    static int find(int n) {
        if(node[n] == n) return n;
        return node[n] = find(node[n]);
    }


}