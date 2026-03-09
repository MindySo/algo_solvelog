import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        Map<Integer, List<Job>> map = new HashMap();
        for(int i = 0; i <= 1000; i++){
            map.put(i, new ArrayList());
        }
        
        for(int i = 0; i < jobs.length; i++){
            map.get(jobs[i][0]).add(new Job(i, jobs[i][0], jobs[i][1]));
        }
        
        PriorityQueue<Job> pq = new PriorityQueue(new Comparator<Job>(){
            public int compare(Job o1, Job o2){
                if(o1.cost != o2.cost) return o1.cost - o2.cost;
                if(o1.reqTime != o2.reqTime) return o1.reqTime - o2.reqTime;
                return o1.no - o2.no;
            }
        });
        
        int prev = 0, curr = 0, cnt = 0, sum = 0;
        while(cnt < jobs.length){
            for(int time = prev; time <= curr; time++){
                if(time > 1000) break;
                for(Job job : map.get(time)) {
                  pq.add(job);
                  cnt++;
                }
            }
            
            prev = curr + 1;
            
            if(pq.isEmpty()){
                curr++;
            }else {
                Job job = pq.poll();
                curr += job.cost;
                sum += (curr - job.reqTime);
            }
        }
        
        while(!pq.isEmpty()){
            Job job = pq.poll();
            curr += job.cost;
            sum += (curr - job.reqTime);
        }

        return sum / jobs.length;
    }
    
    class Job{
        int no;
        int reqTime;
        int cost;
        public Job(int no, int reqTime, int cost){
            this.no = no; this.reqTime = reqTime; this.cost = cost; 
        }
    }
}