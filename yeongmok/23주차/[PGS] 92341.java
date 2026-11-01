import java.util.*;

class Solution {
    private int DEFAULT_TIME, DEFAULT_FEE, UNIT_TIME, UNIT_FEE;
    private Map<String, String> map = new HashMap<>(); // 차량번호 to 입차시각
    private Map<String, Integer> parkingTimeMap = new HashMap<>();
    private Map<String, Integer> feeMap = new HashMap<>();
    
    public int[] solution(int[] fees, String[] records) {
        init(fees);
        
        // set 차량별 주차시간 총합
        StringTokenizer st;
        for(String str : records){
            st = new StringTokenizer(str);
            String time = st.nextToken();
            String carNo = st.nextToken();
            String type = st.nextToken();
            
            if(type.equals("IN")){
                map.put(carNo, time);
            }else if(type.equals("OUT")){
                String inTime = map.get(carNo);
                int sumTime = getSumMinute(inTime, time);
                parkingTimeMap.put(carNo, parkingTimeMap.getOrDefault(carNo, 0) + sumTime);
                map.remove(carNo);
            }
        }
        
        // 출차 안된 차량 set 주차시간 총합
        for(String key : map.keySet()){
            String inTime = map.get(key);
            int sumTime = getSumMinute(inTime, "23:59");
            parkingTimeMap.put(key, parkingTimeMap.getOrDefault(key, 0) + sumTime);
        }
        
        // 요금 계산 후 feeMap 삽입
        for(String carNo : parkingTimeMap.keySet()){
            int parkingTime = parkingTimeMap.get(carNo);
            int fee = getFee(parkingTime);
            
            feeMap.put(carNo, fee);
        }
            
        // 주차 번호 별 정렬
        List<Map.Entry<String, Integer>> list = new ArrayList<>(feeMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>(){
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2){
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        
        // answer
        return getAnswer(list);
    }
    
    private int getFee(int parkingTime){
        if(parkingTime <= DEFAULT_TIME)
            return DEFAULT_FEE;
        
        // 기본요금에 대한 기본시간 차감
        int fee = DEFAULT_FEE;
        parkingTime -= DEFAULT_TIME;
        
        // 단위시간 별 요금 계산
        int multiple = parkingTime / UNIT_TIME;
        if(parkingTime % UNIT_TIME != 0)
            multiple++;
        fee += (multiple * UNIT_FEE);
        
        return fee;
    }
    
    private int getSumMinute(String inTime, String outTime){
        String[] inTimeToken = inTime.split(":");
        String[] outTimeToken = outTime.split(":");
        int differHour = Integer.parseInt(outTimeToken[0]) - Integer.parseInt(inTimeToken[0]);
        int differMinute = Integer.parseInt(outTimeToken[1]) - Integer.parseInt(inTimeToken[1]);
        
        if(differMinute < 0){
            differHour--;
            differMinute += 60;
        }
        
        return (differHour * 60) + differMinute;
    }
    
    private void init(int[] fees){
        DEFAULT_TIME = fees[0];
        DEFAULT_FEE = fees[1];
        UNIT_TIME = fees[2];
        UNIT_FEE = fees[3];
    }
    
    private void debugPrint(){
        System.out.println("map : " + map);
        System.out.println("parkingTimeMap : " + parkingTimeMap);
        System.out.println("feeMap : " + feeMap);
    }
    
    private int[] getAnswer(List<Map.Entry<String, Integer>> list){
        int[] answer = new int[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            answer[i] = list.get(i).getValue();
        }
        
        return answer;
    }
}