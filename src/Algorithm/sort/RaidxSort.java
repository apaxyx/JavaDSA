package Algorithm.sort;
//基数排序，基于桶排序
//空间换时间
//有负数不使用基数排序
//若要支持负数，需要将负数取绝对值变为正数排序之后再反转再把它们放到数组的前面
public class RaidxSort {
    public static void sort(int[] arr){
        //首先获取数组中最大的数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }

        //获取最大数的位数
        int maxLength = (max + "").length();

        //创建桶，实际上就是二维数组，每个桶长度都是arr.length，这个条件使得基数排序占用很大的空间
        int[][] bucket = new int[10][arr.length];
        //用一个数组记录每一个桶中的有效元素个数
        int[] bucketElementCounts = new int[10];

        //数组中最大数的位数决定了进行几次桶排序，用n来获得元素某位数的值
        for(int i = 0, n = 1; i < maxLength; i++, n *= 10){

            //遍历数组
            for(int j = 0; j < arr.length; j++){
                int digitOfElement = arr[j] / n % 10;

                //第一个[]决定了要放在第几个桶，第二个[]决定了放在桶中的第几位，初始为0
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;//放完后加1，下次再放元素就是下一位
            }

            int index = 0;

            //对每个桶按顺序进行取值放回原数组
            for(int k = 0; k < bucketElementCounts.length; k++){

                //该数组中元素个数即为第几个桶中有效元素个数
                if(bucketElementCounts[k] != 0){
                    for(int l = 0; l < bucketElementCounts[k]; l++){
                        arr[index++] = bucket[k][l];
                    }
                }
                //取完后要把记录值归0，因为取完了，下次还要使用
                bucketElementCounts[k] = 0;
            }
        }
    }
}
