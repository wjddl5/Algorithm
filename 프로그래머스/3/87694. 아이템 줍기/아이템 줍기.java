import java.util.*;

class Solution {
    
    int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1}, dy = {0, 1, 0, -1,  -1, 1, -1, 1};
    int[][] map = new int[101][101];
    boolean[][] visit = new boolean[101][101];
    
    public int solution(int[][] rectangle, int characterY, int characterX, int itemY, int itemX) {
        int answer = 0;
        characterX *= 2;
        characterY *= 2;
        itemX *= 2;
        itemY *= 2;
        
        for(int[] rec : rectangle) {
            setting(rec[1]*2, rec[0]*2, rec[3]*2, rec[2]*2);
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        
        queue.offer(new int[] {characterX, characterY, 0, 0});
        visit[characterX][characterY] = true;
        
        while(!queue.isEmpty()) {
            int[] ar = queue.poll();
            int x = ar[0];
            int y = ar[1];
            int cnt = ar[2];
            int chk = ar[3];
            
            if(x == itemX && y == itemY) {
                if(chk == 1) cnt--;
                answer = cnt;
                break;
            }
            
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx<0 || ny<0 || nx>100 || ny>100 || visit[nx][ny] || map[nx][ny] == 0) continue;
                
                if(isTruePoint(nx, ny)) {
                    if(chk == 0) {cnt += 1; chk = 1;}
                    else chk = 0;
                    
                    queue.offer(new int[] {nx, ny, cnt, chk});
                    visit[nx][ny] = true;
                }
            }
        }
        return answer;
    }
    
    public boolean isTruePoint(int x, int y) {
        for(int k=0; k<8; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>100 || ny>100 || map[nx][ny] == 0) return true; 
        }
        return false;
    }
    
    public void setting(int sx, int sy, int ex, int ey) {
        for(int i=sx; i<=ex; i++) {
            for(int j=sy; j<=ey; j++) {
                map[i][j] = 1;
            }
        }
    }
}