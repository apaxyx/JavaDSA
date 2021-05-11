package DataStructures.tree;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/*
  堆排序是利用堆这种数据结构而设计的一种排序算法，堆排序是一种选择排序，它的
最坏，最好，平均时间复杂度均为O(nlogn)，它也是不稳定排序
  堆是具有以下性质的完全二叉树：每个结点的值都大于或等于其左右孩子结点的值，
称为大顶堆，注意，没有要求结点的左孩子的值和右孩子的值的大小关系
  小顶堆每个结点的值都小于或等于其左右孩子结点的值，称为小顶堆

*/
/*
大顶堆特点
arr[i] >= arr[2 * i + 1] && arr[i] >= arr[2 * i + 2]
i对应第几个结点，i从0开始编号

小顶堆特点
arr[i] <= arr[2 * i + 1] && arr[i] <= arr[2 * i + 2]
i对应第几个结点，i从0开始编号

大顶堆升序
小顶堆降序

完全二叉树的最后一个非叶子结点的位置，arr.length / 2 - 1
*/
public class HeapSort {

    public static void main(String[] args) {
        //要求将数组进行升序排序
        //int arr[] = {4, 6, 8, 5, 9};
        // 创建要给80000个的随机的数组
        int[] arr = new int[800];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 8000000); // 生成一个[0, 8000000) 数
        }

        System.out.println("排序前");
        Date data1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(data1);
        System.out.println("排序前的时间是=" + date1Str);

        heapSort(arr);

        Date data2 = new Date();
        String date2Str = simpleDateFormat.format(data2);
        System.out.println("排序前的时间是=" + date2Str);
        System.out.println("排序后=" + Arrays.toString(arr));
    }

    //堆排序
    public static void heapSort(int arr[]) {
        int temp = 0;
        System.out.println("堆排序");

        //最后一个非叶子节点的位置就是arr.length / 2 - 1
        //从最后一个非叶子节点开始调整，调整到根
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        /*
        将最大元素沉到数组末端
        并且重新调堆结构，使其满足堆定义
        每次调整传入的数组长度都减一，
        因为当前最大的都换到了数组的后面
         */
        for(int j = arr.length - 1; j > 0; j--){
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }


    /*
    i传入的是最后一个非叶子结点的值
    也就是从至少三个节点的子树开始调整
    功能是调整数组为一个大顶堆，即结点比左右孩子的值都大
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        //记录临时值，以备交换数值用
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //这个结点的左右孩子先比较一下大小，把大的调整到它们的父结点上
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;//此时i变成了和它交换的孩子结点的位置索引值
            } else {
                break;
            }
        }
        //如果子树中有比传进来的i结点大的，此时arr[i]就是那个子结点
        //否则arr[i]还是这个结点
        arr[i] = temp;
    }
}
