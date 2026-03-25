import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        
        for(int i = 0; i < numbers.length; i++) {
            long num = numbers[i];
            String str = Long.toBinaryString(num);
            int powNum = getSize(str.length());
            int size = (int)Math.pow(2, powNum) - 1;
            StringBuilder sb = new StringBuilder();
            for(int t= 0; t < size - str.length(); t++) sb.append("0");
            sb.append(str);
            str = sb.toString();
            
            answer[i] = DFS(str, size / 2, powNum - 1) != 'X' ? 1 : 0;
        }
        
        return answer;
    }
    
    public int getSize(int length){
        int i = 1;
        while(length > Math.pow(2, i) - 1) i++;
        return i;
    }
    
    public char DFS(String str, int idx, int powNum){
        if(powNum == 0) return str.charAt(idx);
        
        powNum--;
        char child1 = DFS(str, idx - (int)Math.pow(2, powNum), powNum);
        if(child1 == 'X') return 'X';
        
        char child2 = DFS(str, idx + (int)Math.pow(2, powNum), powNum);
        if(child2 == 'X') return 'X';
        
        
        if((child1 == '1' || child2 == '1') && str.charAt(idx) == '0') {
            return 'X';
        }
        return str.charAt(idx);
    }

}

