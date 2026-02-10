import java.util.*;
class Solution {
    public String[] solution(String[] files) {
        Map<String, String> heads = new HashMap();
        Map<String, Integer> numbers = new HashMap();
        Map<String, Integer> idx = new HashMap();
        
        int index = 0, order;
        char c;
        for(String file : files){
            idx.put(file, index++);
            order = 0;
            
            String head = "";
            while(!(file.charAt(order) >= '0' && file.charAt(order) <= '9')) {
                c = file.charAt(order++);
                if(c >= 'a' && c <= 'z') c -= 32;
                head += c;
            }
            heads.put(file, head);
            
            String number = "";
            while(order < file.length() && number.length() <= 5 
                  && file.charAt(order) >= '0' && file.charAt(order) <= '9') {
                number += file.charAt(order++) - '0';
            }
            numbers.put(file, Integer.parseInt(number));
        }
        
        Arrays.sort(files, new Comparator<String>(){
           public int compare(String o1, String o2){
               String head1 = heads.get(o1);
               String head2 = heads.get(o2);
               int len1 = head1.length();
               int len2 = head2.length();
               
               int size = Math.min(len1, len2);
               for(int i = 0; i < size; i++){
                   if(head1.charAt(i) != head2.charAt(i)) return head1.charAt(i) - head2.charAt(i);
               }
               
               if(len1 != len2){
                   return len1 - len2;
               }
             
               
               int num1 = numbers.get(o1);
               int num2 = numbers.get(o2);
               if(num1 != num2) return num1 - num2;
               
               return idx.get(o1) - idx.get(o2);
           } 
        });
        
        return files;
    }
}