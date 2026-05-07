import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;
        
        ArrayList<String>[] list = new ArrayList[n];
        ArrayList<Integer[]> idxList = new ArrayList<>();
        
        //ArrayList 배열로 board 위치를 내가 다루기 쉽게 변경
        for(int i=0;i<n;i++){
            String[] arr = new String[m];
            for(int j=m-1;j>=0;j--){
                arr[m-1-j] = board[j].substring(i,i+1);
            }
            list[i] = new ArrayList<>(Arrays.asList(arr));
        }
        
        while(true){
            //4블록 위치 찾아서 idxList에 저장
            while(true){
                for(int i=0;i<n-1;i++){
                    for(int j=m-1;j>=1;j--){
                        String s = list[i].get(j);
                        if(!s.equals("0")&&s.equals(list[i].get(j-1))&&s.equals(list[i+1].get(j))&&s.equals(list[i+1].get(j-1))){
                            Integer[] a = {i,j};
                            idxList.add(a);
                        }
                    }
                }
                break;
            }
            //4블록이 없으면 종료
            if(idxList.size()==0)
                break;

            //4블록인 곳 0으로 치환 후 개수 세기
            for(int i=0;i<idxList.size();i++){
                if(!list[idxList.get(i)[0]].get(idxList.get(i)[1]).equals("0")){
                    answer += 1;
                    list[idxList.get(i)[0]].set(idxList.get(i)[1],"0");
                }
                if(!list[idxList.get(i)[0]+1].get(idxList.get(i)[1]).equals("0")){
                    answer += 1;
                    list[idxList.get(i)[0]+1].set(idxList.get(i)[1],"0");
                }
                if(!list[idxList.get(i)[0]].get(idxList.get(i)[1]-1).equals("0")){
                    answer += 1;
                    list[idxList.get(i)[0]].set(idxList.get(i)[1]-1,"0");
                }
                if(!list[idxList.get(i)[0]+1].get(idxList.get(i)[1]-1).equals("0")){
                    answer += 1;
                    list[idxList.get(i)[0]+1].set(idxList.get(i)[1]-1,"0");
                }
            }
            //0인 곳 제거 후 위치 재정렬
            for(int i=0;i<n;i++){
                list[i].removeIf(s->s.equals("0"));
                while(list[i].size()<m){
                    list[i].add("0");
                }
            }
            idxList.clear();
        }
        
        return answer;
    }
}
