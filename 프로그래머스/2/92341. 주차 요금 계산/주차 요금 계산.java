import java.util.*;

class Solution {
    static Map<Integer, Integer> parked;
    static Map<Integer, Integer> finalTimes;
    static Map<Integer, Integer> finalFees;
    static int basicTime, basicFee, perTime, perFee, closeTime;    
    
    public int[] solution(int[] fees, String[] records) {
        parked = new HashMap();
        finalTimes = new HashMap();
        finalFees = new TreeMap<>();
        
        basicTime = fees[0];
        basicFee = fees[1];
        perTime = fees[2];
        perFee = fees[3];
        closeTime = 23 * 60 + 59;
        
        int now, time, h, m, car;
        for(String str : records){
            h = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
            m = (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
            now = h * 60 + m;
            
            car = (str.charAt(6) - '0') * 1000
                + (str.charAt(7) - '0') * 100
                + (str.charAt(8) - '0') * 10
                + (str.charAt(9) - '0');
            
            if(str.charAt(11) == 'I'){
                parked.put(car, now);
            }else{
                addFee(car, now);
                parked.remove(car);
            }
        }
        
        for(int remainCar : parked.keySet()){
            addFee(remainCar, closeTime);
        }
        
        for(int parkedCar : finalTimes.keySet()){
            calculate(parkedCar);
        }
        
        return finalFees.values().stream()
                .mapToInt(Integer::intValue)
                .toArray();
    }
    
    public void addFee(int car, int now){
        int time = now - parked.get(car);
        finalTimes.put(car, finalTimes.getOrDefault(car, 0) + time);
    }
    
    public void calculate(int car){
        int fee = basicFee;
        int exceed = finalTimes.get(car) - basicTime;

        if(exceed > 0){
            int perOver = exceed / perTime;
            if(exceed % perTime > 0){
                perOver++;
            }
            fee += perFee * perOver;
        }
        finalFees.put(car, fee);
    }
}