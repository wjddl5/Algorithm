import java.io.*;
import java.util.*;

public class Main {

   static int[][] point;
   static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            point = new int[4][2];
            for(int i=0; i<4; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                point[i][0] = Integer.parseInt(st.nextToken());
                point[i][1] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(point, (o1, o2) -> {
                return o1[0]==o2[0] ? o1[1]-o2[1] : o1[0]-o2[0];
        });
            check();
        }
        System.out.println(sb);
    }

    public static void check() {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        set1.add(Math.abs(point[0][0] - point[1][0]) + Math.abs(point[0][1] - point[1][1]));
        set1.add(Math.abs(point[0][0] - point[2][0]) + Math.abs(point[0][1] - point[2][1]));
        set1.add(Math.abs(point[3][0] - point[1][0]) + Math.abs(point[3][1] - point[1][1]));
        set1.add(Math.abs(point[3][0] - point[2][0]) + Math.abs(point[3][1] - point[2][1]));

        set2.add(Math.abs(point[0][0] - point[3][0]) + Math.abs(point[0][1] - point[3][1]));
        set2.add(Math.abs(point[1][0] - point[2][0]) + Math.abs(point[1][1] - point[2][1]));

        if(set1.size()==1 && set2.size()==1) sb.append(1).append("\n");
        else sb.append(0).append("\n");
    }
}