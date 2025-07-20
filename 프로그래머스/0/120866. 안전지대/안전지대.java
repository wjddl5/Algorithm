class Solution {
    public int solution(int[][] board) {
        int[] dx = {-1,-1,-1, 0,1,1,1,0};
        int[] dy = {-1,0,1,1,1,0,-1,-1};
        int n = board.length;
        int m = board[0].length;
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            find:
            for(int j=0; j<m; j++) {
                if(board[i][j] == 1) continue;
                for(int k=0; k<8; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                    if(board[nx][ny] == 1) continue find;
                }
                answer++;
            }
        }
        return answer;
    }
}