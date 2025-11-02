import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] graph;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int tc = Integer.parseInt(br.readLine());

        while(tc-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for(int i=1; i<=N; i++)
                graph[i] = new ArrayList<>();

            while(m-- > 0) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            int cnt = find();
            sb.append(cnt).append("\n");
        }

        System.out.println(sb);
    }

    static int find() {
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];
        int cnt = -1;

        q.offer(1);
        v[1] = true;

        while(!q.isEmpty()) {
            int node = q.poll();
            cnt++;
            for(int n : graph[node]) {
                if(v[n]) continue;
                q.offer(n);
                v[n] = true;
            }
        }

        return cnt;
    }
}
