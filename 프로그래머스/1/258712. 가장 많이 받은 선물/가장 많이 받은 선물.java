import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int num = 0;
        int N = friends.length;
        Map<String, Integer> map = new HashMap<>();
        int[][] arr = new int[N][N];
        int[] ar = new int[N];
        for(String s : friends) {
            map.put(s, num++);
        } 
        for(String str : gifts) {
            String[] s = str.split(" ");
            String from = s[0];
            String to = s[1];
            int f = map.get(from);
            int t = map.get(to);
            arr[f][t] += 1;
            ar[f] += 1;
            ar[t] -= 1;
        }
        for(int i=0; i<N; i++) {
            int cnt = 0;
            for(int j=0; j<N; j++) {
                if(i==j) continue;
                
                if(arr[i][j] > arr[j][i]) cnt++;
                else if(arr[i][j] == arr[j][i] && ar[i] > ar[j]) cnt++;                   
            }
            answer = Math.max(answer, cnt);
        }
        return answer;
    }
}