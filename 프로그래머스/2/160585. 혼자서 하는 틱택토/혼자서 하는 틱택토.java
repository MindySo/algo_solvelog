class Solution {
    public int solution(String[] board) {
        int oCnt = 0, xCnt = 0;
        
        for(String row : board){
            for(char c : row.toCharArray()){
                if(c == 'O') oCnt++;
                else if(c == 'X') xCnt++;
            }
        }
        
        // 1. 개수 검증: O == X 또는 O == X + 1
        if(oCnt < xCnt || oCnt > xCnt + 1){
            return 0;
        }
        
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        
        // 2. 둘 다 이길 수 없음
        if(oWin && xWin){
            return 0;
        }
        
        // 3. O가 이겼는데 X가 더 뒀으면 안됨
        if(oWin && oCnt == xCnt){
            return 0;
        }
        
        // 4. X가 이겼는데 O가 더 뒀으면 안됨
        if(xWin && oCnt > xCnt){
            return 0;
        }
        
        return 1;
    }
    
    private boolean isWin(String[] board, char mark){
        for(int i = 0; i < 3; i++){
            if(board[i].charAt(0) == mark && 
               board[i].charAt(1) == mark && 
               board[i].charAt(2) == mark){
                return true;
            }
        }
        
        for(int j = 0; j < 3; j++){
            if(board[0].charAt(j) == mark && 
               board[1].charAt(j) == mark && 
               board[2].charAt(j) == mark){
                return true;
            }
        }
        
        if(board[0].charAt(0) == mark && 
           board[1].charAt(1) == mark && 
           board[2].charAt(2) == mark){
            return true;
        }
        
        if(board[0].charAt(2) == mark && 
           board[1].charAt(1) == mark && 
           board[2].charAt(0) == mark){
            return true;
        }
        
        return false;
    }
}