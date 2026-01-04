import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0; 
        
        PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());
        for(int work : works)
            q.offer(work);
        
        while(n-- > 0) {
            int t = q.poll();
            if(t == 0) break;
            q.offer(--t);
        }
        
        for(int t : q)
            answer += Math.pow(t, 2);
        
        return answer;
    }
}