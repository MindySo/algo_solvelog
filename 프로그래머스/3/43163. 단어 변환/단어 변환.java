import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int len = words[0].length();
        
        List<String> wordList = new ArrayList();
        int start = -1;
        int end = -1;
        
        for(int i = 0; i < words.length; i++) {
            if(words[i].equals(begin)) start = i;
            if(words[i].equals(target)) end = i;
            wordList.add(words[i]);
        }
        
        if(end == -1) return 0;
        
        if(start == -1) {
            wordList.add(begin);
            start = wordList.size() - 1;
        }
        
        Map<Integer, List<Integer>> wordMap = new HashMap();
        
        for(int i = 0; i < wordList.size(); i++){
            wordMap.put(i, new ArrayList());

            for(int j = 0; j < wordList.size(); j++){
                if(i == j) continue;
                int cnt = 0;
                for(int c = 0; c < len; c++){
                    if(wordList.get(i).charAt(c) == wordList.get(j).charAt(c)) cnt++;
                }
                if(cnt == len - 1) wordMap.get(i).add(j);
            }
        }
        
        boolean[] visited = new boolean[wordList.size()];
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[] {start, 0});
        
        int answer = 0;
        while(!que.isEmpty()){
            int[] temp = que.poll();
            int curr = temp[0];
            int tried = temp[1];
            
            if(curr == end){
                answer = tried;
                break;
            }
            
            if(visited[curr]) continue;
            visited[curr] = true;
            
            for(int next : wordMap.get(curr))
                if(!visited[next]) que.add(new int[] {next, tried + 1});
        }
        
        return answer;
    }
}