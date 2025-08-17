import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] node;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        node = new int[N+1];

        for(int i=1; i<=N; i++) 
            node[i] = i;
        
        for(int i=0; i<N-2; i++) {
            st = new StringTokenizer(br.readLine());
            
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            union(a, b);
        }

        Map<Integer, Integer> map = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i=1; i<=N; i++) {
            int root = find(i);
            if(map.getOrDefault(root, null) == null) {
                map.put(root, i);
                list.add(i);
            }
            if(map.size() == 2) break;
        }

        System.out.println(list.get(0)+" "+list.get(1));
    }

    static void union(int a, int b) {
        int n1 = find(a);
        int n2 = find(b);
        if(n1 != n2) {
            node[Math.max(n1, n2)] = Math.min(n1, n2);
        } 
    }

    static int find(int n) {
        if(node[n] == n) return n;
        return node[n] = find(node[n]);
    }

}