class Solution {
    
    public int solution(String[] board) {
        int answer = -1;
        
        int[] cnt = {0,0};
        boolean[] win = {false,false};
        
        for(int i =0; i<3; i++){
            int countO = 0;
            int countX = 0;
            for(int j =0; j<3; j++){
                if(board[i].charAt(j)=='O'){
                    cnt[0]++;
                    countO++;
                }else if (board[i].charAt(j)=='X'){
                    cnt[1]++;
                    countX++;
                }
                if(countO==3) win[0] = true;
                if(countX==3) win[1] = true;
            }    
        }
        
        for(int i=0; i<3; i++){
            int countO = 0;
            int countX = 0;
            for(int j =0; j<3; j++){
                if(board[j].charAt(i) == 'O'){
                    countO++;
                }else if(board[j].charAt(i) == 'X'){
                    countX++;
                }
                if(countO==3) win[0] = true;
                if(countX==3) win[1] = true;
            }
        }
        
        int countO =0;
        int countX =0;
        for(int i =0; i<3; i++){
            if(board[i].charAt(i) == 'O'){
                countO++;
            }else if(board[i].charAt(i) == 'X'){
                countX++;
            }
            if(countO==3) win[0] = true;
            if(countX==3) win[1] = true;
        }
        
        countO = 0;
        countX = 0;
        for(int i =0; i<3; i++){
            if(board[i].charAt(2-i) == 'O'){
                countO++;
            }else if(board[i].charAt(2-i) == 'X'){
                countX++;
            }
            if(countO==3) win[0] = true;
            if(countX==3) win[1] = true;
        }
        
        if(cnt[0] < cnt[1]) return 0;
        if(cnt[0] > cnt[1]+1) return 0;
        if(cnt[0] == cnt[1]){
            if(win[0]) return 0;
        }else{
            if(win[1]) return 0;
        }
        if(win[0] == win[1] && win[0] == true ) return 0;
      
        return 1;
    }
}