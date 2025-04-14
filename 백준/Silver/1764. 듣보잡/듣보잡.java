import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;
        ArrayList<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            String str = br.readLine();
            map.put(str, 1);
        }
        for(int i=0; i<M; i++) {
            String str = br.readLine();
            if(map.get(str) != null) {
                cnt++;
                list.add(str);
            }
        }
        list.sort(Comparator.naturalOrder());
        for(String str : list) {
            sb.append(str).append("\n");
        }
        System.out.println(cnt);      
        System.out.println(sb);
    }
}