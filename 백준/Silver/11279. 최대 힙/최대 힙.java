import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(br.readLine());
            if(n == 0) {
                if(q.size() == 0) sb.append(0).append("\n");
                else sb.append(q.poll()).append("\n");
                continue;
            }
            q.offer(n);
        }
        System.out.println(sb);
    }

}