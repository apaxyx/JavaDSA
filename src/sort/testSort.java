package sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class testSort {
    public static void main(String[] args) {
        int range = 8192;//数组长度，同时也是要排序的数的总量

        int[] arr = new int[range];//待排序数组

        //合并排序用临时数组
        int[] temp = new int[arr.length];

        //生成随机数
        for (int i = 0; i < range; i++) {
            arr[i] = (int) (Math.random() * 100000);//随机数取值在0---100000
        }
        //生成ArrayList供快速排序
        //int[] arr1 = {10, 11, 12, 0, 13, 14, 15};
        int[] arr1 = {20,19};

        /*
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            arr.add((int) (Math.random() * 100000));
        }
        */
        //System.out.println(arr);

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);

        //冒泡排序
        //BubbleSort.sort(arr);

        //选择排序
        //SelectSort.sort(arr);

        //插入排序
        //InsertSort.sort(arr);
        //插入排序，代码结构优化版
        //InsertSort.sort_c(arr);

        //希尔排序，在插入排序的基础上优化
        //ShellInsertSort.sort01(arr);//希尔排序交换法，比插入排序还慢
        //ShellInsertSort.sort02(arr);//希尔排序移位法，比插入排序快
        //ShellInsertSort.sort03(arr);//希尔排序，DSAAA_use_C一书中的代码学习版

        //快速排序，韩顺平老师版
        QuickSort.sort(arr1, 0, arr1.length - 1);

        //快速排序，C语言拷贝版，三数中值法取对比值
        //QSort.sort(arr, 0, arr.length - 1);

        //快速排序，简单易懂，C++Vector改JavaArrayList版
        //QSort.listSort(arr);

        //合并排序
        //MergeSort.sort(arr,0, arr.length - 1, temp);

        //基数排序
        //RaidxSort.sort(arr);

        //输出已经排序好的数组
        System.out.print(Arrays.toString(arr1) + "\t");

        //输出ArrayList版快速排序的ArrayList
        //System.out.println(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);

        System.out.println("\n排序前的时间——" + date1Str);
        System.out.println("排序后的时间——" + date2Str);
    }
}
