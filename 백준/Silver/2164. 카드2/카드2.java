import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        
        Queue<Integer> queue = new ArrayDeque<>();
        int N = Integer.parseInt(br.readLine());
        
        for(int i=1; i<=N; i++) {
            queue.offer(i);
        }

        while(queue.size() > 1) {
            queue.poll();
            int n = queue.poll();
            queue.offer(n);
        }

        System.out.println(queue.poll());
    }
}