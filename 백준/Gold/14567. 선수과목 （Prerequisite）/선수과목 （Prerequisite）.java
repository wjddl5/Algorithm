import java.io.*;
import java.util.*;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int n, m;
    static int[] ar, answer;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        ar = new int[n+1];
        answer = new int[n+1];

        for(int i=0; i<=n; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            graph.get(first).add(second);
            ar[second] += 1;
        }
        find();

        for(int i=1; i<=n; i++) {
            sb.append(answer[i]).append(" ");
        }
        System.out.println(sb);
    }

    public static void find() {
        Queue<Integer> queue = new ArrayDeque<>();
        int answer_cnt = 1;
        int cnt = 0;
        
        for(int i=1; i<=n; i++) {
            if(ar[i] == 0) queue.offer(i);
        }
        
        while(cnt < n) {
            ArrayList<Integer> list = new ArrayList<>();
            
            while(!queue.isEmpty()) {
                int n = queue.poll();
                answer[n] = answer_cnt;
                cnt ++;
                
                for(int node : graph.get(n)) {
                    ar[node] -= 1;
                    if(ar[node] == 0) list.add(node);
                }
                
            }
            if(!list.isEmpty()) {
                for(int node : list) {
                    queue.offer(node);
                }
            }
            answer_cnt ++;
        }

    }
}