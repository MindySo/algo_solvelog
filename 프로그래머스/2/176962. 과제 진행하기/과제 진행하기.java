import java.util.*;

class Solution {
    static class Plan{
        String name;
        int start;
        int playtime;
        Plan(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    
    static PriorityQueue<Plan> timetable;
    static String[] answer;
    public String[] solution(String[][] plans) {
        init(plans);
        study();
        
        return answer;
    }
    
    static void init(String[][] plans){
        answer = new String[plans.length];
        timetable = new PriorityQueue<Plan>(new Comparator<Plan>(){
            @Override
            public int compare(Plan o1, Plan o2){
                return o1.start - o2.start;
            }
        });
        
        for(String[] p : plans){
            int h = (p[1].charAt(0) - '0') * 10 + (p[1].charAt(1) - '0');
            int m = (p[1].charAt(3) - '0') * 10 + (p[1].charAt(4) - '0');
            timetable.add(new Plan(p[0], (h * 60 + m), Integer.parseInt(p[2])));
        }
    }
    
    static void study(){
        Stack<Plan> stk = new Stack();
        int idx = 0;
        
        Plan curr, next = null, undone;
        int now = 0;
        curr = timetable.poll();
        while(!timetable.isEmpty()){
            next = timetable.poll(); 
            
            // 다음 플랜 시작시간 전까지 현재 플랜을 완료할 수 있으면
            // answer에 넣고 + now 갱신
            if(curr.start + curr.playtime <= next.start){
                answer[idx++] = curr.name;
                now = curr.start + curr.playtime;
                
                // 다음 플랜 시작 시간까지 미완료 플랜 진행
                while(now < next.start && !stk.isEmpty()){
                    undone = stk.pop();
                    
                    // 다음 플랜 시작 시간까지 미완료 플랜을 완료할 수 있으면
                    if(now + undone.playtime <= next.start){
                        answer[idx++] = undone.name;
                        now += undone.playtime;
                    }else{  
                    // 다음 플랜 시작 시간까지 미완료 플랜을 완료할 수 없으면 : 남은 시간만 갱신
                        undone.playtime -= next.start - now;
                        stk.push(undone);
                        now = next.start;
                    }
                }
            }else{ // 다음 플랜 시작시간 전까지 현재 플랜을 완료할 수 없으면
                curr.playtime -= next.start - now;
                stk.push(curr);
                now = next.start;
            }
            
            curr = next;
        }
        
        // 마지막 next 처리
        answer[idx++] = next.name;
        
        // stk에 남은 일정 처리
        while(!stk.isEmpty()){
            answer[idx++] = stk.pop().name;
        }
    }
}