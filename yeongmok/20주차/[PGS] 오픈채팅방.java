import java.util.*;

class Solution {
    public String[] solution(String[] records) {
        Map<String, String> scores = new HashMap<>();
        List<String> answer = new ArrayList<>();

        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");

            if (record[0].equals("Enter")
                    || record[0].equals("Change")) {
                scores.put(record[1], record[2]);
            }
        }

        // 최종 닉네임으로 입장·퇴장 메시지 생성
        for (int i = 0; i < records.length; i++) {
            String[] record = records[i].split(" ");

            if (record[0].equals("Enter")) {
                answer.add(scores.get(record[1]) + "님이 들어왔습니다.");
            } else if (record[0].equals("Leave")) {
                answer.add(scores.get(record[1]) + "님이 나갔습니다.");
            }
        }

        return answer.toArray(new String[0]);
    }
}