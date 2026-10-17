// https://school.programmers.co.kr/learn/courses/30/lessons/42888
// [프로그래머스] 오픈채팅방 : Java
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        // uid로 사용자 구분
        // 사용자별 최종 닉넥임을 트래킹할 수 있게 Map<uid, nickname>에 저장
        // 입출 기록들은 List<String[]{uid, action}> 형태로 저장
        
        Map<String, String> nicknames = new HashMap<>();
        List<String[]> parsedRecord = new ArrayList<>();
        
        for (int i = 0; i < record.length; i++) {
            String[] message = record[i].split(" ");
            String action = message[0];
            String uid = message[1];
            
            if (action.equals("Enter")) {
                nicknames.put(uid, message[2]);
                parsedRecord.add(new String[] {uid, action});
            }
            else if (action.equals("Leave")) {
                parsedRecord.add(new String[] {uid, action});
            }
            else if (action.equals("Change")) {
                nicknames.put(uid, message[2]);
            }
        }
        
        String[] result = new String[parsedRecord.size()];
        
        for (int i = 0; i < parsedRecord.size(); i++) {
            String[] message = parsedRecord.get(i);
            String uid = message[0];
            String action = message[1];
            
            if (action.equals("Enter")) {
                result[i] = nicknames.get(uid) + "님이 들어왔습니다.";
            }
            else if (action.equals("Leave")) {
                result[i] = nicknames.get(uid) + "님이 나갔습니다.";
            }
        }
        
        return result;
    }
}