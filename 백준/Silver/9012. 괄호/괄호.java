import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        

        int n = Integer.parseInt(br.readLine());

        while(n-- > 0) {
            sb.append(find(br.readLine()) ? "YES" : "NO").append("\n");
        }

        System.out.println(sb);
    }

    static boolean find(String str) {
        Stack<Integer> stack = new Stack<>();
        
        int length = str.length();
        for(int i=0; i<length; i++) {
            char c = str.charAt(i);
            if(c == '(') {
                stack.push(0);
            } else {
                if(stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}