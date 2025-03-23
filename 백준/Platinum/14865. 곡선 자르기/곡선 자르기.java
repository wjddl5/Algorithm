import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Point> list = new ArrayList<>();
    static int[][] ar;
    static int n, res1, res2, minIndex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        n = Integer.parseInt(br.readLine());
        ar = new int[n][2];     
        int min = Integer.MAX_VALUE;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ar[i][0] = a; 
            ar[i][1] = b;
            int tmpMin = a + b;
            if(min > tmpMin) {
                min = tmpMin;
                minIndex = i;
            }
        }
        find();
        check();
        System.out.println(res1+" "+res2);
    }

    public static void find() {
        ArrayList<Integer> tempList = new ArrayList<>();
        for(int k=minIndex; k<minIndex+n-1; k++) {
            int i = k;
            int ii = k + 1;
            if(i>=n) i -= n;
            if(ii>=n) ii -= n;

            if(ar[i][0] == ar[ii][0]) {
                if(ar[i][1] < 0 && ar[ii][1] > 0 || ar[i][1] > 0 && ar[ii][1] < 0) {
                    tempList.add(ar[i][0]);
                }
            }
            if(tempList.size() == 2) {
                int x1 = tempList.get(0);
                int x2 = tempList.get(1);
                if(x1 < x2) {
                    Point p1 = new Point(x1, true);
                    Point p2 = new Point(x2, false);
                    list.add(p1);
                    list.add(p2);
                }else {
                    Point p1 = new Point(x1, false);
                    Point p2 = new Point(x2, true);
                    list.add(p1);
                    list.add(p2);
                }
                tempList.clear();
            }
            
        }
        list.sort((o1, o2) -> o1.x - o2.x);
    }

    public static void check() {
        Stack<Integer> stack = new Stack<>();
        int pointNum = 0;
        for(Point p : list) {
            if(p.isStart) stack.add(pointNum);
            else {
                int num = stack.pop();
                if(stack.isEmpty()) res1 ++;
                if(num == pointNum) res2 ++;

                pointNum ++;
            }
        }
    }
}

class Point  {
    public int x;
    public boolean isStart;

    public Point(int x, boolean isStart)  {
        this.x = x;
        this.isStart = isStart;
    }
}