import java.util.*;

class Solution {
    public int[] solution(String[] operations) {       
        PriorityQueue<Integer> maxQ = new PriorityQueue<>((n1, n2) -> n2 - n1);
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        
        for(String str : operations) {
            switch(str) {
                case "D 1":
                    if(maxQ.isEmpty()) continue;
                    int max = maxQ.poll();
                    minQ.remove(max);
                    break;
                case "D -1":
                    if(minQ.isEmpty()) continue;
                    int min = minQ.poll();
                    maxQ.remove(min);
                    break;
                default:
                    int n = Integer.parseInt(str.substring(2));
                    maxQ.offer(n);
                    minQ.offer(n);
            }
        }
        
        if(minQ.size() > 0) return new int[] {maxQ.poll(), minQ.poll()};
        else return new int[] {0, 0};
    }
}