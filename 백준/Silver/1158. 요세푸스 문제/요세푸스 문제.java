import java.io.*;
import java.util.*;

public class Main {
    static int N, K;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Deque<Integer> dq = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            dq.offer(i);
        }

        sb.append("<");

        int cnt = 1;
        while(!dq.isEmpty()) {
            if(cnt == K) {
                sb.append(dq.poll()).append(",").append(" ");
                cnt = 1;
                continue;
            }
            int n = dq.poll();
            dq.offer(n);
            
            cnt++;
        }

        sb.delete(sb.length()-2, sb.length());
        sb.append(">");
        
        System.out.println(sb);
    }
}
