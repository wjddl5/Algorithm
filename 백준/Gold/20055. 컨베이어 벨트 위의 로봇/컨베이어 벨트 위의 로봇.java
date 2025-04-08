import java.io.*;
import java.util.*;

public class Main {

    static int N, K, count=0, time=0;
    static int[] ar;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()) * 2 -1;
        K = Integer.parseInt(st.nextToken());
        
        ar = new int[N+1];
        robot = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N+1; i++){
            ar[i] = Integer.parseInt(st.nextToken());
        }

        while(count < K) {
            moveBelt();
            moveRobot();
            setRobot();
            time++;
        }
        System.out.println(time);
    }

    public static void setRobot() {
        if(ar[0] > 0) {
            robot[0] = true;
            ar[0]--;
            if(ar[0]<1) count++;
        }
    }

    public static void moveBelt() {
        int tmp = ar[N];
        boolean tmpRobot = robot[N];
        for(int i=N-1; i>=0; i--) {
            ar[i+1] = ar[i];
            robot[i+1] = robot[i];
        }
        ar[0] = tmp;
        robot[0] = tmpRobot;
        offRobot();
    }

    public static void moveRobot() {
        for(int i=N/2 -1; i>=0; i--) {
            if(!robot[i] || robot[i+1] || ar[i+1]<1) continue;
            robot[i+1] = true;
            robot[i] = false;
            ar[i+1]--;
            if(ar[i+1]<1) count++;
        }
        offRobot();
    }

    public static void offRobot() {
        if(robot[N/2]) robot[N/2] = false;
    }

}