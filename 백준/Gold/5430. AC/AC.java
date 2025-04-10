import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        testCase:
        for(int tc=0; tc<T; tc++) {
            String[] method = br.readLine().split("");
            br.readLine();
            String[] str = br.readLine().replace("[", " ").replace("]", "").trim().split(",");         
            Deque<Integer> deque = new ArrayDeque<>();
            if(!str[0].equals("")) {
                for(String s : str) {
                    deque.offer(Integer.parseInt(s));
                }
            }
            boolean chk = true;
            for(String m : method) {
                switch (m) {
                    case "R":
                        chk = !chk;      
                        break;
                    case "D":
                        if(deque.size()<1) {
                            sb.append("error").append("\n");
                            continue testCase;
                        }
                        if(chk) deque.pollFirst();
                        else deque.pollLast();
                        break;
                }
            }
            sb.append("[");
            while(!deque.isEmpty()) {
                sb.append(chk ? deque.pollFirst() : deque.pollLast());
                if(!deque.isEmpty()) sb.append(",");
            }
            sb.append("]").append("\n");
        }
        System.out.println(sb);
    }

}