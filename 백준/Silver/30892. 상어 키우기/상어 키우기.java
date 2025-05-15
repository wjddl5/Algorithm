import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int eat = Integer.parseInt(st.nextToken());
        long size = Integer.parseInt(st.nextToken());

        int[] sharks = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            sharks[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(sharks);

        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new ArrayDeque<>();

        for(int shark : sharks) {
            if(size > shark) stack.push(shark);
            else queue.offer(shark);
        }

        while(eat-- > 0) {
            if(!stack.isEmpty()) size += stack.pop();
            
            int qSize = queue.size();
            for(int i=0; i<qSize; i++) {            
                int shark = queue.peek();
                if(size > shark) {
                    stack.add(shark);
                    queue.poll();
                } else break;
            }
        }

        System.out.println(size);
    }
    
}