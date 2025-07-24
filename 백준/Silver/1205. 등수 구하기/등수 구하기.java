import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int answer = 1;
        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        
        int i = 1;
        int cnt = 0;

        if(N > 0) st = new StringTokenizer(br.readLine());
        while(N-- > 0) {
            int n = Integer.parseInt(st.nextToken());
            if(n > target) {
                i++;
                continue;
            } else if(n == target) {
                cnt++;
                continue;
            } else break;
        }

        if(i + cnt > P) answer = -1;
        else answer = i;
        
        System.out.println(answer);
    }

    
}