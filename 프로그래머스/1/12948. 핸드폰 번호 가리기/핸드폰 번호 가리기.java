class Solution {
    public String solution(String phone_number) {
        String answer = "";
        for(int i = 0; i < phone_number.length() - 4; i++)
            answer += "*";
        
        for(int i = 4; i > 0; i--)
            answer += phone_number.charAt(phone_number.length() - i);
        return answer;
    }
}