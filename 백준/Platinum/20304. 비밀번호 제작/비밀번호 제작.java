import java.io.*;
import java.util.*;

class Main {
    static int N, max;
    static int[] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine()); 
        st = new StringTokenizer(br.readLine());

        dist = new int[1000001];
        Arrays.fill(dist, -1);

        Queue<Integer> queue = new ArrayDeque<>();
        while(m-- > 0) {
            int p = Integer.parseInt(st.nextToken());
            dist[p] = 0;
            queue.offer(p);
        }

        find(queue);
        getMax();
        System.out.println(max);
    }

    static void find(Queue<Integer> queue) {
        while(!queue.isEmpty()) {
            int num = queue.poll();
            
            for(int i=0; i<20; i++) {
                int next = num ^ (1 << i);

                if(next < 1000001 && dist[next] < 0) {
                    dist[next] = dist[num] + 1;
                    queue.offer(next);
                }
            }
        }
    }

    static void getMax() {
        for(int i=0; i<=N; i++) {
            max = Math.max(max, dist[i]);
        }
    }
    
}