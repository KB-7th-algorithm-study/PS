import java.util.*;

// [PGS] 42888 오픈채팅방
class Solution {
    public String[] solution(String[] record) {
        // 1. uid별 최종 닉네임 정리 (Enter / Change 둘 다 닉네임 갱신)
        Map<String, String> nick = new HashMap<>();
        for (String r : record) {
            String[] p = r.split(" ");
            if (p[0].equals("Enter") || p[0].equals("Change")) {
                nick.put(p[1], p[2]);
            }
        }

        // 2. Enter / Leave 만 메시지로 변환
        List<String> answer = new ArrayList<>();
        for (String r : record) {
            String[] p = r.split(" ");
            if (p[0].equals("Enter")) {
                answer.add(nick.get(p[1]) + "님이 들어왔습니다.");
            } else if (p[0].equals("Leave")) {
                answer.add(nick.get(p[1]) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}
