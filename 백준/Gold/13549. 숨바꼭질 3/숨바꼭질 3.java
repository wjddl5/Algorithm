import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] ar = new int[100001];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Arrays.fill(ar, Integer.MAX_VALUE);
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] {N, 0});
        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int now = tmp[0];
            int dis = tmp[1];

            if(now == K && ar[now] > dis) {
                ar[now] = dis;
            }
            
            if(now > 0 && ar[now-1] > dis+1) {
                ar[now-1] = dis +1;
                queue.offer(new int[] {now-1, dis+1});
            }
            if(now < 100000 && ar[now+1] > dis+1) {
                ar[now+1] = dis +1;
                queue.offer(new int[] {now+1, dis+1});
            }
            if(now <= 50000 && ar[now*2] > dis) {
                ar[now*2] = dis;
                queue.offer(new int[] {now*2, dis});
            }
        }
        System.out.println(ar[K]);
    }

}
