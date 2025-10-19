import java.io.*;
import java.util.*;

public class Main {

    static int N, count;
    static Deque<Integer> deque;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;


        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        deque = new ArrayDeque<>();
        for(int i=1; i<=N; i++) {
            deque.offer(i);
        }

        st = new StringTokenizer(br.readLine());

        while(M-- > 0) {
            int target = Integer.parseInt(st.nextToken());
            find(target);
        }

        System.out.println(count);
    }

    static void find(int target) {
        int idx = 0;
        for(int n : deque) {
            if(target == n) break;
            idx++;
        }
        while(true) {
            if(target == deque.peek()) {
                deque.poll();
                break;
            }
            if(idx > deque.size() / 2) {
                deque.offerFirst(deque.pollLast());
                count++;
            } else {
                deque.offerLast(deque.pollFirst());
                count++;
            }
        }
    }

}
