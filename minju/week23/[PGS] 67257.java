import java.util.*;

class Solution {
  public long solution(String expression) {
    List<Long> operands = new ArrayList<>();
    List<Character> operators = new ArrayList<>();

    char[][] orders = {
        {'+', '-', '*'},
        {'+', '*', '-'},
        {'*', '-', '+'},
        {'*', '+', '-'},
        {'-', '+', '*'},
        {'-', '*', '+'}
    };

    //연산자, 숫자 분리
    String num = "";
    for (int i = 0; i < expression.length(); i++){

      char c = expression.charAt(i);

      if (c == '+' || c == '-' || c == '*'){
        operators.add(c);
        operands.add(Long.parseLong(num));
        num = "";
      }
      else{
        num += c;
      }
    }
    operands.add(Long.parseLong(num));

    //계산
    long answer = 0;

    for (char[] order : orders){

      List<Long> opds = new ArrayList<>(operands);
      List<Character> opts = new ArrayList<>(operators);

      for (char op : order){

        int idx = 0;
        //우선순위에 해당하는 연산자 발견
        while(idx < opts.size()){

          char o = opts.get(idx);
          if (o == op) {

            long result = 0;

            if (op == '*'){
              result =
                  opds.get(idx) * opds.get(idx + 1);
            }
            else if (op == '+'){
              result =
                  opds.get(idx) + opds.get(idx + 1);
            }
            else { //-
              result =
                  opds.get(idx) - opds.get(idx + 1);
            }

            opds.set(idx, result);
            opds.remove(idx + 1);
            opts.remove(idx);
          }
          else idx++;
        }
      }

      answer = Math.max(Math.abs(opds.get(0)), answer);
    }

    return answer;
  }
}
