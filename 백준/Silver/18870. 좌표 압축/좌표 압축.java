import java.io.*;
import java.util.*;

public class Main { 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[] num = new int[N];
        Set<Integer> set = new HashSet<>();   
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int n = Integer.parseInt(st.nextToken());
            set.add(n);
            num[i] = n;
        }
        ArrayList<Integer> list = new ArrayList<>(set);
        list.sort(null);

        Map<Integer, Integer>  map = new HashMap<>();
        for(int i=0; i<list.size(); i++) {
            map.put(list.get(i), i);
        }
        for(int n : num) {      
            sb.append(map.get(n)+" ");
        }
        System.out.println(sb);
    }

}