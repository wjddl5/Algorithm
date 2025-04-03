import java.io.*;
import java.util.*;
 
public class Solution {
 
    static int N, maxCore, minLine;
    static ArrayList<int[]> list;
    static boolean[][] visit;
    static int[] dx={-1,0,1,0}, dy={0,1,0,-1};
     
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T  = Integer.parseInt(br.readLine());
 
        for(int tc=1; tc<=T; tc++) {
            N = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            visit = new boolean[N][N];
            maxCore = 0;
            minLine  = Integer.MAX_VALUE;
 
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++)  {
                    if(Integer.parseInt(st.nextToken()) == 0) continue;
                    visit[i][j] = true; 
                    if(i==0 || j==0 || i==N-1 || j==N-1) continue;
                    list.add(new int[] {i, j});
                }
            }
            dfs(0,0,0);
            sb.append("#").append(tc).append(" ").append(minLine).append("\n");
        }
        System.out.println(sb);
    }
 
    public static void dfs(int index, int core, int line) {
        if(index == list.size()) {
            if(maxCore == core && minLine > line) minLine = line; 
            if(maxCore < core) {
                maxCore = core;
                minLine = line;
            }
            return;
        }
        int x = list.get(index)[0];
        int y = list.get(index)[1];
         
        for(int k=0; k<4; k++) {
            int size = getSize(x, y, k);
            if(size<0) continue;
            dfs(index+1, core+1, line+size);
            back(x, y, k);
        }
        dfs(index+1, core, line);
    }
 
    public static void back(int x, int y, int k) {
        while(true) {
            x += dx[k];
            y += dy[k];
            if(!inOfBound(x, y)) break;
            visit[x][y] = false;
        }
    }
 
    public static int getSize(int x, int y, int k) {
        int size = 0;
        int tmpx = x;
        int tmpy = y;
 
        while(true)  {
            tmpx += dx[k];
            tmpy += dy[k];
            if(!inOfBound(tmpx, tmpy)) break;
 
            if(visit[tmpx][tmpy]) {
                while(true) {
                    tmpx -= dx[k];
                    tmpy -= dy[k];
                    if(tmpx==x && tmpy==y) break;
                    visit[tmpx][tmpy] = false;
                }
                return -1;
            }
            else {
                visit[tmpx][tmpy] = true;
                size++;
            } 
        }
        return size;
    }
 
    public static boolean inOfBound(int x, int y) {
        if(x<0 || y<0 || x>=N || y>=N) return false;
        return true;
    }
 
}