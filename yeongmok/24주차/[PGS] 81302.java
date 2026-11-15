class Solution {
    int[] answer = new int[5];
    int[] dx = {-1, 0, 0, 1};
    int[] dy = {0, -1, 1, 0};

    char[][][] array = new char[5][9][9];

    public boolean check_inner(int i, int y, int x, int originX, int originY){
        for(int l = 0; l < 4; l++){
            int px = x + dx[l];
            int py = y + dy[l];
            if(px == originX && py == originY){
                continue;
            }
            if(array[i][py][px] == 'P'){
                answer[i] = 0;
                return false;
            }
        }
        return true;
    }

    public boolean check(int i, int y, int x){
        for(int l = 0; l < 4; l++){
            int px = x + dx[l];
            int py = y + dy[l];
            if(array[i][py][px] == 'P'){
                return false;
            }
            else if(array[i][py][px] == 'O')
            {
                if(check_inner(i, py, px, x, y) == false){
                    return false;
                }
            }
        }
        return true;
    }
    public int[] solution(String[][] places) {

        for(int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                array[i][j] = new char[]{'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X', 'X'};
                if (!(j < 2 || j > 6)) {
                    char[] line = places[i][j - 2].toCharArray();
                    System.arraycopy(line, 0, array[i][j], 2, 5);
                }

            }
        }
        for(int i = 0; i < 5; i++){

            roomLoop : for(int j = 2; j < 7; j++){
                for(int k = 2; k < 7; k++){
                    if(array[i][j][k] == 'P'){
                        if(check(i, j, k) == false){
                            answer[i] = 0;
                            break roomLoop;
                        }
                    }
                }
                answer[i] = 1;
            }

        }
        return answer;
    }
}