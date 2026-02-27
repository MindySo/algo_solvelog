import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        Integer num;
        int max = 0;
        for(int i = 0; i < numbers.length; i++){
            num = numbers[i];
            max = Math.max(max, num);
            nums[i] = num.toString();
        }
        
        if(max == 0) return "0";
        
        Arrays.sort(nums, new Comparator<String>(){
           public int compare(String o1, String o2){
               return Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2);
           } 
        });
        
        StringBuffer sb = new StringBuffer();
        for(String n : nums){
            sb.append(n);
        }
        
        return sb.toString();
    }
}