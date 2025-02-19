import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] NM = br.readLine().split(" ");
            int N = Integer.parseInt(NM[0]);
            int M = Integer.parseInt(NM[1]);

            String[][] castle = new String[N][M];

            for(int i=0; i<N; i++) { 
                String str = br.readLine();
                for(int j=0; j<M; j++) {
                    castle[i][j] = String.valueOf(str.charAt(j));
                }
            }

            int row = 0;   
            for(int i=0; i<N; i++){ 
                boolean chk = true;
                for(int nr=0; nr<M; nr++){
                    if(castle[i][nr].equals("X")) {
                        chk = false;
                        break;
                    }
                }
                if(chk) row++; 
            }
            
            int col = 0;
            for(int j=0; j<M; j++){ 
                boolean chk = true;
                for(int nl=0; nl<N; nl++){
                    if(castle[nl][j].equals("X")) {
                        chk = false;
                        break;
                    }                        
                }
                if(chk) col++; 
            }
            System.out.println(Math.max(row, col));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}