import java.io.*;
import java.util.*;

public class Main {

    static int target;
    static int[] from;
    static char[] how;
    static boolean[] visit;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        
        for(int tc=0; tc<T; tc++) {
            visit = new boolean[10000];
            how = new char[10000];
            from = new int[10000];

            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            bfs(n);
            sb.append(print()).append('\n');
        }
        System.out.println(sb);
    }

    public static void bfs(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);
        visit[n] = true;
        from[n] = -1;

        while(!queue.isEmpty()) {
            int num = queue.poll();

            for(char control : new char[] {'D', 'S', 'L', 'R'}) {
                int next = dslr(num, control);
                if(visit[next]) continue; 
                
                queue.offer(next);
                visit[next]  = true;
                from[next] = num;
                how[next]  = control;
                if(next == target) return;
            } 
        }
    }

    public static String print() {
        StringBuilder sbr = new StringBuilder();
        int num = target;
        while(from[num] != -1)  {
            sbr.append(how[num]);
            num = from[num];
        }
        return sbr.reverse().toString();
    }

    public static int dslr(int n, char str) {
        switch (str) {
            case 'D':
                return (n*2)%10000;
            case 'S':
                return n == 0 ? 9999 : n -1;
            case 'L':
                return (n%1000*10) + (n/1000);
            case 'R':
                return (n%10*1000) + (n/10);
        }
        return -1;
    }

}