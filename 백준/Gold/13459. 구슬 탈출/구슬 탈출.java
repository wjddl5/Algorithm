import java.io.*;
import java.util.*;

public class Main {
    static int N, M, targetX, targetY, answer;
    static char[][] map;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        BallInfo ball = new BallInfo(0, 0, 0, 0);

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                if(c == 'R') {
                    ball.redX = i;
                    ball.redY = j;
                    c = '.';
                } else if(c == 'B') {
                    ball.blueX = i;                    
                    ball.blueY = j;                    
                    c = '.';
                } else if(c == 'O') {
                    targetX = i;                    
                    targetY = j;                    
                    c = '.';
                }
                map[i][j] = c;
            }
        }

        find(ball, 1);
        System.out.println(answer);
    }

    static void find(BallInfo oriball, int count) {
        if(count > 10) return;

        for(int k=0; k<4; k++) {
            BallInfo ball = new BallInfo(oriball.redX, oriball.redY, oriball.blueX, oriball.blueY);
            switch (k) {
                case 0:
                    if(ball.redX < ball.blueX) {
                        ball = moveRed(k, ball);
                        ball = moveBlue(k, ball);
                    } else {
                        ball = moveBlue(k, ball);
                        ball = moveRed(k, ball);
                    }
                    break;
                case 1:
                    if(ball.redY > ball.blueY) {
                        ball = moveRed(k, ball);
                        ball = moveBlue(k, ball);
                    } else {
                        ball = moveBlue(k, ball);
                        ball = moveRed(k, ball);
                    }
                    break;
                case 2:
                    if(ball.redX > ball.blueX) {
                        ball = moveRed(k, ball);
                        ball = moveBlue(k, ball);
                    } else {
                        ball = moveBlue(k, ball);
                        ball = moveRed(k, ball);
                    }
                    break;
                case 3:
                    if(ball.redY < ball.blueY) {
                        ball = moveRed(k, ball);
                        ball = moveBlue(k, ball);
                    } else {
                        ball = moveBlue(k, ball);
                        ball = moveRed(k, ball);
                    }
                    break;
            }
            if(ball.redX == -1 && ball.blueX != -1) {
                answer = 1;
                return;
            }
            if(ball.blueX == -1 || isStop(ball, oriball)) continue;
            find(ball, count+1);     
        }
    }

    static BallInfo moveBlue(int k, BallInfo ball) {
        int bx = ball.blueX;
        int by = ball.blueY;
        while(true) {
            int i = 1;
            bx += dx[k]*i;
            by += dy[k]*i;
            i++;
            if(isOutOfBound(bx, by) || map[bx][by]=='#' || (bx==ball.redX && by==ball.redY)) {
                bx -= dx[k];
                by -= dy[k];
                break;
            }
            if(bx==targetX && by==targetY) {
                bx = -1;
                break;
            }
        }
        ball.blueX = bx;
        ball.blueY = by;
        return ball;
    }

    static BallInfo moveRed(int k,BallInfo ball) {
        int rx = ball.redX;
        int ry = ball.redY;
        while(true) {
            int i = 1;
            rx += dx[k]*i;
            ry += dy[k]*i;
            i++;
            if(isOutOfBound(rx, ry) || map[rx][ry]=='#' || (rx==ball.blueX && ry==ball.blueY)) {
                rx -= dx[k];
                ry -= dy[k];
                break;
            }
            if(rx==targetX && ry==targetY) {
                rx = -1;
                break;
            }
        }
        ball.redX = rx;
        ball.redY = ry;
        return ball;
    }

    static boolean isStop(BallInfo oriBall, BallInfo ball) {
        return (oriBall.redX == ball.redX
            && oriBall.redY == ball.redY
            && oriBall.blueX == ball.blueX
            && oriBall.blueY == ball.blueY
        );
    }

    static boolean isOutOfBound(int x, int y) {
        return (x<0 || y<0 || x>=N || y>=M);
    }

}

class BallInfo {
    int redX, redY, blueX, blueY;
    BallInfo(int redX, int redY, int blueX, int blueY) {
        this.redX = redX;
        this.redY = redY;
        this.blueX = blueX;
        this.blueY = blueY;
    } 
}
