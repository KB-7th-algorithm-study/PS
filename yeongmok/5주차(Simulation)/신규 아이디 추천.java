class Solution {
    public String solution(String new_id) {
        //step1
        new_id = new_id.toLowerCase();
        //step2
        new_id = new_id.replaceAll("[^0-9a-z._-]","");
        //step3
        new_id = new_id.replaceAll("[.]+",".");
        //step4
        if(new_id.length()!=0 && new_id.charAt(0)=='.'){
            new_id = new_id.substring(1,new_id.length());
        }
        if(new_id.length()!=0 && new_id.charAt(new_id.length()-1)=='.'){
            new_id = new_id.substring(0,new_id.length()-1);
        }
        //step5
        if(new_id.equals(""))   new_id = "a";
        //step6
        if(new_id.length()>15){
            new_id = new_id.substring(0,15);
            if(new_id.charAt(new_id.length()-1)=='.'){
                new_id = new_id.substring(0,new_id.length()-1);
            }   
        }
        //step7
        while(new_id.length()<=2){
            new_id += new_id.charAt(new_id.length()-1);
        }

        return new_id;
    }
}