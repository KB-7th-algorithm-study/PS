package PS.sanghak.week4;


class 조이스틱 {
    public int solution(String name) {
        int answer = 0;
        for(int i=0; i<name.length(); i++){
            char ctr = name.charAt(i);
            answer += Math.min(ctr-'A','Z'-ctr+1);
        }

        //쭉 이동했을때의 값
        int nameSize = name.length();
        int move = nameSize-1;
        for(int i=0; i<nameSize; i++){
            int next = i + 1;

            //현재 인덱스의 다음 문자가 A이면
            while(next<nameSize && name.charAt(next)=='A'){
                next++;
            }

            int right = i * 2 + (nameSize-next);
            int left = (nameSize-next) * 2 + i;
            move = Math.min(move,Math.min(right,left));


        }

        return move+answer;
    }
}
