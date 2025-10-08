import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static Stack<Tower> stack;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        stack = new Stack<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int t = Integer.parseInt(st.nextToken());
            
            while(!stack.isEmpty()) {
                if(t <= stack.peek().high) {
                    sb.append(stack.peek().idx).append(" ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) sb.append(0).append(" ");

            stack.push(new Tower(i + 1, t));
        }

        System.out.println(sb);
    }

}

class Tower {
    int idx;
    int high;
    public Tower(int idx, int high) {
        this.idx = idx;
        this.high = high;
    }
}
