import java.io.*;
import java.util.*;

public class Main {

    static int R, C;
    static int[] play;
    static char[][] map;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];

        for(int i=0; i<R; i++) {
            String str = br.readLine();
            for(int j=0; j<C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int cnt = Integer.parseInt(br.readLine());
        play = new int[cnt];
        st = new StringTokenizer(br.readLine());

        for(int i=0; i<cnt; i++) {
            play[i] = Integer.parseInt(st.nextToken());
        }

        find();
        answer();
    }

    static void find() {
        boolean player = true;
        for(int p : play) {
            if(player) { // 왼쪽
                macdegi(p, 0, player); // 막대기 던지기
                setBlock();
            } else { // 오른쪽
                macdegi(p, C-1, player);
                setBlock();
            }

            player = !player;
        }
    }

    static void macdegi(int h, int c, boolean isLeft) {
        while(true) {
            if(c >= C || c < 0) break;
            if(map[R-h][c] == 'x') {
                map[R-h][c] = '.';
                break;
            }
            if(isLeft) c++;
            else c--;
        }
    }

    static void setBlock() {
        // 가장 밑바닥에 붙어있는 블럭은 고정
        for(int j=0; j<C; j++) {
            if(map[R-1][j] == 'x') noDown(R-1, j);
        }
       
        down();

        // 고정 처리 한 블럭 원상복귀
        for(int i=0; i<R; i++) { 
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'y') {
                    map[i][j] = 'x';
                }
            }
        }
    }

    static void noDown(int sx, int sy) {
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[R][C];
        
        queue.offer(new int[] {sx, sy});
        visit[sx][sy] = true;
        map[sx][sy] = 'y'; // 움직이지 않는 블럭으로 등록

        while(!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];

            for(int k=0; k<4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(isOutOfBound(nx, ny) || visit[nx][ny] || map[nx][ny] == '.') continue;
                
                queue.offer(new int[] {nx, ny});
                visit[nx][ny] = true;
                map[nx][ny] = 'y';
            }
        }
        
    }

    static void down() {
        ArrayList<int[]> list = new ArrayList<>(); // 붙어있는 블럭 리스트

        for(int i=R-1; i>=0; i--) { 
            for(int j=0; j<C; j++) {
                if(map[i][j] == 'x') {
                    list.add(new int[] {i, j});
                    
                }
            }
        }

        downBlock(list);
    }

    static void downBlock(ArrayList<int[]> list) {
        ArrayList<int[]> tmp_list = new ArrayList<>();
        while(list.size() > 0 && !isFloor(list)) {
            for(int i=0; i<list.size(); i++) {
                int[] ar = list.get(i);
                int x = ar[0];
                int y = ar[1];
                map[x][y] = '.';
                map[x+1][y] = 'x';
                tmp_list.add(new int[] {x+1, y});
            }
            list.clear();
            for(int[] ar : tmp_list) list.add(ar);
            tmp_list.clear();
        }
    }

    static boolean isFloor(ArrayList<int[]> list) {
        for(int[] ar : list) {
            // 바닥에 닿았거나, 고정 블럭에 닿았거나
            if(ar[0] + 1 >= R || map[ar[0] + 1][ar[1]] == 'y') return true;
        }
        return false;
    }

    static boolean isOutOfBound(int x, int y) {
        return x<0 || y<0 || x>=R || y>=C;
    }

    static void answer() {
        StringBuilder sb = new StringBuilder();
        for(char[] tmp : map) {
            for(char c : tmp) {
                sb.append(c);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    
}