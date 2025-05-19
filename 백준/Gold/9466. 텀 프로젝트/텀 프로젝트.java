import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static int[] ar;
    static boolean[] visit, team;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb =  new StringBuilder();

        int T =  Integer.parseInt(br.readLine());
        while(T-- > 0) {
            count = 0;
            N = Integer.parseInt(br.readLine());
            ar = new int[N+1];
            visit = new boolean[N+1];
            team = new boolean[N+1];

            st = new StringTokenizer(br.readLine());
            for(int i=1; i<=N; i++) {
                ar[i] = Integer.parseInt(st.nextToken());
            }

            find();
            sb.append(N-count).append("\n");
        }
        System.out.println(sb);                      
    }

    static void find() {
        for(int i=1; i<=N; i++) {
            if(!visit[i]) dfs(i);
        }
    }
    
    static void dfs(int num) {
        visit[num] = true;
        int next = ar[num];

        if(!visit[next]) {
            dfs(next);
        }
        else if(!team[next]) {
            for(int i=next; i!=num; i=ar[i]) {     
                count++;
            }
            count++;
        }    
        team[num] = true;
    }

}