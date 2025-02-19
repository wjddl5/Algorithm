import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("input.txt"));

        int cnt = 1;
        
        int N = Integer.parseInt(br.readLine());

        if(N < 10) N *= 10;
        
        int target = 0;
        int temp = 0;

        int n2 = N % 10;
        int n1 = (N - n2) / 10;

        while(target != N) {     
            
            temp = n1 + n2;

            target = (n2 * 10) + (temp % 10);

            if(target == N) break;

            cnt++;

            n2 = target % 10;
            n1 = (target - n2) / 10;
        }
        System.out.println(cnt);
    }
}