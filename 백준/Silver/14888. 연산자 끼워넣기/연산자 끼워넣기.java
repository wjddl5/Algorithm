import java.io.*;
import java.util.*;

public class Main {

    static int N, min = 1_000_000_001, max = -1_000_000_001;
    static int[] numbers;
    static char[] operator, oper;
    static boolean[] visit;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operator = new char[N-1];
        oper = new char[N-1];
        visit = new boolean[N-1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Character> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            int j = Integer.parseInt(st.nextToken());
            switch (i) {
                case 0:
                    while(j-- > 0) list.add('+');
                    break;
                case 1:
                    while(j-- > 0) list.add('-');
                    break;
                case 2:
                    while(j-- > 0) list.add('*');
                    break;
                case 3:
                    while(j-- > 0) list.add('/');
                    break;
            }
        }

        for(int i=0; i<N-1; i++) {
            operator[i] = list.get(i);
        }
        
        perm(0);

        System.out.println(max);    
        System.out.println(min);
    }

    static void perm(int cnt) {
        if(cnt == N-1) {
            findMax();
            findMin();
        }
        for(int i=0; i<N-1; i++) {
            if(visit[i]) continue;
            oper[cnt] = operator[i];
            visit[i] = true;
            perm(cnt+1);
            visit[i] = false;
        }
    }

    static void findMax() {
        int res = numbers[0];
        for(int i=0; i<N-1; i++) {
            char c = oper[i];
            switch (c) {
                case '+':
                    res += numbers[i+1];
                    break;
                case '-':
                    res -= numbers[i+1];
                    break;
                case '*':
                    res *= numbers[i+1];
                    break;
                case '/':
                    res /= numbers[i+1];
                    break;
            }
        }
        max = Math.max(max, res);
    }

    static void findMin() {
        int res = numbers[0];
        for(int i=0; i<N-1; i++) {
            char c = oper[i];
            switch (c) {
                case '+':
                    res += numbers[i+1];
                    break;
                case '-':
                    res -= numbers[i+1];
                    break;
                case '*':
                    res *= numbers[i+1];
                    break;
                case '/':
                    res /= numbers[i+1];
                    break;
            }
        }
        min = Math.min(min, res);
    }

    
}