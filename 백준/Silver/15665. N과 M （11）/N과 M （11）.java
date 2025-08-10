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
        ar = new int[M];
        
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        
        for(int i=0; i<N; i++) 
            set.add(Integer.parseInt(st.nextToken()));
            
        N = set.size();
        numbers = new int[N];

        int i = 0;
        for(int n : set) 
            numbers[i++] = n;
        
        Arrays.sort(numbers);

        dfs(0);

        System.out.println(sb);
    }

    static void dfs(int depth) {
        if(depth == M) {
            for(int n : ar) 
                sb.append(n).append(" ");
            sb.append("\n");
            return;
        }

        for(int i=0; i<N; i++) {
            ar[depth] = numbers[i];
            dfs(depth + 1);
        }
    }

}