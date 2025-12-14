import java.io.*;
import java.util.*;

class Main {

    static int N, answer;
    static ArrayList<Integer>[] inGraph, outGraph;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        inGraph = new ArrayList[N+1];
        outGraph = new ArrayList[N+1];
        for(int i=1; i<=N; i++) {
            inGraph[i] = new ArrayList<>();
            outGraph[i] = new ArrayList<>();
        }

        while(m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            inGraph[u].add(v);
            outGraph[v].add(u);
        }

        find();
        System.out.println(answer);
    }

    static void find() {
        int[] inCnts = new int[N+1];
        int[] outCnts = new int[N+1];

        for(int i=1; i<=N; i++) {
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] v = new boolean[N+1];
            q.offer(i);
            v[i] = true;
            while(!q.isEmpty()) {
                int node = q.poll();
                for(int n : inGraph[node]) {
                    if(v[n]) continue;
                    inCnts[i]++;
                    q.offer(n);
                    v[n] = true;
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            Queue<Integer> q = new ArrayDeque<>();
            boolean[] v = new boolean[N+1];
            q.offer(i);
            v[i] = true;
            while(!q.isEmpty()) {
                int node = q.poll();
                for(int n : outGraph[node]) {
                    if(v[n]) continue;
                    outCnts[i]++;
                    q.offer(n);
                    v[n] = true;
                }
            }
        }

        int mid = (N+1)/2;
        for(int i=1; i<=N; i++) {
            if(inCnts[i] >= mid || outCnts[i] >= mid) answer++;
        }

    }

}