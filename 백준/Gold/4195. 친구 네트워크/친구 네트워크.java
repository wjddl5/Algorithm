import java.io.*;
import java.util.*;

class Main {

    static int[] parent, size;
    static int idx;
    static Map<String, Integer> names;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {

            int F = Integer.parseInt(br.readLine());
            idx = 0;
            names = new HashMap<>();
            parent = new int[F*2];
            size = new int[F*2];
            
            for(int i=0; i<F*2; i++) {
                parent[i] = i;
                size[i] = 1;
            }

            while(F-- > 0) { 
                String[] str = br.readLine().split(" ");
                String s1 = str[0];
                String s2 = str[1];

                if(!names.containsKey(s1)) {
                    names.put(s1, idx);
                    idx++;
                }
                if(!names.containsKey(s2)) {
                    names.put(s2, idx);
                    idx++;
                }

                int n1 = names.get(s1);
                int n2 = names.get(s2);

                union(n1, n2);
                sb.append(size[find(n1)]).append("\n");
            } 
        }

        System.out.println(sb);
    }

    static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 != n2) {
            int c = Math.max(n1, n2);
            int p = Math.min(n1, n2);
            parent[c] = p;
            size[p] += size[c];
        }
    }

    static int find(int a) {
        if(parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }

}