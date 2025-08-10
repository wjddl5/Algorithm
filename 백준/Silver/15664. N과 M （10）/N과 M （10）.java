import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[] numbers, ar;
    static StringBuilder sb = new StringBuilder(); 

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());    
        numbers = new int[N];
        ar = new int[M];
        
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) 
            numbers[i] = Integer.parseInt(st.nextToken());   
        
        Arrays.sort(numbers);

        dfs(0, 0);

        System.out.println(sb);
    }

    static void dfs(int start, int depth) {
        if(depth == M) {
            for(int n : ar) 
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }

        int pre = 0;
        for(int i=start; i<N; i++) {
            if(numbers[i] == pre) continue;

            ar[depth] = numbers[i];
            pre = numbers[i];
            dfs(i + 1, depth + 1);
        }
    }

}