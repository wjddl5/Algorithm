import java.io.*;
import java.util.*;
 
public class Solution {
 
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Person> Personlist, listA, listB;
    static int[][] sta; //{x1, y1, time1, x2, y2, time2}
    static int answer;
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());
        for(int tc=1; tc<=T; tc++) {
 
            Personlist = new ArrayList<>();
            sta = new int[2][3];
            answer = Integer.MAX_VALUE;
 
            int N = Integer.parseInt(br.readLine());
            int s = 0;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N;j++) {
                    int n = Integer.parseInt(st.nextToken());
                    if(n==1) {
                        Personlist.add(new Person(i, j, 0, 0));
                        }
                    if(n>1) {
                        sta[s][0] = i;
                        sta[s][1]= j;
                        sta[s][2] = n;
                        s++;
                    }
                }
            }
            subs(0);
 
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.print(sb);
    }
 
    public static void subs(int cnt) {
        if(cnt == Personlist.size()) {
            listA = new ArrayList<>();
            listB = new ArrayList<>();
            for(Person p : Personlist) {
                if(p.group == 0) listA.add(p);
                else listB.add(p); 
            }
            setDisToSta();
            return;
        }
        Person p = Personlist.get(cnt);
        p.group = 1;
        subs(cnt+1);
 
        p.group = 0;
        subs(cnt+1);
 
    }
 
    public static void setDisToSta() {
        for(Person p : listA) {
            p.dis = Math.abs(p.x - sta[0][0]) + Math.abs(p.y - sta[0][1]);
        }
        for(Person p : listB) {
            p.dis = Math.abs(p.x - sta[1][0]) + Math.abs(p.y - sta[1][1]);
        }
        find();
         
    }
 
    public static void find() {
        int time = 1;
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
 
        while(true) {
            // 계단을 향해 한 칸 걸어감
            for(int i=0; i<listA.size(); i++) {
                Person p = listA.get(i);
                Person p2 = new Person(p.x, p.y, listA.get(i).dis-1, p.group);
                listA.set(i, p2);
            }
            for(int i=0; i<listB.size(); i++) {
                Person p = listB.get(i);
                Person p2 = new Person(p.x, p.y, listB.get(i).dis-1, p.group);
                listB.set(i, p2);
            }
 
            // 계단에 도착
            for(int i=listA.size()-1; i>=0; i--) {
                if(listA.get(i).dis == 0) {
                    list.add(sta[0][2]);  
                    listA.remove(i);
                } 
            }
            for(int i=listB.size()-1; i>=0; i--) {
                if(listB.get(i).dis == 0) {
                    list2.add(sta[1][2]);  
                    listB.remove(i);
                }
            }
 
            // 다 내려간 사람 삭제
            ArrayList<Integer> l = new ArrayList<>();
            for(int i=0; i<list.size(); i++) {
                if(list.get(i) == 0) l.add(i);
            }
            for(int i=l.size()-1; i>=0; i--) {
                int n = l.get(i);
                list.remove(n);
            }
            ArrayList<Integer> l2 = new ArrayList<>();
            for(int i=0; i<list2.size(); i++) {
                if(list2.get(i) == 0) l2.add(i);
            }
            for(int i=l2.size()-1; i>=0; i--) {
                int n = l2.get(i);
                list2.remove(n);
            }
             
            // 3명 계단 내려가는 중
            for(int i=0; i<list.size() && i<3; i++) {
                list.set(i, list.get(i)-1);
            }       
            for(int i=0; i<list2.size() && i<3; i++) {
                list2.set(i, list2.get(i)-1);
            }       
 
            time++;
            if(time > answer) return;
            if(listA.isEmpty() && listB.isEmpty() && list.isEmpty() && list2.isEmpty()) break;
        }
        answer = Math.min(answer, time);
    }
 
}
 
class Person {
    public int x;
    public int y;
    public int dis;
    public int group;
 
    public Person(int x, int y, int dis, int group) {
        this.x = x;
        this.y = y;
        this.dis = dis;
        this.group = group;
    }
}