import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visit = new boolean[n];
        
        for(int i=0; i<n; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            queue.offer(i);
            answer++;
                   
            while(!queue.isEmpty()) {
                int node = queue.poll();
                
                for(int j=0; j<n; j++) {
                    if(computers[node][j] == 0 || visit[j]) continue;
                    visit[j] = true;
                    queue.offer(j);
                }
            }
        }
        
        return answer;
    }
}