package Algorithm.recursion;
//八皇后问题
public class Queen8 {
    int max = 8;//共有多少个皇后

    //定义数组array, 保存皇后放置位置的结果,
    //比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    private void check(int n){
        if(n == max){
            print();
            return ;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            if(judge(n)){
                check(n + 1);
            }
        }
    }

    private boolean judge(int n){
        judgeCount++;
        for (int i = 0; i < n; i++) {
            if(array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    private void print(){
        count++;
       /* for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "");
        }
        System.out.println();*/

        char[][] pic = new char[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(j == array[i]){
                    pic[i][j] = '*';
                }
                else{
                    pic[i][j] = 'O';
                }
                System.out.print(pic[i][j]);
            }
            System.out.println();
        }
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        Queen8 q = new Queen8();
        q.check(0);
        System.out.printf("一共有%d种解法", count);
        System.out.printf("一共判断产嚓的次数%d次", judgeCount);
    }
}
