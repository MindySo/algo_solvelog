import java.util.*;

class Solution {
    static List<int[]> bookList;
    
    public int solution(String[][] book_time) {
        //{{startTime, endTime, isPlaced}}
        bookList = new ArrayList();
        for(String[] book : book_time){
            bookList.add(new int[] {
                convertToMinute(book[0]), convertToMinute(book[1]), 0});
        }
        
        Collections.sort(bookList, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
               return o1[0] - o2[0];
           } 
        });
        
        int rooms = 0;
        int[] curr;
        for(int idx = 0; idx < bookList.size(); idx++){
            if(bookList.get(idx)[2] == 0){
                rooms++;
                placeBook(idx);
            }
        }
        
        return rooms;
    }
    
    public int convertToMinute(String str){
        int h = ((str.charAt(0) - '0') * 10) + (str.charAt(1) - '0');
        int m = ((str.charAt(3) - '0') * 10) + (str.charAt(4) - '0');
        return (h * 60 + m);
    }
    
    public void placeBook(int idx){
        int[] book = bookList.get(idx);
        
        int target = book[1] + 10;
        bookList.get(idx)[2] = 1;
        
        int start = 0;
        int end = bookList.size() - 1;
        int mid = (start + end) / 2;
        
        while(start <= end){
            mid = (start + end) / 2;
            
            if(bookList.get(mid)[0] < target){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        
        if(start >= bookList.size()){
            return;
        }
        
        while(true){
            if(start >= bookList.size()){
                return;
            }else if(bookList.get(start)[2] == 0){
                break;
            }
            start++;
        }
        
        placeBook(start);
    }
}