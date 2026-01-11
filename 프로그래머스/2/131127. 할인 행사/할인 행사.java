import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        Map<String, Integer> discountMap = new HashMap();
        Map<String, Integer> wantMap = new HashMap();
        Set<String> canGet = new HashSet();
        int answer = 0;
        
        for(int i = 0; i < want.length; i++){
            wantMap.put(want[i], number[i]);
        }
        
        for(int i = 0; i < 10; i++){
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }
        
        for(String str : want){
            if(discountMap.getOrDefault(str, 0) >= wantMap.getOrDefault(str, 0)){
                canGet.add(str);
            }
        }
        
        if(canGet.size() == want.length){
            answer++;
        }
        
        String out, in;
        int amount;
        for(int day = 0; day < discount.length - 10; day++){
            // 기존 열흘 중 첫날 물건은 세일 빠짐
            out = discount[day];
            amount = discountMap.getOrDefault(out, 0);
            amount = amount > 0 ? amount - 1 : 0;
            discountMap.put(out, amount);
            
            if(amount < wantMap.getOrDefault(out, 0)){
                canGet.remove(out);
            }
            
            // 기존 열흘의 다음 날 물건이 세일임
            in = discount[day + 10];
            amount = discountMap.getOrDefault(in, 0) + 1;
            discountMap.put(in, amount);
            
            if(wantMap.containsKey(in) && amount >= wantMap.getOrDefault(in, 0)){
                canGet.add(in);
            }
            
            // 원하는 모든 품목을 할인받는가
            if(canGet.size() == want.length){
                answer++;
            }
        }
        return answer;
    }
}