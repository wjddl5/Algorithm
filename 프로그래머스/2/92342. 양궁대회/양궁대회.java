import java.util.*;

class Solution {
    
    int max = -1;
    int[] lion, answer;
    
    public int[] solution(int n, int[] info) {
        answer = new int[] {-1};
        lion = new int[11];
        
        dfs(0, 0, n, info);
        
        return answer;
    }
    
    public void dfs(int depth, int count, int n, int[] info) {
        if(depth == 11) {
            if(count < n) lion[10] = n - count;
            getAnswer(info);
            if(count < n) lion[10] = 0;;
            return;
        }
        if(count + info[depth] + 1 <= n) {
            lion[depth] = info[depth] + 1;
            dfs(depth + 1, count + info[depth] + 1, n, info);
            lion[depth] = 0;
        } 
        dfs(depth + 1, count, n, info);
        
    }
    
    public void getAnswer(int[] info) {
        int apeachScore = 0, lionScore = 0;
        for(int i=0; i<11; i++) {
            if(info[i] == 0 && lion[i] == 0) continue;
            if(lion[i] > info[i]) lionScore += (10 - i);
            else apeachScore += (10 - i);
        }
        int diff = lionScore - apeachScore;
        if(diff > 0) {
            if(max < diff) {
                max = diff;
                answer = lion.clone();
            } else if(diff == max){
                if(isBetter()) {
                    answer = lion.clone();
                }
            }
        } 
    }
    
    public boolean isBetter() {
        for(int i=10; i>=0; i--) {
            if(lion[i] > answer[i]) return true;
            if(lion[i] < answer[i]) return false;
        }
        return false;
    }
}