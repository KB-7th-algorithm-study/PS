import java.util.*;

class Solution {
  public String[] solution(String[] record) {

    Map<String, String> userMap = new HashMap<>();
    List<String[]> logs = new ArrayList<>();

    for (String rec : record) {
      String[] info = rec.split(" ");
      String command = info[0];
      String uid = info[1];

      if (command.equals("Enter")) {
        userMap.put(uid, info[2]);
        logs.add(new String[]{uid, "님이 들어왔습니다."});
      }
      else if (command.equals("Leave")) {
        logs.add(new String[]{uid, "님이 나갔습니다."});
      }
      else { // Change
        userMap.put(uid, info[2]);
      }
    }

    String[] answer = new String[logs.size()];

    for (int i = 0; i < logs.size(); i++) {
      String uid = logs.get(i)[0];
      String message = logs.get(i)[1];

      answer[i] = userMap.get(uid) + message;
    }

    return answer;
  }
}

//첫 시도 (맞음)
//import java.util.*;
//
//class Pair {
//  String sentence;
//  String uid;
//
//  public Pair(String sentence, String uid){
//    if (sentence.equals("Enter")){
//      this.sentence = "님이 들어왔습니다.";
//    }
//    else if (sentence.equals("Leave")){
//      this.sentence = "님이 나갔습니다.";
//    }
//
//    this.uid = uid;
//  }
//}
//
//class Solution {
//  public String[] solution(String[] record) {
//
//    Map<String, String> userMap = new HashMap<>();
//    Deque<Pair> roomQueue = new ArrayDeque<>();
//
//    for (String rec : record){
//      String[] inform = rec.split(" ");
//
//      String uid = inform[1];
//
//      //change
//      if (inform[0].equals("Change")){
//        userMap.put(uid, inform[2]);
//      }
//      //enter, leave
//      else {
//        if (inform[0].equals("Enter")){
//          userMap.put(uid, inform[2]);
//        }
//        roomQueue.add(new Pair(inform[0], uid));
//      }
//    }
//
//    String[] result = new String[roomQueue.size()];
//    int idx = 0;
//    while(!roomQueue.isEmpty()){
//      Pair pair = roomQueue.poll();
//      String resultStr = userMap.get(pair.uid) + pair.sentence;
//      result[idx++] = resultStr;
//    }
//
//    return result;
//  }
//}
