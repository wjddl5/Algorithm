class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;
        int mid = 0;
        int n = diffs.length; 
        int answer = 100001;
        
        while(left <= right) {
            long total = 0;
            mid = (left + right) / 2;   
            
            for(int i=0; i<n; i++) {
                int diff = diffs[i];
                int time_cur = times[i];

                if(diff > mid) {
                    int time_prev = times[i-1];       
                    total += (diff-mid)*(time_prev+time_cur);
                }
                total += time_cur;
            }
            if(total > limit) left = mid + 1;
            else {
                answer = Math.min(answer, mid);
                right = mid - 1;
            }
        }
        return answer;
    }
}