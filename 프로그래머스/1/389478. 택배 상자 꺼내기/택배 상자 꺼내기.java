import java.util.*;

class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        int[] box = new int[w];
        
        Arrays.fill(box, n/w);
        
        if(n/w % 2 == 0) {
            for(int i=0; i<n%w; i++) box[i]++;
        } else {
            for(int i=w-1; i>=w-(n%w); i--) box[i]++;
        }
        
        int cnt = num / w;
        int point = num % w;
        
        if(cnt % 2 == 0) {
            if(point == 0) {
                point = 1;
                cnt--;
            } 
            answer = box[point-1] - cnt;
        } else {
            if(point == 0) {
                point = 1;
                cnt--;
            } 
            answer = box[w-point] - cnt;
        }
        
        return answer;
    }
}