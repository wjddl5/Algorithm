import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxq = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());

            if (maxq.isEmpty() || n <= maxq.peek()) {
                maxq.offer(n);
            } else {
                minq.offer(n);
            }

            if (maxq.size() > minq.size() + 1) {
                minq.offer(maxq.poll());
            } else if (minq.size() > maxq.size()) {
                maxq.offer(minq.poll());
            }

            sb.append(maxq.peek()).append("\n");
        }

        System.out.println(sb);
    }

}
