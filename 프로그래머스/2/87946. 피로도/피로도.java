import java.util.*;

class Solution {
    
    int dungeonCount, max;
    int[] dungeonSelect;
    boolean[] visit;
    
    public int solution(int k, int[][] dungeons) {
        dungeonCount = dungeons.length;
        dungeonSelect = new int[dungeonCount];
        visit = new boolean[dungeonCount];
        
        perm(0, k, dungeons);
        
        return max;
    }
    
    void perm(int depth, int k, int[][] dungeons) {
        if(depth == dungeonCount) {
            find(k, dungeons);
            return;
        }
        for(int i=0; i<dungeonCount; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            dungeonSelect[depth] = i;
            perm(depth + 1, k, dungeons);
            visit[i] = false;
        }  
    }
    
    void find(int k, int[][] dungeons) {
        int cnt = 0;
        for(int i : dungeonSelect) {
            int start = dungeons[i][0];
            int p = dungeons[i][1];
            if(k >= start) {
                k -= p;
                cnt++;
            }
        }
        max = Math.max(max, cnt);
    }
}