package DataStructures.sparearray;

//稀疏数组
//用来将五子棋盘压缩保存到硬盘上
public class SparseArray {
    public static void main(String[] args) {
        //二维数组11 * 11
        //0：表示没有棋子 1:表示黑子 2：表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        System.out.println("原始的二维数组");
        for (int[] row : chessArr1) {
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        //第一行存储原数组的行列值和非0值的个数
        //然后有几个非0值便创建几行数组
        //稀疏数组只有3列，每一行分别保存第几个非0值的行、列和值的信息
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if(chessArr1[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0],
            sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复成二维数组
         int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println();
        System.out.println("恢复后的二维数组");

        for(int[] row : chessArr2){
            for(int data : row){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
