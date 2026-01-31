import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        long low = 0;
        long high = (long) n * times[times.length - 1];
        
        while(low <= high) {
            long mid = low + (high - low) / 2;
            
            long cnt = 0;
            for(int time : times) {
                cnt += mid / time;
                if(cnt >= n) break;
            }
            
            if(cnt < n) {
                low = mid + 1;
            } else {
                answer = mid;
                high = mid - 1;      
            }
        }
        
        return answer;
    }
}