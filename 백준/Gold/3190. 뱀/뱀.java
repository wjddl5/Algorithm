import java.io.*;
import java.util.*;

public class Main {

    static int N, time=0;
    static int[][] map;
    static char[] control = new char[10001];
    static Deque<int[]> snake = new ArrayDeque<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        map[0][0] = 7;
        snake.offerFirst(new int[] {0, 0});

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())-1][Integer.parseInt(st.nextToken())-1] = 1;
        }

        int L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            control[time] = st.nextToken().charAt(0);
        }

        game();
        System.out.println(time);
    }

    public static void game() {
        int x=0; int y=0; char dir='R';
        boolean check = true;
        while(check) {
            if(time>0 && control[time]!=0) dir = turnSnake(dir, control[time]);
            
            switch (dir) {
                case 'U':
                x -= 1;
                break;
                case 'R':
                y += 1;
                break;
                case 'D':
                x += 1;
                break;
                case 'L':
                y -= 1;
                break;
            }
            time++;
            if(canMove(x, y)) {
                moveSnake(x, y);
            }
            else check = false;
        }
    }

    public static void moveSnake(int nx, int ny) {
        if(map[nx][ny] != 1) {
            int[] tail = snake.pollLast();
            map[tail[0]][tail[1]] = 0;
        }
        map[nx][ny] = 7;
        snake.offerFirst(new int[] {nx, ny});
    }

    public static char turnSnake(char dir, char turn) {
        char newDir = '0';
        if(turn == 'L') {
            switch (dir) {
            case 'U':
                newDir = 'L';
                break;
            case 'R':
                newDir = 'U';
                break;
            case 'D':
                newDir = 'R';
                break;
            case 'L':
                newDir = 'D';
                break;
            }
        }else {
            switch (dir) {
            case 'U':
                newDir = 'R';
                break;
            case 'R':
                newDir = 'D';
                break;
            case 'D':
                newDir = 'L';
                break;
            case 'L':
                newDir = 'U';
                break;
            }
        }
        return newDir;
    }

    public static boolean canMove(int nx, int ny) {
        if(nx<0 || ny<0 || nx>=N || ny>=N || map[nx][ny] == 7) return false;
        return true;
    }
}