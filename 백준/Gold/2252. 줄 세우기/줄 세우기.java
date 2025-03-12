import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] ar;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        for(int i=-1; i<n; i++) {
            graph.add(new ArrayList<>());
        }

        ar = new int[n+1];

        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            int low = Integer.parseInt(st.nextToken());
            int high = Integer.parseInt(st.nextToken());
            graph.get(low).add(high);
            ar[high] += 1;
        }

        find();
        System.out.println(sb);
    }

    public static void find() {
        Queue<Integer> queue = new ArrayDeque<>();
        for(int i=1; i<=n; i++) {
            if(ar[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()) {
            int n = queue.poll();
            sb.append(n).append(" ");

            for(int node : graph.get(n)){
                ar[node] -= 1;
                if(ar[node] == 0) queue.offer(node);
            }
        }
    }

}