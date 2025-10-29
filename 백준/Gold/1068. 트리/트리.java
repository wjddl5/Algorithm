import java.io.*;
import java.util.*;

public class Main {

    static int N, count;
    static ArrayList<Integer>[] graph;
    static boolean[] deadNode;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        deadNode = new boolean[N];
        graph = new ArrayList[N];
        for(int i=0; i<N; i++) {
            graph[i] = new ArrayList<>();
        }


        st = new StringTokenizer(br.readLine());
        int root = 0;
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if(n < 0) {root = i; continue;}

            graph[n].add(i);
        }

        int r = Integer.parseInt(br.readLine());
        deadNode[r] = true;

        Queue<Integer> q = new ArrayDeque<>();
        if(!deadNode[root]) {
            q.offer(root);
        }
        while(!q.isEmpty()) {
            int node = q.poll();
            boolean chk = false;

            for(int n : graph[node]) {
                if(deadNode[n]) continue;
                q.offer(n);

                chk = true;
            }

            if(!chk) count++;
        }   

        System.out.println(count);
    }

}