import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        for(int i=0; i<=N; i++) {
            list.add(new ArrayList<>());
        }
        for(int i=1; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int root =  Integer.parseInt(st.nextToken());
            int node =  Integer.parseInt(st.nextToken());
            list.get(root).add(node);
            list.get(node).add(root);
        }
        find();
        System.out.println(sb);
    }

    static void find() {
        Queue<Integer> queue = new ArrayDeque<>();
        int[] res = new int[N+1];
        boolean[] visit = new boolean[N+1];
        queue.offer(1);
        visit[1] = true;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            for(int node :  list.get(cur)) {
                if(visit[node]) continue;
                queue.offer(node);
                visit[node] = true;
                res[node] = cur;
            }
        }
        for(int i=2; i<=N; i++) {
            sb.append(res[i]).append("\n");
        }
    }
}