import java.io.*;
import java.util.*;

public class Main {
    static int N, min=Integer.MAX_VALUE;
    static int[][] house, cost;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;      

        N = Integer.parseInt(br.readLine());

        house = new int[N][3];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dp();
        System.out.println(min);
    }
    
    static void dp() {
        for(int start=0; start<3; start++) {
            cost = new int[N][3];

            Arrays.fill(cost[0], 1001);
            cost[0][start] = house[0][start];

            for(int i=1; i<N; i++) {
                cost[i][0] = house[i][0] + Math.min(cost[i-1][1], cost[i-1][2]);
                cost[i][1] = house[i][1] + Math.min(cost[i-1][0], cost[i-1][2]);
                cost[i][2] = house[i][2] + Math.min(cost[i-1][0], cost[i-1][1]);
            }

            for(int i=0; i<3; i++) {
                if(i == start) continue;
                min = Math.min(min, cost[N-1][i]);
            }
        }
    }

}