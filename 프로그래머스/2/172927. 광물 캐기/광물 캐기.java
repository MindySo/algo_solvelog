import java.util.*;

class Solution {
    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        int mineralCount = Math.min(totalPicks * 5, minerals.length);
        
        // 5개씩 그룹으로 나누기
        List<Group> groups = new ArrayList<>();
        
        for (int i = 0; i < mineralCount; i += 5) {
            int diamond = 0, iron = 0, stone = 0;
            
            for (int j = i; j < Math.min(i + 5, mineralCount); j++) {
                if (minerals[j].equals("diamond")) diamond++;
                else if (minerals[j].equals("iron")) iron++;
                else stone++;
            }
            
            groups.add(new Group(diamond, iron, stone));
        }
        
        // 각 그룹의 피로도 차이(돌 곡괭이 vs 다이아몬드 곡괭이)를 기준으로 내림차순 정렬
        Collections.sort(groups, (a, b) -> {
            int diffA = a.getStoneFatigue() - a.getDiamondFatigue();
            int diffB = b.getStoneFatigue() - b.getDiamondFatigue();
            return diffB - diffA; // 차이가 큰 순서대로
        });
        
        int totalFatigue = 0;
        int groupIdx = 0;
        
        // 다이아몬드 곡괭이부터 사용
        for (int i = 0; i < picks[0] && groupIdx < groups.size(); i++) {
            totalFatigue += groups.get(groupIdx++).getDiamondFatigue();
        }
        
        // 철 곡괭이 사용
        for (int i = 0; i < picks[1] && groupIdx < groups.size(); i++) {
            totalFatigue += groups.get(groupIdx++).getIronFatigue();
        }
        
        // 돌 곡괭이 사용
        for (int i = 0; i < picks[2] && groupIdx < groups.size(); i++) {
            totalFatigue += groups.get(groupIdx++).getStoneFatigue();
        }
        
        return totalFatigue;
    }
    
    class Group {
        int diamond, iron, stone;
        
        Group(int diamond, int iron, int stone) {
            this.diamond = diamond;
            this.iron = iron;
            this.stone = stone;
        }
        
        int getDiamondFatigue() {
            return diamond + iron + stone; // 모든 광물에 피로도 1
        }
        
        int getIronFatigue() {
            return diamond * 5 + iron + stone;
        }
        
        int getStoneFatigue() {
            return diamond * 25 + iron * 5 + stone;
        }
    }
}