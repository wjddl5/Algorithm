import java.util.*;

class Solution {
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
    
    public int solution(int[][] land) {
        int N = land.length;
        int M = land[0].length;
        int max = 0;
        int[] oil = new int[M];
        boolean[][] visit = new boolean[N][M];
        
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                if(visit[i][j] || land[i][j] == 0) continue;
                
                Queue<int[]> queue = new ArrayDeque<>();
                Set<Integer> set = new HashSet<>();
                int cnt = 1;
                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                set.add(j);

                while(!queue.isEmpty()){
                    int[] ar = queue.poll();
                    int x = ar[0];
                    int y = ar[1];

                    for(int k=0; k<4; k++){
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || ny<0 || nx>=N || ny>=M || visit[nx][ny] || land[nx][ny]==0) continue;

                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        set.add(ny);
                        cnt++;
                    }
                }

                for(int col : set) {
                    oil[col] += cnt;
                }
            }
        }
        
        for(int o : oil) {
            max = Math.max(max, o);
        }
        
        return max;
    }
 
}