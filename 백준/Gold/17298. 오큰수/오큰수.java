import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        int[] ar = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) 
            ar[i] = Integer.parseInt(st.nextToken());
        
        int[] ans = find(ar);

        StringBuilder sb = new StringBuilder();
        for(int a : ans)
            sb.append(a).append(" ");

        System.out.println(sb);
    }

    static int[] find(int[] ar) {
        int[] ans = new int[N];
        Stack<Integer> stack = new Stack<>();

        for(int i=N-1; i>=0; i--) {
            while(!stack.isEmpty() && ar[i] >= stack.peek()) {
                stack.pop();
            }
            ans[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(ar[i]);
           
        }
        return ans; 
    }
}