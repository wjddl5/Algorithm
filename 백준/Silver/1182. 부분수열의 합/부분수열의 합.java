import java.io.*;
import java.util.*;

public class Main {

    static int N, target, count;
    static int[] number;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        number = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            number[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0, 0);

        System.out.println(count);
    }

    static void dfs(int depth, int sum, int length) {
        if(depth == N) {
            if(sum == target && length > 0) count++;
            return;
        }

        dfs(depth+1, sum, length);
        dfs(depth+1, sum+number[depth], length+1);
        
        
    }
  
}