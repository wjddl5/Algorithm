class Solution {
    public int solution(int n) {
        int answer = 0;
        
        int[] ar = new int[n+1];
        int l = 1;
        int r = 1;
        
        for(int i=1; i<=n; i++) {
            ar[i] = i;
        }
        
        while(true) {
            if(l>n) break;
            int sum = 0;
            for(int i=l; i<=r; i++) {
                sum += ar[i];
            }
            if(sum < n) r++;
            else if(sum > n) l++;
            else {
                l++;
                answer ++;
            }
        }
        
        return answer;
    }
}