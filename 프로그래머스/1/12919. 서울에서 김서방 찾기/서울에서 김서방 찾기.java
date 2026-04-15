class Solution {
    public String solution(String[] seoul) {
        int answer = 0;
        while(!seoul[answer].equals("Kim")){
            answer++;
        }
        
        return "김서방은 " + answer + "에 있다";
    }
}