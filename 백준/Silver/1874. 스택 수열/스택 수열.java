import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
   
        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(br.readLine());

        int num = 1;
        stack.push(num++);
        sb.append('+').append("\n");

        while(N-- > 0) {
            int n = Integer.parseInt(br.readLine());
            
            for(int i=num; i<=n; i++) {
                stack.push(i);
                sb.append('+').append("\n");
                num++;
            }
            if(stack.peek() == n) {
                stack.pop();
                sb.append('-').append("\n");
            }
        }

        System.out.println(stack.isEmpty() ? sb : "NO");
    }
}