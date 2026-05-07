package PS.sanghak.week5;

public class 신규아이디추천 {

    public String solution(String new_id) {
        String str = new_id;
        //1단계 : 대문자 'B'와 'T'가 소문자 'b'와 't'로 바뀌었습니다.
        str = str.toLowerCase();


        //2단계 : '!', '@', '#', '*' 문자가 제거되었습니다.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((c >= 'a' && c <= 'z') ||
                    (c >= '0' && c <= '9') ||
                    c == '-' || c == '_' || c == '.') {
                sb.append(c);
            }
        }
        str = sb.toString();


        //3단계 :  '...'와 '..' 가 '.'로 바뀌었습니다.
        str = str.replaceAll("\\.{2,}", ".");

        //4단계 : 아이디의 처음에 위치한 '.'가 제거되었습니다.
        if (str.startsWith(".")) str = str.substring(1);
        if (str.endsWith(".")) str = str.substring(0, str.length() - 1);

        //5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
        if (str.isEmpty()) {
            str = "a";
        }

        //6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
        //만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
        if (str.length() >= 16) {
            str = str.substring(0, 15).replaceAll("\\.$", "");
        }

        //7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
        while (str.length() < 3) {
            str += str.charAt(str.length() - 1);
        }
        String answer = str;
        return answer;
    }
}
