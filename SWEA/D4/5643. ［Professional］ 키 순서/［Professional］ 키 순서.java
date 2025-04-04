import java.io.*;
import java.util.*;

public class Solution {

    static int N, M, answer;
    static ArrayList<Integer>[] upperAr, lowerAr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
            answer = 0;
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            upperAr = new ArrayList[N+1];
            lowerAr = new ArrayList[N+1];

            for(int i=1; i<=N; i++) {
                upperAr[i] = new ArrayList<>();
                lowerAr[i] = new ArrayList<>();
            }

            for(int i=0; i<M; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                upperAr[a].add(b);
                lowerAr[b].add(a);
            }
            find();
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    public static void find() {
        for(int num=1; num<=N; num++) {
            Queue<Integer> queue = new ArrayDeque<>();
            boolean[] v = new boolean[N+1];
            queue.offer(num);
            v[num] = true;
            int cnt = 1;
            while(!queue.isEmpty()) {
                int root = queue.poll();
                if(upperAr[root].size() > 0) {
                    ArrayList<Integer> list = upperAr[root];
                    for(int node : list) {
                        if(v[node]) continue;
                        queue.offer(node);
                        v[node] = true;
                        cnt++;
                    }
                }
            }
            queue.offer(num);
            while(!queue.isEmpty()) {
                int root = queue.poll();
                if(lowerAr[root].size() > 0) {
                    ArrayList<Integer> list = lowerAr[root];
                    for(int node : list) {
                        if(v[node]) continue;
                        queue.offer(node);
                        v[node] = true;
                        cnt++;
                    }
                }
            }
            if(cnt == N) answer++;
        }
    }


}