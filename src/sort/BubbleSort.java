package sort;
//冒泡排序
/*
  从第一个元素开始相邻两个元素比大小，大的放在后面
每一趟排序把数组中的元素都比较一遍，这样最大的元素就放在了最后一个
  下一趟比较继续从第一个元素开始，将它和和相邻的元素，比较到倒数第二个元素，
这样第二大的元素就放在了数组倒数第二个位置
  最后数组排序完成，一共排序(arr.length - 1)次
*/

public class BubbleSort {

    public static void sort(int[] arr){
        int temp = 0;
        boolean flag = true;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length -1 - i; j++) {
                if(arr[j] > arr[j + 1]){
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//这里做一个优化，即没有运行交换代码时说明数组已经有序，
//可以不用再进行排序，就可以结束循环
            if(flag){
                break;
            }else{
//需要重置，因为下一趟排序时如果没有进行元素交换，
//flag == false,那么就不会跳出循环,优化就不能完成
                flag = true;
            }
        }
    }
}
