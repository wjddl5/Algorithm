import java.io.*;
import java.util.*;

public class Main {

    static int N, min=Integer.MAX_VALUE;
    static int[][] sp;
    static int[] teamA;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        sp = new int[N][N];
        teamA = new int[N/2];
        visit = new boolean[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                sp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0);

        System.out.println(min);
    }

    static void dfs(int start, int depth) {
        if(depth == N/2) {
            getSp(getTeamB());
            return;
        }

        for(int i=start; i<N; i++) {
            if(visit[i]) continue;

            teamA[depth] = i;
            visit[i] = true;
            dfs(i+1, depth+1);
            visit[i] = false;
        }
    }

    static int[] getTeamB() {
        int[] teamB = new int[N/2];
        int index = 0;
        for(int i=0; i<N; i++) {
            if(visit[i]) continue;

            teamB[index] = i;
            index++;
        }
        return teamB;
    }

    static void getSp(int[] teamB) {
        int s1 = 0, s2 = 0;

        for(int i : teamA) {
            for(int j : teamA) {
                s1 += sp[i][j];
            }
        }

        for(int i : teamB) {
            for(int j : teamB) {
                s2 += sp[i][j];
            }
        }

        min = Math.min(min, Math.abs(s1 - s2));
    }
  
}