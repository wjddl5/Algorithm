import java.util.*;

class Solution {
    
    int N, M, answer = Integer.MAX_VALUE;
    int[] dx = {-1, 0, 1, 0, 0}, dy = {0, 1, 0, -1 , 0};
    int[][] map;
    boolean[][] red_visit, blue_visit;
    
    public int solution(int[][] maze) {
        
        int rx = 0, ry = 0, bx = 0, by = 0;
        N = maze.length;
        M = maze[0].length;
        
        map = new int[N][M];
        red_visit = new boolean[N][M];
        blue_visit = new boolean[N][M];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                switch(maze[i][j]) {
                    case 1:
                        rx = i;
                        ry = j;
                        break;
                    case 2:
                        bx = i;
                        by = j;
                        break;
                    default :
                        map[i][j] = maze[i][j];
                }
            }
        }
        
        red_visit[rx][ry] = true;
        blue_visit[bx][by] = true;
        
        dfs(rx, ry, bx, by, 0);
        
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }
    
    public void dfs(int rx, int ry, int bx, int by, int day) {
        if(isEnd(rx, ry, 'R') && isEnd(bx, by, 'B')) {
            answer = Math.min(answer, day);
            return;
        } // 완료

        
        for(int i=0; i<5; i++) {   
            if(isEnd(rx, ry, 'R')) i = 4; // 도착지점 => 제자리 멈춤 유지
            
            int nrx = rx + dx[i];
            int nry = ry + dy[i];
            
            if(isOutBound(nrx, nry)) continue;
            if(!isEnd(rx, ry, 'R') && !isCanMove(nrx, nry, i, 'R')) continue;
                
            for(int j=0; j<4; j++) {
                if(isEnd(bx, by, 'B')) j = 4;
                
                int nbx = bx + dx[j];
                int nby = by + dy[j];
                
                if(isOutBound(nbx, nby)) continue;
                if(!isEnd(bx, by, 'B') && !isCanMove(nbx, nby, j, 'B')) continue;
                if((nrx == nbx && nry == nby) || (nrx == bx && nry == by && nbx == rx && nby == ry)) continue; // 같은 자리 겹침 or 서로 위치 교환
                
                red_visit[nrx][nry] = true;
                blue_visit[nbx][nby] = true;
                dfs(nrx, nry, nbx, nby, day+1);
                red_visit[nrx][nry] = false;
                blue_visit[nbx][nby] = false;
            }
        }
    }
    
    public boolean isEnd(int x, int y, char color) {
        if(color == 'R' && map[x][y] == 3) return true;
        if(color == 'B' && map[x][y] == 4) return true;
        return false;
    }
    
    public boolean isCanMove(int x, int y, int k, char color) {
        if(isOutBound(x, y)) return false;
        if(color == 'R' && red_visit[x][y]) return false;
        if(color == 'B' && blue_visit[x][y]) return false;
        if(map[x][y] == 5) return false;

        return true;
    }
    
    public boolean isOutBound(int x, int y) {
        return x<0 || y<0 || x>=N || y>=M;
    }
    
}