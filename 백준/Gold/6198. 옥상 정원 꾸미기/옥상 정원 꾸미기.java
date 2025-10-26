import java.io.*;
import java.util.*;

public class Main {

  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    long answer = 0;
    Stack<Integer> stack = new Stack<>();

    int N = Integer.parseInt(br.readLine());
    
    for(int i=0; i<N; i++) {
      int n = Integer.parseInt(br.readLine());
      
      while(!stack.isEmpty()) {
        if(n >= stack.peek()) stack.pop();
        else break;
      }

      answer += stack.size();

      stack.push(n);
    }

    System.out.println(answer);
  }


}