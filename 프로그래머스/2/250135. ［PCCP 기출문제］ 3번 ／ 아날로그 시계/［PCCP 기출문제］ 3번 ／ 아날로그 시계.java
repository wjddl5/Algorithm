class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        double ss = 6 * s1;
        double sm = 6 * m1 + 0.1 * s1;
        double sh = 30 * (h1%12) + 0.5 * m1 + 0.008 * s1;      
        if(ss == sm || ss == sh) answer++;
        
        while(true) {
            if(h1==h2 && m1==m2 && s1==s2) break;
            
            double s = 6 * s1;
            double m = 6 * m1 + 0.1 * s1;
            double h = 30 * (h1%12) + 0.5 * m1 + 0.008 * s1;
                 
            s1++;
            if(s1 == 60) {
                s1 = 0;
                m1++;
                if(m1 == 60) {
                    m1 = 0;
                    h1++;
                }
            }
            
            double ns = 6 * s1;
            double nm = 6 * m1 + 0.1 * s1;
            double nh = 30 * (h1%12) + 0.5 * m1 + 0.008 * s1;
            
            if(ns == 0.0) ns = 360.0;
            if(nm == 0.0) nm = 360.0;
            if(nh == 0.0) nh = 360.0;
            
            
            if(nh==360 && nm==360 && ns==360) answer++;
            else {
                if(s < m && ns >= nm) answer++;
                if(s < h && ns >= nh) answer++;
            }

        }
        return answer;
    }
}