import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<Integer>[] graph;
    static Map<Integer, Integer> score;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        score = new HashMap<>();
        graph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            graph[i] = new ArrayList<>();
            score.put(i, 0);
        }

        st = new StringTokenizer(br.readLine());
        st.nextToken();
        for(int i=2; i<=N; i++) {
            int p = Integer.parseInt(st.nextToken());
            graph[p].add(i);
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            int s = score.get(i);
            score.put(i, s + w);
        }

        find();

        System.out.println(sb);
    }

    static void find() {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);

        while(!q.isEmpty()) {
            int node = q.poll();
            int sc = score.get(node);

            for(int n : graph[node]) {
                int s = score.get(n);
                score.put(n, sc + s);
                q.offer(n);
            }
        }

        for(int i=1; i<=N; i++) {
            sb.append(score.get(i)).append(" ");
        }
    }
}