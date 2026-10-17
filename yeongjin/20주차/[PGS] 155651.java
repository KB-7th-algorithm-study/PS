import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        List<List<int[]>> room = new ArrayList<>();
        Arrays.sort(book_time,(o1,o2)->o1[0].compareTo(o2[0]));
        
        for(String[] time : book_time ){
            String[] s = time[0].split(":");
            String[] e = time[1].split(":");
            int start = Integer.parseInt(s[0])*60 + Integer.parseInt(s[1]);
            int end = Integer.parseInt(e[0])*60 + Integer.parseInt(e[1]);
            if(room.isEmpty()){
                room.add(new ArrayList<>(Arrays.asList(new int[] {start,end})));
            }else{
                boolean b = false;
                for(List<int[]> list  : room){
                    if(list.get(list.size()-1)[1] +10 <= start){
                        list.add(new int[] {start,end});
                        b = true;
                        break;
                    }
                }
                if(b == false) {
                    room.add(new ArrayList<>(Arrays.asList(new int[] {start,end})));
                }
            }
        }
        return room.size() ;
    }
}