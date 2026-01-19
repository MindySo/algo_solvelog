import java.util.*;
class Solution {
    static class Robot {
        int r, c, next;
        int[] route;
    }
    static int[][] map;
    static int[][] garages;
    static Set<Integer> checkSet;
    static Set<Robot> doneSet;
    static int crash;
    
    public int solution(int[][] points, int[][] routes) {
        List<Robot> robots = new ArrayList();
        map = new int[101][101];
        garages = points;
        checkSet = new HashSet();
        doneSet = new HashSet();
        crash = 0;
        
        for(int i = 0; i < routes.length; i++){
            Robot robot = new Robot();
            robot.route = routes[i];
            robot.next = 1;
            robot.r = garages[robot.route[0] - 1][0];
            robot.c = garages[robot.route[0] - 1][1];
            robots.add(robot);
            if(++map[robot.r][robot.c] == 2) crash++;
        }
        
        while(true){
            checkSet.clear();
            doneSet.clear();
            
            for(Robot r : robots){
                if(r.next != 0) move(r);
            }
            
            if(checkSet.isEmpty()) break;
            
            for(int check : checkSet){
                if(map[check / 101][check % 101] >= 2) crash++;
            }
            
            for(Robot r : doneSet){
                map[r.r][r.c]--;
            }
        }
        
        return crash;
    }
    
    public void move(Robot r){
        int point = r.route[r.next] - 1;
        int nextR = garages[point][0];
        int nextC = garages[point][1];
        
        map[r.r][r.c]--;
        
        if(r.r > nextR){
            r.r--;
        }else if(r.r < nextR){
            r.r++;
        }else if(r.c > nextC){
            r.c--;
        }else if(r.c < nextC){
            r.c++;
        }
        
        map[r.r][r.c]++;
        checkSet.add(r.r * 101 + r.c);
        
        if(r.r == nextR && r.c == nextC){
            if(r.next < r.route.length - 1){
                r.next++;
            }else{
                r.next = 0;
                doneSet.add(r);
            }
        }
    }
}
