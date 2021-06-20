package sort;
//希尔排序ShellSort
/*
基于插入排序，解决插入排序时小的数据在数组靠后位置时元素交换次数过多导致的性能问题
基于分组的插入排序
第一次按数组长度的一半分组，即步长 = arr.length / 2
每完成一轮插入排序就把步长设为原来的一半，即gap /= 2
我的理解，将数组gap位置的元素作为待操作插入元素，gap位置之前每相隔gap位置的元素看作有序表进行插入排序
此轮结束，再从gap+1位置开始，直到数组最后一个元素
然后再从gap / 2的位置开始重复
直到gap = 1(有可能在gap = 1之前数组已经有序了，待优化？待理解)，此时的数组已经接近或者已经排序好，
那么对这样一个数组进行插入排序就会减少很多的移位操作，从而提高性能
所以要记就记希尔排序，插入排序不用记
（当然可以在插入排序的基础上记忆希尔排序，推荐记忆从C语言版本改过来的Java版本，因为代码行数更少）

*/
public class ShellInsertSort {
    //交换法，速度比插入排序还慢
    public static void sort01(int[] arr){
        int temp = 0;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                for(int j = i - gap; j >= 0; j -= gap){
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //移位法，真正的有用的快速的希尔排序
    public static void sort02(int[] arr){
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while(j - gap >= 0 && temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    arr[j] = temp;
                }
            }
        }
    }
    //《数据结构与算法分析——使用C语言》一书第七章中的希尔排序，使用Java语言重写，几乎没变化
    public static void sort03(int[] arr){
        int temp;
        int j;

        for(int gap = arr.length / 2; gap > 0; gap /= 2){

            for(int i = gap; i < arr.length; i++){
                temp = arr[i];
                for(j = i; j >=gap && temp < arr[j - gap]; j -= gap){
                    arr[j] = arr[j - gap];
                }
                /*for(j = i; j >=gap; j -= gap){
                    if(temp < arr[j - gap]){
                        arr[j] = arr[j - gap];
                    }else{
                        break;
                    }
                }*/
                arr[j] = temp;
            }
        }
    }

    public static void sort_c(int[] arr){
        int temp;
        int j;
        for(int gap = arr.length / 2; gap > 0; gap /= 2){
            for(int i = gap; i < arr.length; i++){
                temp = arr[i];
                for(j = i; j >= gap && temp < arr[j - gap]; j -= gap){
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }

        }
    }
}
