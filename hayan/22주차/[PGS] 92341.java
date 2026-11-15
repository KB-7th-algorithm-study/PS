import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        HashMap<String, Integer> record = new HashMap<>();
        TreeMap<String, Integer> timeSum = new TreeMap<>();
        
        for(int i=0; i<records.length; i++){
            String[] rec = records[i].split(" ");
            String[] t = rec[0].split(":");
            int time = Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]);
            if(rec[2].equals("IN")){
                record.put(rec[1], time);
            }else{ //출차
                int inTime = record.get(rec[1]);
                record.remove(rec[1]);
                timeSum.put(rec[1], timeSum.getOrDefault(rec[1], 0) + (time-inTime));
            }
        }
        
        for(String key : record.keySet()){ //11:59 출차 처리
            int inTime = record.get(key);
            int t = 23*60 + 59;
            timeSum.put(key, timeSum.getOrDefault(key, 0)+(t-inTime));
        }
        
        for(Integer value : timeSum.values()){
            int res=0;
            if(value <= fees[0]){ //기본 시간 이하
                res = fees[1];
            }else{ //기본 시간 초과
                res = fees[1];
                int a = value - fees[0];
                int b = fees[2];
                res += ((a+b-1)/b)*fees[3];
            }
            answer.add(res);
        }
        
        return answer.stream().mapToInt(i->i).toArray();
    }
}