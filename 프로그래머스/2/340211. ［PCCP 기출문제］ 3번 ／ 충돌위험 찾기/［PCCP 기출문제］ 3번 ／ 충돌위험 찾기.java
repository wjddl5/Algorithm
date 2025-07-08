import java.util.*;

class Solution {
    
    Map<String, Integer> visit = new HashMap<>();
    int answer;
    
    public int solution(int[][] points, int[][] routes) {
        for(int[] route : routes) {
            int cnt = 0;
            if (isFirstVisit(points[route[0] -1][0], points[route[0] -1][1], cnt)) answer++;
            
            for(int i = 1; i < route.length; i++) {
                int[] start = points[route[i-1] -1];
                int[] end = points[route[i] -1];
                
                cnt = move(start[0], start[1], end[0], end[1], cnt);
            }
        }
        return answer;
    }
    
    public int move(int x, int y, int ex, int ey, int cnt) {
        while (true) {   
            if (x == ex && y == ey) break;

            if (x < ex) x++;
            else if (x > ex) x--;
            else if (y < ey) y++;
            else if (y > ey) y--;
            cnt++;   
            if (isFirstVisit(x, y, cnt)) answer++;
        }
        return cnt;
    }
    
    public boolean isFirstVisit(int x, int y, int cnt) {
        String key = x + "," + y + "," + cnt;
        int count = visit.getOrDefault(key, 0) + 1;
        visit.put(key, count);
        return count == 2;
    }
    
}