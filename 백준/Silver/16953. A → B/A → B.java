import java.io.*;
import java.util.*;

public class Main {
    static int start, target;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        
        System.out.println(find());
    }

    static int find() {
        Queue<long[]> q = new ArrayDeque<>();
        Set<Long> set = new HashSet<>();

        q.offer(new long[] {start, 1});
        set.add((long) start);

        while(!q.isEmpty()) {
            long[] tmp = q.poll();
            long num = tmp[0];
            long cnt = tmp[1];

            if(num == target) return (int) cnt;

            long next1 =  num * 2;
            long next2 =  num * 10 + 1;

            if (next1 <= target && !set.contains(next1)) {
                q.offer(new long[] {next1, cnt + 1});
                set.add(next1);
            }

            if (next2 <= target && !set.contains(next2)) {
                q.offer(new long[] {next2, cnt + 1});
                set.add(next2);
            }
        }
        
        return -1;
    }

}