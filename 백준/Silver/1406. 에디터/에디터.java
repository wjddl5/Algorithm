import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = null;

        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        String str = br.readLine();
        for(int i=0; i<str.length(); i++) 
            left.push(str.charAt(i));
        
        int M = Integer.parseInt(br.readLine());
        while(M-- > 0) {
            st = new StringTokenizer(br.readLine());

            switch (st.nextToken()) {
                case "L":
                    if(!left.isEmpty()) right.push(left.pop());
                    break;
                case "D":
                    if(!right.isEmpty()) left.push(right.pop());
                    break;
                case "B":
                    if(!left.isEmpty()) left.pop();
                    break;
                case "P":
                    left.push(st.nextToken().charAt(0));
                    break;
            }
        }


        while (!left.isEmpty()) 
            right.push(left.pop());
        
        while (!right.isEmpty()) 
            sb.append(right.pop());
        
        System.out.println(sb);
    }
}