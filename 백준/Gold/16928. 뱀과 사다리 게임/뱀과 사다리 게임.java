import java.io.*;
import java.util.*;

public class Main {

    static int[] jump = new int[101], ar = new int[101];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            jump[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }
        Arrays.fill(ar, 100);
        ar[1] = 0;
        bfs();
        System.out.println(ar[100]);
    }

    public static void bfs() {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        while(!queue.isEmpty()) {
            int p = queue.poll();
            for(int i=6; i>0; i--) {
                int np = p + i;
                if(np > 100) continue;

                if(jump[np] == 0) {     // 평범한 칸
                    if(ar[np] <= ar[p] + 1) continue;
                    queue.offer(np);
                    ar[np] = ar[p] + 1;
                } else {                // 사다리 or 뱀
                    if(ar[jump[np]] <= ar[p] + 1) continue;
                    queue.offer(jump[np]);
                    ar[jump[np]] = ar[p] + 1;
                }
            }
        }
    }

}