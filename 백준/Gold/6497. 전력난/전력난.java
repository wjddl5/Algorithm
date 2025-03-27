import java.io.*;
import java.util.*;

public class Main {

    static int M, N, total, answer;
    static int[][] graph;
    static int[] ar;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            
            if(M==0 && N==0) break;
            
            total = 0;
            graph = new int[N][3];
            ar = new int[M];
            
            for(int i=0; i<M; i++) {
                ar[i] = i;
            }
            
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                int size = Integer.parseInt(st.nextToken());
                
                int root = Math.min(x, y);
                int node = Math.max(x, y);  
                
                graph[i][0] = root;
                graph[i][1] = node;
                graph[i][2] = size;
                total += size;
            }
            getAnswer();
            sb.append(answer).append("\n");
        }
            
        System.out.println(sb);
        }
        
    public static void getAnswer() {
        Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
        int cnt = 0;
        for(int i=0; i<graph.length; i++) {
            if((find(graph[i][0]) != find(graph[i][1]))) {
                cnt += graph[i][2];
                union(graph[i][0], graph[i][1]);
            }
        }
        answer = total - cnt;
    }

    public static int find(int node) {
        if(node == ar[node]) return node;
        return ar[node] = find(ar[node]);
    }
    public static void union(int root, int node) {
        int n1 = find(root);
        int n2 = find(node);
        
        if(n1 != n2) ar[n2] = n1;
    } 
}

