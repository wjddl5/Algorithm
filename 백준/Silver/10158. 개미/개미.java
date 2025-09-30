import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int time = Integer.parseInt(br.readLine());

        //input

        int nx = (x + time) % (N * 2);
        int ny = (y + time) % (M * 2);

        if(nx > N) nx = 2 * N - nx;
        if(ny > M) ny = 2 * M - ny;

        System.out.println(nx + " " + ny);
    }
}