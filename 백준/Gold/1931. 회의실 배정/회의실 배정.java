import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static ArrayList<int[]> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());     
        int max = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            list.add(new int[] {from, to});
            max = Math.max(max, to);
        }
        list.sort(Comparator
            .comparingInt((int[] o) -> o[1]) // 끝나는 시간
            .thenComparingInt(o -> o[0]) // 시작하는 시간
        );
        int cnt = 0;
        int end = 0;
        for(int[] t : list) {
            if(end <= t[0]) {
                end = t[1];
                cnt++;
            }  
        }
        System.out.println(cnt);
    }

}