import java.util.*;
class Solution {
    static class MaxCourse{
        int maxCnt;
        List<String> courses;
    }
    static Map<String, Integer> map;
    static MaxCourse[] maxCourses;
    
    public String[] solution(String[] orders, int[] course) {
        map = new HashMap();
        maxCourses = new MaxCourse[course[course.length - 1] + 1];
        for(int i = 0; i < maxCourses.length; i++){
            maxCourses[i] = new MaxCourse();
            maxCourses[i].maxCnt = 0;
            maxCourses[i].courses = new ArrayList();
        }
        
        for(String order : orders){
            char[] sortedOrder = new char[order.length()];
            for(int i = 0; i < order.length(); i++){
                sortedOrder[i] = order.charAt(i);
            }
            Arrays.sort(sortedOrder);
            
            for(int c = 0; c < course.length; c++){
                DFS(sortedOrder, "", course[c], 0);
            }
        }

        List<String> list = new ArrayList();
        for(MaxCourse mc : maxCourses){
            if(mc.maxCnt >= 2){
                for(String str : mc.courses){
                    list.add(str);
                }
            }
        }
        
        String[] answer = new String[list.size()];
        int idx = 0;
        Collections.sort(list);
        for(String str : list){
            answer[idx++] = str;
        }
        
        return answer;
    }
    
    static void DFS(char[] sortedOrder, String str, int course, int idx){
        if(str.length() == course){
            map.put(str, map.getOrDefault(str, 0) + 1);
            
            if(map.get(str) > maxCourses[course].maxCnt){
                maxCourses[course].maxCnt = map.get(str);
                maxCourses[course].courses.clear();
                maxCourses[course].courses.add(str);
            }else if(map.get(str) == maxCourses[course].maxCnt){
                maxCourses[course].courses.add(str);
            }
            return;
        }
        
        if(idx == sortedOrder.length) return;
            
        for(int i = idx; i < sortedOrder.length; i++){
            DFS(sortedOrder, str + sortedOrder[i], course, i + 1);
        }
    }
}