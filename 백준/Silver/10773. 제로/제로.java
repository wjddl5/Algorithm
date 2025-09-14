import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        int K = Integer.parseInt(br.readLine());
        int sum = 0;

        Stack<Integer> stack = new Stack<>();
        while(K-- > 0) {
            int n = Integer.parseInt(br.readLine());
            if(n > 0) {
                stack.push(n);
                continue;
            } 
            if(!stack.isEmpty()) {
                stack.pop();
            }
        }

        for(int n : stack) {
            sum += n;
        }

        System.out.println(sum);
    }
}