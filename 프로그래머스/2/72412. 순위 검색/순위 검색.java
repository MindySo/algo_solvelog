import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap();
        char[] lang = {'c', 'j', 'p', '-'};
        char[] pos = {'b', 'f', '-'};
        char[] exp = {'j', 's', '-'};
        char[] food = {'c', 'p', '-'};
        
        for(char l : lang){
            String s1 = "" + l;
            for(char p : pos){
                String s2 = s1 + p;
                for(char e : exp){
                    String s3 = s2 + e;
                    for(char f : food){
                        String s4 = s3 + f;
                        map.put(s4, new ArrayList());
                    }
                }
            }
        }
        
        lang = new char[] {'-', '-'};
        pos = new char[] {'-', '-'};
        exp = new char[] {'-', '-'};
        food = new char[] {'-', '-'};
        for(String str : info){
            StringTokenizer st = new StringTokenizer(str);
            lang[0] = st.nextToken().charAt(0);
            pos[0] = st.nextToken().charAt(0);
            exp[0] = st.nextToken().charAt(0);
            food[0] = st.nextToken().charAt(0);
            int score = Integer.parseInt(st.nextToken());
            
            for(char l : lang){
                String s1 = "" + l;
                for(char p : pos){
                    String s2 = s1 + p;
                    for(char e : exp){
                        String s3 = s2 + e;
                        for(char f : food){
                            String s4 = s3 + f;
                            map.get(s4).add(score);
                        }
                    }
                }
            }
        }
        
        for(List<Integer> list : map.values()){
            Collections.sort(list);
        }
        
        int[] answer = new int[query.length];
        int idx = 0;
        for(String str : query){
            StringTokenizer st = new StringTokenizer(str);
            String req = "" + st.nextToken().charAt(0);
            st.nextToken();
            req += st.nextToken().charAt(0);
            st.nextToken();
            req += st.nextToken().charAt(0);
            st.nextToken();
            req += st.nextToken().charAt(0);
            
            int reqScore = Integer.parseInt(st.nextToken());
            int start = 0;
            int end = map.get(req).size();
            int mid = 0;
            while(start < end){
                mid = (start + end) / 2;
                if(map.get(req).get(mid) >= reqScore){
                    end = mid;
                }else{
                    start = mid + 1;
                }
            }
            
            answer[idx++] = map.get(req).size() - start;
        }
        
        return answer;
    }
}