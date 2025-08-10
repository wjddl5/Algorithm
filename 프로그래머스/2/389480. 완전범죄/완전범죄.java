import java.util.*;

class Solution {
    
    public int solution(int[][] info, int n, int m) {
        int INF = 1_000_000_000;
        int N = info.length;
        int[][] dp = new int[N+1][m+1];
        
        for(int i=0; i<=N; i++) 
            Arrays.fill(dp[i], INF);
        
        dp[0][0] = 0;
        
        for(int i=1; i<=N; i++) {
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j=0; j<m; j++) {
                // a
                int aSteal = Math.min(dp[i][j], dp[i-1][j] + a);
                if(dp[i][j] > aSteal) {
                    dp[i][j] = aSteal;
                }
                
                // b
                if(j+b < m) {
                    int bSteal = Math.min(dp[i][j+b], dp[i-1][j]);
                    if(dp[i][j+b] > bSteal) {
                        dp[i][j+b] = bSteal;
                    }
                }
            }
        }
        int answer = INF;
        for(int j=0; j<m; j++)
            answer = Math.min(answer, dp[N][j]);
        
        return answer < n ? answer : -1;
        
    }

}