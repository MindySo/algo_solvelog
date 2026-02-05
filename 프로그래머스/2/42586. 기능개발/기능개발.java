import java.util.*;
class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> proQue = new ArrayDeque();
        Queue<Integer> speedQue = new ArrayDeque();
        List<Integer> answer = new ArrayList();
        
        for(int i = 0; i < progresses.length; i++){
            proQue.add(progresses[i]);
            speedQue.add(speeds[i]);
        }
        
        int progress, speed, size, done;
        boolean prevDone;
        while(!proQue.isEmpty()){
            size = proQue.size();
            done = 0;
            prevDone = true;
            for(int i = 0; i < size; i++){
                progress = proQue.poll();
                speed = speedQue.poll();
                progress += speed;
                if(prevDone && progress >= 100){
                    done++;
                }else{
                    proQue.add(progress);
                    speedQue.add(speed);
                    prevDone = false;
                }
            }
            if(done > 0) answer.add(done);
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}