import java.util.*;
class Solution {
    static int[][] map;
    static int[][] robots;
    static int[][] garages;
    static int[][] ways;
    static int[] target;
    static Set<Integer> undone;
    static Set<Integer> checkSet;
    static Set<Integer> outSet;
    static int crash;
    public int solution(int[][] points, int[][] routes) {
        map = new int[101][101];
        robots = new int[routes.length][2];
        garages = points;
        ways = routes;
        target = new int[routes.length];
        undone = new HashSet();
        checkSet = new HashSet();
        outSet = new HashSet();
        crash = 0;
        
        for(int i = 0; i < routes.length; i++){
            undone.add(i);
            robots[i][0] = garages[routes[i][0] - 1][0];
            robots[i][1] = garages[routes[i][0] - 1][1];
            target[i] = 1;
            if(++map[robots[i][0]][robots[i][1]] == 2) crash++;
        }
        
        while(true){
            checkSet.clear();
            outSet.clear();
            
            for(int r = 0; r < robots.length; r++){
                if(target[r] != 0) move(r);
            }
            
            if(checkSet.isEmpty()) break;
            
            for(int check : checkSet){
                if(map[check / 101][check % 101] >= 2) crash++;
            }
            
            for(int r : outSet){
                map[robots[r][0]][robots[r][1]]--;
            }
        }
        
        return crash;
    }
    
    public void move(int robot){
        int currR = robots[robot][0];
        int currC = robots[robot][1];
        int point = ways[robot][target[robot]] - 1;
        int targetR = garages[point][0];
        int targetC = garages[point][1];
        
        map[currR][currC]--;
        
        if(currR > targetR){
            robots[robot][0] = --currR;
        }else if(currR < targetR){
            robots[robot][0] = ++currR;
        }else if(currC > targetC){
            robots[robot][1] = --currC;
        }else if(currC < targetC){
            robots[robot][1] = ++currC;
        }
        
        map[currR][currC]++;
        checkSet.add(currR * 101 + currC);
        
        if(currR == targetR && currC == targetC){
            if(target[robot] < ways[robot].length - 1){
                target[robot]++;
            }else{
                target[robot] = 0;
                outSet.add(robot);
            }
        }
    }
}