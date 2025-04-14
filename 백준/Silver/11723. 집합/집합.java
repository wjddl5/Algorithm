import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        int a = 1;
        int n = 0;
        for(int i=0; i<t; i++) {
            String[] line = br.readLine().split(" ");
            if(!line[0].equals("all") && !line[0].equals("empty")) n = Integer.parseInt(line[1]);
            switch (line[0]) {
                case "add":
                    a |= (1 << n);
                    break;
                case "remove":
                    a &= ~(1 << n);
                    break;
                case "check":
                    if((a & (1 << n)) == (1 << n)) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case "toggle":
                    a ^= 1 << n;
                    break;
                case "all":
                    a = 1;
                    for(int j=1; j<21; j++) {
                        a |= (1 << j);
                    }
                    break;
                case "empty":
                    a = 1;
                    break;
            }
        }
        System.out.println(sb);
    }
}