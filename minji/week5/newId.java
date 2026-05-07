package PS.minji.week5;

public class newId {
    public String solution(String new_id) {
        String answer = "";

        answer = step1(new_id);
        answer = step2(answer);
        answer = step3(answer);
        answer = step4(answer);
        answer = step5(answer);
        answer = step6(answer);
        answer = step7(answer);

        return answer;
    }

    public String step1(String s) {
        return s.toLowerCase();
    }

    public String step2(String s) {
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if((c >= 'a' && c <= 'z') ||
                    (c >= '0' && c <= '9') ||
                    c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }

        return sb.toString();
    }

    public String step3(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);

            if(c == '.' && sb.length() > 0 &&
                    sb.charAt(sb.length() - 1) == '.') {
                continue;
            }

            sb.append(c);
        }

        return sb.toString();
    }


    public String step4(String s) {
        if( s.length() > 0 && s.charAt(0) == '.') {
            s = s.substring(1);
        }

        if(s.length()>0 && s.charAt(s.length()-1) == '.') {
            s = s.substring(0, s.length()-1);
        }

        return s;

    }

    public String step5(String s) {
        StringBuilder sb = new StringBuilder(s);
        if(sb.length() == 0) {
            sb.append('a');
        }

        return sb.toString();
    }

    public String step6(String s) {

        if(s.length() >= 16) {
            s = s.substring(0, 15);
        }

        if(s.length() > 0 && s.charAt(s.length()-1) == '.') {
            s = s.substring(0, s.length()-1);
        }

        return s;
    }

    public String step7(String s) {
        StringBuilder sb = new StringBuilder(s);


        while(sb.length() < 3) {
            sb.append(sb.charAt(sb.length()-1));
        }

        return sb.toString();
    }
}
