import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int answer = 0;
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        while(N-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        while(pq.size() > 1) {
            int n = pq.poll();
            int m = pq.poll();
            answer += n + m;
            pq.offer(n + m);
            
        }

        System.out.println(answer);
    }
}