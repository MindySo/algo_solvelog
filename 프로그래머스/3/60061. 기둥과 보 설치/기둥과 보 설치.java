import java.util.*;
class Solution {
    static int N;
    static Set<Integer> pillar;
    static Set<Integer> floor;
    public int[][] solution(int n, int[][] build_frame) {
        N = n + 1;
        pillar = new HashSet();
        floor = new HashSet();
        
        for(int[] frame : build_frame){
            int r = frame[1];
            int c = frame[0];
            boolean isFloor = frame[2] == 0 ? false : true;;
            boolean isBuild = frame[3] == 0 ? false : true;
            
            int point = r * N + c;
            if(isFloor){
                if(isBuild){
                    if(checkFloor(point)) floor.add(point);
                }else{
                    boolean stable = true;
                    floor.remove(point);
                    
                    if(c > 0 && floor.contains(point - 1) 
                       && !checkFloor(point - 1)) stable = false;
                    if(floor.contains(point + 1)
                       && !checkFloor(point + 1)) stable = false;
                    if(pillar.contains(point)
                      && !checkPillar(point)) stable = false;
                    if(pillar.contains(point + 1)
                      && !checkPillar(point + 1)) stable = false;
                    
                    if(!stable) floor.add(point);
                }
            }else{
                if(isBuild){
                    if(checkPillar(point)) pillar.add(point);
                }else{
                    boolean stable = true;
                    pillar.remove(point);
                    
                    if(floor.contains(point + N)
                       && !checkFloor(point + N)) stable = false;
                    if(c > 0 && floor.contains(point + N - 1) 
                       && !checkFloor(point + N - 1)) stable = false;
                    if(r < N - 1 && pillar.contains(point + N)
                       && !checkPillar(point + N)) stable = false;
                    
                    if(!stable) pillar.add(point);
                }
            }
        }
        
        int[][] answer = new int[pillar.size() + floor.size()][3];
        int idx = 0;
        
        for(int point : pillar){
            answer[idx][0] = point % N;
            answer[idx++][1] = point / N;
        }
        
        for(int point : floor){
            answer[idx][0] = point % N;
            answer[idx][1] = point / N;
            answer[idx++][2] = 1;
        }
        
        Arrays.sort(answer, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
                if(o1[0] != o2[0]) return o1[0] - o2[0];
                if(o1[1] != o2[1]) return o1[1] - o2[1];
                return o1[2] - o2[2];
           } 
        });
        
        return answer;
    }
    
    public boolean checkFloor(int point){
        int r = point / N;
        int c = point % N;

        if(pillar.contains(point - N) 
            || pillar.contains(point - N + 1)
            || (floor.contains(point - 1) 
                && floor.contains(point + 1))) return true;
        return false;
    }
    
    public boolean checkPillar(int point){
        int r = point / N;
        int c = point % N;
        
        if(r == 0 || pillar.contains(point - N)
            || floor.contains(point)
            || (c > 0 && floor.contains(point - 1))) return true;
        return false;
    }
}