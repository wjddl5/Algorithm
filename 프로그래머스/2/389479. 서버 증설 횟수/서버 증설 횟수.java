class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        int serverCount = 1;
        int[] serverTimes = new int[k];
        
        for(int player : players) {
            
            serverCount -= serverTimes[0];
            
            for(int i=1; i<k; i++) {
                serverTimes[i-1] = serverTimes[i];
            }
            serverTimes[k-1] = 0;
            
            if(serverCount * m <= player) {
                int newServer = (player - serverCount * m) / m + 1;
                serverTimes[k-1] = newServer;
                serverCount += newServer;
                answer += newServer;
            }
        }

        return answer;
    }
}