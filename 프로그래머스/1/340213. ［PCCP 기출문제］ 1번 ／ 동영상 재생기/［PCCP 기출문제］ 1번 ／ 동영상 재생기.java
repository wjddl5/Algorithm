class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String[] vl = video_len.split(":");
        String[] po = pos.split(":");
        String[] os = op_start.split(":");
        String[] oe = op_end.split(":");
        
        int video_time = Integer.parseInt(vl[0])*60 + Integer.parseInt(vl[1]);
        int pos_time = Integer.parseInt(po[0])*60 + Integer.parseInt(po[1]);
        int ops_time = Integer.parseInt(os[0])*60 + Integer.parseInt(os[1]);
        int ope_time = Integer.parseInt(oe[0])*60 + Integer.parseInt(oe[1]);
        
        for(String command : commands){
            if(pos_time >= ops_time && pos_time <= ope_time) {
                pos_time = ope_time;
            }
            switch(command) {
                case "prev":
                    if(pos_time > 9) {
                        pos_time -= 10;   
                    }else{
                        pos_time = 0;
                    }
                    break;
                case "next":
                    if(pos_time < video_time - 9) {
                        pos_time += 10; 
                    }else{
                        pos_time = video_time;
                    }                 
                    break;
            }
            if(pos_time >= ops_time && pos_time <= ope_time) {
                pos_time = ope_time;
            }
        }
        
        int m = pos_time / 60;
        int s = pos_time % 60;
        
        StringBuilder sb = new StringBuilder();
        sb.append(m/10).append(m%10).append(":").append(s/10).append(s%10);

        return sb.toString();
    }
}