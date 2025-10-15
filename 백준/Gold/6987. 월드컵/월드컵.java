import java.io.*;
import java.util.*;

public class Main {

    static int[][] game, team;
    static boolean isGame; 
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        for(int tc=0; tc<4; tc++) {

            game = new int[6][3]; 
            team = new int[15][2];
            isGame = false;
            boolean chk = true;
            
            st = new StringTokenizer(br.readLine());

            for(int i=0; i<6; i++) {
                
                int win = Integer.parseInt(st.nextToken());
                int draw = Integer.parseInt(st.nextToken());
                int lose = Integer.parseInt(st.nextToken());

                game[i][0] = win;
                game[i][1] = draw;
                game[i][2] = lose;
                
                if(win > 5 || draw > 5 || lose > 5) chk = false;
            }

            int n = 0;
            for(int i=0; i<6; i++) {
                for(int j=i+1; j<6; j++) {
                    team[n][0] = i;
                    team[n][1] = j;
                    n++;
                }
            }
            // input

            if(chk) find(0);

            sb.append(isGame ? 1 : 0).append(" ");
        }

        System.out.println(sb);
    }

    static void find(int depth) {
        if(isGame) return;
        if(depth == 15) {
            isGame = true;
            return;
        }

        int i = team[depth][0];
        int j = team[depth][1];

        if(game[i][0] > 0 && game[j][2] > 0) {
            game[i][0]--;
            game[j][2]--;
            find(depth + 1);
            game[i][0]++;
            game[j][2]++;
        }
        
        if(game[i][1] > 0 && game[j][1] > 0) {
            game[i][1]--;
            game[j][1]--;
            find(depth + 1);
            game[i][1]++;
            game[j][1]++;
        }

        if(game[i][2] > 0 && game[j][0] > 0) {
            game[i][2]--;
            game[j][0]--;
            find(depth + 1);
            game[i][2]++;
            game[j][0]++;
        }
 
    }
}
