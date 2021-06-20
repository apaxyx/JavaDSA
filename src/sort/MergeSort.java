package sort;
/*
合并排序
采用分治思想
*/
public class MergeSort {
    public static void sort(int[] arr, int left, int right, int[] temp){
        if(left < right){
            int mid = (left + right) / 2;
            sort(arr, left, mid, temp);
            sort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //合并操作
        //左边的数组元素（从第一个元素开始）和右边的数组的元素（从第一个元素开始）对比，小的放入临时数组
        while(i <= mid && j <= right){
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t++;
                i++;
            }else{
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //上一步操作后，可能会有一个数组还有剩余元素，将它们顺序放入临时数组的后面
        while(i <= mid){
            temp[t] = arr[i];
            t++;
            i++;
        }

        while(j <= right){
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将临时数组的元素拷贝回原数组
        t = 0;
        int tempLeft = left;//这里的tempLeft可能是为了便于理解
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
      /*  while(left <= right){
            arr[left] = temp[t];
            t++;
            left++;
        }*/
    }
}
