import java.io.*;
import java.util.*;

class Main {

    static class Node {
        int idx, x, y, r;
        Node(int idx, int x, int y, int r) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.r = r;
        }
    }

    static int N;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while(tc-- > 0) {
            N = Integer.parseInt(br.readLine());
            
            parent = new int[N];
            for(int i=0; i<N; i++) parent[i] = i;

            ArrayList<Node> list = new ArrayList<>();
            int idx = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                list.add(new Node(idx++, x, y, r));
            }

            for(int i=0; i<N; i++) {
                Node n1 = list.get(i);
                for(int j=i+1; j<N; j++) {
                    Node n2 = list.get(j);
                    
                    if(inBound(n1, n2)) {
                        union(n1, n2);
                    }
                }
            }

            int ans = 0;
            for(int i=0; i<N; i++) {
                if(parent[i] == i) ans++;
            }
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
    }

    static boolean inBound(Node n1, Node n2) {
        long x = n1.x - n2.x;
        long y = n1.y - n2.y;
        long r = n1.r + n2.r;

        return x * x + y * y <= r * r;
    }

    static void union(Node n1, Node n2) {
        int a = find(n1.idx);
        int b = find(n2.idx);
        if(a < b) parent[a] = b; 
        else if(a > b) parent[b] = a; 
    }

    static int find(int n) {
        if(parent[n] == n) return n;
        return parent[n] = find(parent[n]);
    }

}