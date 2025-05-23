import java.io.*;
import java.util.*;

public class Main {
    static int N, score;
    static int[][] map;
    static int[] dx={-1,0,1,0},dy={0,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
          
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        //M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        find();
        System.out.println(score);
    }

    static void find() {
        while(true) {
            ArrayList<int[]> list = findGroup();
            if(list == null) break;

            removeMap(list);
            score += Math.pow(list.size(), 2);
            gravityMap();
            rotationMap();
            gravityMap();
        }
    }

    static ArrayList<int[]> findGroup() {
        ArrayList<int[]> list = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visit = new boolean[N][N];
        int rainbow = 0;
        int[] gijun = {0, 0};

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visit[i][j] || map[i][j] < 1) continue;
                
                ArrayList<int[]> tmplist = new ArrayList<>();
                ArrayList<int[]> rainbowlist = new ArrayList<>();
                tmplist.add(new int[] {i, j});
                queue.offer(new int[] {i, j});
                visit[i][j] = true;
                int tmpRainbow = 0;
                int num = map[i][j];
                int[] tmpgijun = new int[] {i, j};

                while(!queue.isEmpty()) {
                    int[] tmp = queue.poll();
                    int x = tmp[0];
                    int y = tmp[1];

                    for(int k=0; k<4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if(nx<0 || ny<0 || nx>=N || ny>=N || visit[nx][ny] || (map[nx][ny] != 0 && map[nx][ny] != num)) continue;

                        tmplist.add(new int[] {nx, ny});
                        queue.offer(new int[] {nx, ny});
                        visit[nx][ny] = true;
                        if(map[nx][ny] == 0) {
                            tmpRainbow++;
                            rainbowlist.add(new int[] {nx, ny});
                        }
                        else {
                            if(tmpgijun[0] > nx ||
                            tmpgijun[0] == nx && tmpgijun[1] > ny) {
                                tmpgijun[0] = nx;
                                tmpgijun[1] = ny;
                            }
                        }
                    }
                }
                
                if(rainbowlist.size() > 0) {
                    for(int[] ar : rainbowlist) {
                        visit[ar[0]][ar[1]] = false;                        
                    }
                }

                if(list.size() < tmplist.size()) {
                    list.clear();
                    for(int[] ar : tmplist) {
                        list.add(ar);
                    }
                    rainbow = tmpRainbow;
                    gijun[0] = tmpgijun[0];
                    gijun[1] = tmpgijun[1];
                } else if (list.size() == tmplist.size()){                 
                    if (rainbow < tmpRainbow ||
                    (rainbow == tmpRainbow && gijun[0] < tmpgijun[0]) ||
                    (rainbow == tmpRainbow && gijun[0] == tmpgijun[0] && gijun[1] < tmpgijun[1])) {
                        
                        list.clear();
                        for(int[] ar : tmplist) {
                            list.add(ar);
                        }
                        rainbow = tmpRainbow;
                        gijun[0] = tmpgijun[0];
                        gijun[1] = tmpgijun[1];
                    }

                }
            }
        }
        if(list.size() < 2) return null;
        return list;
    }

    static void removeMap(ArrayList<int[]> list) {
        for(int[] ar : list) {
            map[ar[0]][ar[1]] = -2;
        }
    }

    static void gravityMap() {
        for(int i=N-2; i>=0; i--) {
            for(int j=0; j<N; j++) {
                if(map[i][j] < 0) continue;

                int nx = i+1;
                while(nx < N) {
                    if(map[nx][j] > -2) break;
                    nx++;
                }
                nx--;
                if(i != nx) {
                    map[nx][j] = map[i][j];
                    map[i][j] = -2;
                }
            }  
        }
    }

    static void rotationMap() {
        int[][] tmpMap = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                tmpMap[i][j] = map[j][N-i-1];
            }
        }
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                map[i][j] = tmpMap[i][j];
            }
        }
    }

}