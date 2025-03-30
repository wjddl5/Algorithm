import java.io.*;
import java.util.*;

public class Main {

    static int N, M, H, answer=99;
    static boolean[][] ar;
    static ArrayList<int[]> list = new ArrayList<>();
    static int[][] garoAr = new int[3][2];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()) *2 -1;
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken()) *2 +1;

        ar = new boolean[H][N];

        for(int i=0; i<H ;i++) {
            for(int j=0; j<N; j+=2) {
                ar[i][j] = true;
            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) *2 -1;
            int c = Integer.parseInt(st.nextToken()) *2 -1;
            ar[r][c] = true;
        }

        for(int i=1; i<H; i+=2) {
            for(int j=1; j<N; j+=2) {
                if(ar[i][j]) continue;
                if(inOfBound(i, j-2) && ar[i][j-2]) continue;
                if(inOfBound(i, j+2) && ar[i][j+2]) continue;
                list.add(new int[] {i, j});
            }
        }

        for(int size=0; size<4; size++) {
            subs(size, 0);
            if(answer < 4) break;
        }

        System.out.println(answer > 3 ? -1 : answer);
    }

    public static void subs(int size, int cnt) {
        if (size >= answer) return;
        if(cnt == size) {
            setOnGaro();
            if(checkGaro() && check()) answer = size;
            setOffGaro();
            return;
        }  
        for(int i=cnt; i<list.size(); i++) {
            garoAr[cnt] = list.get(i);
            subs(size, cnt+1);
        }
    }
 
    public static void setOnGaro() {
        for(int[] line : garoAr) {
            if(line[0] == 0) continue;
            ar[line[0]][line[1]] = true;
        }
    }
    public static void setOffGaro() {
        for(int[] line : garoAr) {
            if(line[0] == 0) continue;
            ar[line[0]][line[1]] = false;
        }
    }

    public static boolean checkGaro() {
        for(int[] line : garoAr) {
            if(line[0] == 0) continue;
            int i  = line[0];
            int j  = line[1];
            if(inOfBound(i, j-2) && ar[i][j-2]) return false;
            if(inOfBound(i, j+2) && ar[i][j+2]) return false;
        }
        return true;
    }

    public static boolean check() {
        for(int j=0; j<N; j+=2) {
            int col = j;
            for(int i=0; i<H; i++) {
                if(inOfBound(i, col+1) && ar[i][col+1]) col += 2;
                else if(inOfBound(i, col-1) && ar[i][col-1]) col -= 2;
            }
            if(col != j) return false;
        }
        return true;
    }

    public static boolean inOfBound(int r, int c)  {
        if(r < 0 || c < 0 || r >= H || c >= N) return false;
        return true;
    }

}

