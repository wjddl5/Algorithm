import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            TreeMap<Integer, Integer> map = new TreeMap<>();
            int K = Integer.parseInt(br.readLine());
            for(int i=0; i<K; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();
                int n = Integer.parseInt(st.nextToken());
                if(s.equals("I")) {
                    map.put(n, map.getOrDefault(n, 0) + 1);   
                } else {
                    if(map.size() < 1) continue;
                    if(n == 1) {
                        int key = map.lastKey();
                        if(map.get(key) == 1) map.remove(key);
                        else map.put(key, map.get(key) -1);
                    }
                    else {
                        int key = map.firstKey();
                        if(map.get(key) == 1) map.remove(key);
                        else map.put(key, map.get(key) -1);
                    }
                }
            }
            sb.append(map.isEmpty() ? "EMPTY" : map.lastKey()+" "+map.firstKey()).append("\n");
        }
        System.out.println(sb);
    }
}

