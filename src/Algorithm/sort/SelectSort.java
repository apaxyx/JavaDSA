package Algorithm.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
/*
  设数组中第一个元素为最小，然后让第一个元素和第二个元素及之后的元素依次比较，只要这个元素
比第一个元素小就用这个元素继续和后面的元素比较，第一轮比到数组最后一个元素，这样得到的这个
元素就是数组中最小的，把它和数组第一个元素交换（如果没有比第一个元素小的就不交换）
  然后设此时数组中最小的元素是第二个元素，从第二个元素开始，让它和后面的元素依次比较，此轮
比较也可以得到从第二个元素到数组最后一个元素之间最小的元素，把它再放到第二位，这样依次进行
直到最后一个元素可以不用比较了，因为前面的元素都比它小，所以进行arr.length - 1趟，每趟比较
从那一趟开始的元素到最后一个元素的次数
此时数组中
*/

public class SelectSort {
    public static void sort(int[] arr){
        int minIndex;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;//记录最小值的索引
            min = arr[i];//记录开始元素，设它为最小值
            for (int j = i + 1; j < arr.length; j++) {
                if(min > arr[j]){//如果发现比最小值小的值的话
                    min = arr[j];//记录当前最小值
                    minIndex = j;//记录当前最小值的索引
                }
            }
            if(minIndex != i){//最小值索引改变说明需要改变当前最小值
                arr[minIndex] = arr[i];//把这个值交换到记录的最小值的位置
                arr[i] = min;//把最小值放到它应该在的位置
            }
        }
    }

}
