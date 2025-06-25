import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map; 
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        Ball ball = new Ball(0, 0, 0, 0, 0, "");

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<M; j++) {
                char c = str.charAt(j);
                switch(c) {
                    case 'R':
                        ball.rx = i;
                        ball.ry = j;
                        map[i][j] = '.';
                        break;
                    case 'B':
                        ball.bx = i;
                        ball.by = j;
                        map[i][j] = '.';   
                        break;
                    default:
                        map[i][j] = c;
                }
            }
        }
        find(ball);
        System.out.println(-1);
    }

    static void find(Ball startBall) {
        Queue<Ball> queue = new ArrayDeque<>();
        Set<String> visit = new HashSet<>();

        queue.offer(startBall);
        visit.add(startBall.rx+","+startBall.ry+","+startBall.bx+","+startBall.by);

        while(!queue.isEmpty()) {
            Ball ball = queue.poll();
            if(ball.count >= 10) continue;

            for(int k=0; k<4; k++) {
                Ball newBall = null;
                if(isRedFirst(ball, k)) {
                    newBall = moveBlue(moveRed(ball, k), k);
                } else {
                    newBall = moveRed(moveBlue(ball, k), k);
                }

                String key = newBall.rx+","+newBall.ry+","+newBall.bx+","+newBall.by;
                if(isStop(ball, newBall) || visit.contains(key) || newBall.bx == -1) continue;
                
                newBall.count = ball.count + 1;;
                newBall.move = ball.move + k;

                if(newBall.rx == -1 && newBall.bx != -1) {
                    System.out.println(newBall.count);
                    print(newBall.move);
                    System.exit(0);
                }

                queue.offer(newBall);
                visit.add(key);       
            }
        }

    }

    static Ball moveRed(Ball ball, int dir) {
        int x = ball.rx;
        int y = ball.ry;
        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(map[x][y] == 'O') {
                x = -1;
                break;
            } else if(map[x][y] == '#' || (x==ball.bx && y==ball.by)) {
                x -= dx[dir];
                y -= dy[dir];
                break;
            }
        }
        return new Ball(x, y, ball.bx, ball.by, ball.count, ball.move);
    }

    static Ball moveBlue(Ball ball, int dir) {
        int x = ball.bx;
        int y = ball.by;
        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(map[x][y] == 'O') {
                x = -1;
                break;
            } else if(map[x][y] == '#' || (x==ball.rx && y==ball.ry)) {
                x -= dx[dir];
                y -= dy[dir];
                break;
            }
        }
        return new Ball(ball.rx, ball.ry, x, y, ball.count, ball.move);
    }

    static boolean isRedFirst(Ball ball, int dir) {
        boolean chk = false;
        switch (dir) {
            case 0: // U
                chk = ball.rx < ball.bx;
                break;
            case 1: // R
                chk = ball.ry > ball.by;
                break;
            case 2: // D
                chk = ball.rx > ball.bx;
                break;
            case 3: // L
                chk = ball.ry < ball.by;
                break;
        }
        return chk;
    }

    static boolean isStop(Ball oriBall, Ball ball) {
        return oriBall.rx == ball.rx &&
               oriBall.ry == ball.ry &&
               oriBall.bx == ball.bx &&
               oriBall.by == ball.by;
    }

    static void print(String move) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<move.length(); i++) {
            char c = move.charAt(i);
            switch (c) {
                case '0':
                    sb.append('U');
                    break;
                case '1':
                    sb.append('R');
                    break;
                case '2':
                    sb.append('D');
                    break;
                case '3':
                    sb.append('L');
                    break;
            }
        }
        System.out.println(sb);
    }

}

class Ball {
    int rx, ry, bx, by, count;
    String move;
    public Ball(int rx, int ry, int bx, int by, int count, String move) {
        this.rx = rx;
        this.ry = ry;
        this.bx = bx;
        this.by = by;
        this.count = count;
        this.move = move;
    }
    
}