import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] node;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int tc=1; tc<=T; tc++) {
            sb.append("Scenario ").append(tc).append(":").append("\n");

            N = Integer.parseInt(br.readLine());

            node = new int[N];

            for(int i=0; i<N; i++)
                node[i] = i;
            
            int k = Integer.parseInt(br.readLine());

            while(k-- > 0) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }

            int m = Integer.parseInt(br.readLine());

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if(find(a) == find(b)) sb.append(1).append("\n");
                else sb.append(0).append("\n");
            }
            sb.append("\n");
        }
        System.out.println(sb);
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