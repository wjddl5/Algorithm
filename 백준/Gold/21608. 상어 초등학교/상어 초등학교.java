import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] map, student;
    static int[] order, dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        student = new int[N*N+1][4];
        order = new int[N*N];
        for(int i=0; i<N*N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            student[n][0] = Integer.parseInt(st.nextToken());
            student[n][1] = Integer.parseInt(st.nextToken());
            student[n][2] = Integer.parseInt(st.nextToken());
            student[n][3] = Integer.parseInt(st.nextToken());
            order[i] = n;
        }
        set();
        System.out.println(getAnswer());
    }

    public static void set() {
        for(int num : order) {
            int x=N-1, y=N-1, like=0, hole=0;

            for(int i=N-1; i>=0; i--) {
                for(int j=N-1; j>=0; j--) {
                    if(map[i][j] > 0) continue;

                    int tmpLike = likeCount(num, i, j);
                    if(like < tmpLike) {
                        like = tmpLike;
                        hole = holeCount(i, j);
                        x = i;
                        y = j;
                    }
                    else if(like == tmpLike) {
                        int tmpHole = holeCount(i, j);
                        if(hole < tmpHole) {
                            hole = tmpHole;
                            x = i;
                            y = j;
                        } else if(hole == tmpHole) {
                            if(x > i) {
                                x = i;
                                y = j;
                            } else if(x == i) {
                                if(y > j) {
                                    x = i;
                                    y = j;
                                }
                            }
                        }
                    }
                }
            }
            map[x][y] = num;
        }
    }

    public static int getAnswer() {
        int score = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                int cnt = likeCount(map[i][j], i, j);
                switch (cnt) {
                    case 1:
                        score += 1;
                        break;
                    case 2:
                        score += 10;
                        break;
                    case 3:
                        score += 100;
                        break;
                    case 4:
                        score += 1000;
                        break;
                }
            }
        }
        return score;
    }

    public static int likeCount(int num, int x, int y) {
        int cnt = 0;
        int[] likeAr = student[num];
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
            for(int n : likeAr) {
                if(map[nx][ny] == n) cnt++;
            }
        }
        return cnt;
    }
    
    public static int holeCount(int x, int y) {
        int cnt = 0;
        for(int k=0; k<4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(nx<0 || ny<0 || nx>=N || ny>=N) continue;
            if(map[nx][ny] == 0) cnt++;
        }
        return cnt;
    }   
    
}