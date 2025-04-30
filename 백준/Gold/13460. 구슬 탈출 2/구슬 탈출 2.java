import java.io.*;
import java.util.*;

public class Main {
    static int minCount=Integer.MAX_VALUE;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][M];
        
        String line = null;
        Ball redBall = null, blueBall = null;
        for(int i=0; i<N; i++) {
            line = br.readLine();
            for(int j=0; j<M; j++) {
                char c = line.charAt(j);
                map[i][j] = c;
                switch (c) {
                    case 'R':
                        redBall = new Ball('R', i, j);
                        break;
                    case 'B':
                        blueBall = new Ball('B', i, j);
                        break;
                }
            }
        }
        turnMap(map, redBall, blueBall, 1);
        System.out.println(minCount==Integer.MAX_VALUE ? -1 : minCount);
    }

    static void turnMap(char[][] o_map, Ball o_redBall, Ball o_blueBall, int turnCount) {
        if(turnCount > 10) return;
        for(int dir=0; dir<4; dir++) {
            char[][] map = copyMap(o_map);
            Ball redBall = new Ball(o_redBall.color, o_redBall.x, o_redBall.y);
            Ball blueBall = new Ball(o_blueBall.color, o_blueBall.x, o_blueBall.y);
            switch(dir) {
                case 0:
                    if(redBall.x < blueBall.x) {
                        moveBall(map, redBall, dir, turnCount);
                        moveBall(map, blueBall, dir, turnCount);
                    } else {
                        moveBall(map, blueBall, dir, turnCount);
                        moveBall(map, redBall, dir, turnCount);
                    }
                    break;
                case 1:
                    if(redBall.y > blueBall.y) {
                        moveBall(map, redBall, dir, turnCount);
                        moveBall(map, blueBall, dir, turnCount);
                    } else {
                        moveBall(map, blueBall, dir, turnCount);
                        moveBall(map, redBall, dir, turnCount);
                    }
                    break;
                case 2:
                    if(redBall.x > blueBall.x) {
                        moveBall(map, redBall, dir, turnCount);
                        moveBall(map, blueBall, dir, turnCount);
                    } else {
                        moveBall(map, blueBall, dir, turnCount);
                        moveBall(map, redBall, dir, turnCount);
                    }
                    break;
                case 3:
                    if(redBall.y < blueBall.y) {
                        moveBall(map, redBall, dir, turnCount);
                        moveBall(map, blueBall, dir, turnCount);
                    } else {
                        moveBall(map, blueBall, dir, turnCount);
                        moveBall(map, redBall, dir, turnCount);
                    }
                    break;
            }
            if(map[blueBall.x][blueBall.y] == 'O') continue;
            if(o_redBall.x==redBall.x && o_redBall.y==redBall.y && o_blueBall.x==blueBall.x && o_blueBall.y==blueBall.y) continue;
            if(map[blueBall.x][blueBall.y] != 'O' && map[redBall.x][redBall.y] == 'O') {
                minCount = Math.min(minCount, turnCount);
                return;
            }
            turnMap(map, redBall, blueBall, turnCount + 1);
        }
    }



    static void moveBall(char[][] map, Ball ball, int dir, int count) {
        int x = ball.x;
        int y = ball.y;
        map[x][y] = '.';
        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(map[x][y] == 'O') {  
                ball.x = x;
                ball.y = y;
                return;
            }
            if(map[x][y] != '.') {
                ball.x = x - dx[dir];
                ball.y = y - dy[dir];
                map[ball.x][ball.y] = ball.color;
                return;
            }
        }
    }

    static char[][] copyMap(char[][] o_map) {
        char[][] map = new char[o_map.length][o_map[0].length];
        for(int i=0; i<o_map.length; i++) {
            for(int j=0; j<o_map[0].length; j++) {
                map[i][j] = o_map[i][j];
            }
        }
        return map;
    }

}

class Ball {
    char color;
    int x;
    int y;
    Ball(char color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }
}
