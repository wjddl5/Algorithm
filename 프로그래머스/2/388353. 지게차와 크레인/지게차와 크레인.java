import java.util.*;

class Solution {
    
    int N, M, answer;
    char[][] map;
    int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    
    public int solution(String[] storage, String[] requests) {
        N = storage.length;
        M = storage[0].length();
        answer = N * M;
        
        map = new char[N+2][M+2];
        for(int i=0; i<=N+1; i++)
            Arrays.fill(map[i], '.');
        
        for(int i=1; i<=N; i++) {
            String str = storage[i-1];
            for(int j=1; j<=M; j++) {
                map[i][j] = str.charAt(j-1);
            }
        }
        
        for(String s : requests) {
            if(s.length() == 1) bfs(s.charAt(0));
            else crane(s.charAt(0));
        }
        
        return answer;
    }
    
    void bfs(char target) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visit = new boolean[N+2][M+2];
        
        q.offer(new int[] {0, 0});
        visit[0][0] = true;
        
        while(!q.isEmpty()) {
            int[] xy = q.poll();
            int x = xy[0];
            int y = xy[1];
            
            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if(nx<0 || ny<0 || nx>N+1 || ny>M+1 || visit[nx][ny]) continue;
                if(map[nx][ny] == '.') {
                    q.offer(new int[] {nx, ny});
                    visit[nx][ny] = true;
                } else if(map[nx][ny] == target) {
                    map[nx][ny] = '.';
                    visit[nx][ny] = true;
                    answer--;
                }
            }
        }
    }
    
    void crane(char target) {
        for(int i=1; i<=N; i++) {
            for(int j=1; j<=M; j++) {
                if(map[i][j] == target) {
                    map[i][j] = '.';
                    answer--;
                }
            }
        }
    }
    
}