import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        List<Integer> nums = new ArrayList();
        Set<Integer> removed = new HashSet();

        PriorityQueue<Integer> min = new PriorityQueue(new Comparator<Integer>(){
           public int compare(Integer o1, Integer o2){
               return nums.get(o1) - nums.get(o2);
           } 
        });

        PriorityQueue<Integer> max = new PriorityQueue(new Comparator<Integer>(){
           public int compare(Integer o1, Integer o2){
               return nums.get(o2) - nums.get(o1);
           } 
        });
        
        int idx = 0;
        
        for(String str : operations){
            String[] val = str.split(" ");
            int num = Integer.parseInt(val[1]);
            if(val[0].charAt(0) == 'I'){
                nums.add(num);
                min.add(idx);
                max.add(idx);
                idx++;
            }else if(num == 1){
                while(!max.isEmpty() && removed.contains(max.peek())) max.poll();
                if(!max.isEmpty()) removed.add(max.poll());
            }else{
                while(!min.isEmpty() && removed.contains(min.peek())) min.poll();
                if(!min.isEmpty()) removed.add(min.poll());
            }
        }
        
        while(!max.isEmpty() && removed.contains(max.peek())) max.poll();
        if(max.isEmpty()) return new int[] {0, 0};
        
        int[] answer = new int[2];
        answer[0] = nums.get(max.poll());
        
        while(!min.isEmpty() && removed.contains(min.peek())) min.poll();
        answer[1] = nums.get(min.poll());
        
        return answer;
    }
}