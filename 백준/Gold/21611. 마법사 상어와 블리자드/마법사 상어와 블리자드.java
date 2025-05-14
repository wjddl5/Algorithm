import java.io.*;
import java.util.*;

public class Main {
    static int N, X, Y, total;
    static int[][] map, magic;
    static ArrayList<Integer> ballList;
    static int[] dx={0,1,0,-1}, dy={-1,0,1,0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        X = Y = ((N + 1) / 2) - 1;

        map = new int[N][N];
        magic = new int[m][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            magic[i][0] = Integer.parseInt(st.nextToken());
            magic[i][1] = Integer.parseInt(st.nextToken());
        }

        start();
        System.out.println(total);
    }

    static void start() {
        for(int[] m : magic) {
            int d = m[0] -1;
            int s = m[1];

            blizzard(d, s);
            setList();
            explosion();
            setGroup();
        }
    }

    static void blizzard(int d, int s) {
        int[] cx={-1,1,0,0}, cy={0,0,-1,1};
        int x = X, y = Y;;

        for(int i=0; i<s; i++) {
            x += cx[d];
            y += cy[d];
            map[x][y] = 0;
        }
    }

    static void setList() {
        ballList = new ArrayList<>();
        int x = X, y = Y;
        int k = 0;
        int le = 1;

        while(true) {
            for(int i=0; i<2; i++) {
                for(int j=0; j<le; j++) {
                    x += dx[k];
                    y += dy[k];
                    
                    if(x<0 || y<0) return;
                    if(map[x][y] > 0) ballList.add(map[x][y]);
                }
                k++;
                if(k == 4) k = 0;
            }
            le++;
        }
    }

    static void explosion() {
        ArrayList<Integer> removeList;
        ArrayList<Integer> tmpList;
        int i = 0;
        boolean chk = true;

        while(chk) {
            removeList = new ArrayList<>();
            chk = false;

            while(i < ballList.size()-3) {
                tmpList = new ArrayList<>();           
                int num = ballList.get(i);
                tmpList.add(i);

                int j=i+1;
                while(j<ballList.size() && ballList.get(j) == num) {
                    tmpList.add(j);
                    j++;
                }
                
                if(tmpList.size() >= 4) {
                    for(int n : tmpList) {
                        removeList.add(n);
                    }          
                } 

                i = j;
            }

            if(removeList.size() > 0) {
                removeList.sort(null);
                for(int j=removeList.size()-1; j>=0; j--) {
                    int n = removeList.get(j);
                    total += ballList.get(n);
                    ballList.remove(n);
                }
                i = 0; 
                chk = true;
            }
        }
    }
    
    static void setGroup() {
        ArrayList<Integer> newBallList = new ArrayList<>();
        int cnt = 0;
        int num = 0;

        for(int i=0; i<ballList.size(); i++) {
            if(ballList.get(i) == num) {
                cnt ++;
                continue;
            }
            else if(cnt == 0) {
                num = ballList.get(i);
                cnt = 1;
                continue;
            }
            newBallList.add(cnt);
            newBallList.add(num);
            
            cnt = 1;
            num = ballList.get(i);
        }
        newBallList.add(cnt);
        newBallList.add(num);
        setMap(newBallList);
    }

    static void setMap(ArrayList<Integer> list) {
        map = new int[N][N];
        int x = X, y = Y;
        int k = 0;
        int le = 1;
        int index = 0;

        while(true) {
            for(int i=0; i<2; i++) {
                for(int j=0; j<le; j++) {
                    x += dx[k];
                    y += dy[k];
                    
                    if(x<0 || y<0 || index >= list.size()) return;
                    map[x][y] = list.get(index);
                    index ++;
                }
                k++;
                if(k == 4) k = 0;
            }
            le++;
        }
    }

}