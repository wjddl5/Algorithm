import java.io.*;

public class Main {

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Parse parse = new Parse();
            Input input = getInput(br);
            System.out.println(parse.answer(input.word, input.type));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Input getInput(BufferedReader br) throws IOException {
        String word = br.readLine();
        String type = getType(word);
        return new Input(word, type);
    }

    private String getType(String word) {
        if(hasError(word)) return "error";
        else if(hasUnderBar(word) && !hasUpper(word)) return "C++";
        else return "Java";
    }

    private boolean hasUnderBar(String word) {
        if(word.contains("_")) return true;
        return false;
    }

    private boolean hasUpper(String word) {
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) < 91) return true;
        }
        return false;
    }

    private boolean hasError(String word) {
        if(word.charAt(0) < 97) return true;
        if(word.charAt(word.length()-1) == 95) return true;

        boolean upper=false; boolean underBar=false;
        for(int i=0; i<word.length(); i++) {
            if(word.charAt(i) == 95) {
                underBar = true;
                if(i<word.length()-2 && word.charAt(i+1) == 95) return true;
            }
            if(word.charAt(i) < 91) upper  = true;
        }

        if(upper && underBar) return true;
        return false;
    }


    private static class Input {
        final String word;
        final String type;

        Input(String word, String type) {
            this.word = word;
            this.type = type;
        }
    }
        
}

class Parse {

    private StringBuilder sb = new StringBuilder();

    public String answer(String word, String type) {
        switch (type) {
            case "Java":
                toCPP(word);
                break;
        
            case "C++":
                toJava(word);
                break;
            case "error":
                sb.append("Error!");
                break;
        }
        return sb.toString();
    }

    private void toCPP(String word) {
        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(c < 97) {
                char down = (char)(c + 32);
                sb.append("_").append(down);
            }
            else sb.append(c);
        }
    }

    private void toJava(String word) {
        String[] ar = word.split("_");
        sb.append(ar[0]);
        for(int i=1; i<ar.length; i++) {
            char c = (char) (ar[i].charAt(0) - 32);
            sb.append(c);
            if(ar[i].length()>1){
                String str = ar[i].substring(1);
                sb.append(str);
            }
        }
    }

    
}
    