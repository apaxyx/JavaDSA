package Algorithm.sort;

public class QuickSort {
    public static void sort(int[] arr, int left, int right){
        int l = left;
        int r = right;

        int pivot = arr[(left + right) / 2];
        int temp = 0;

        while(l < r){

            //从数组最左边开始找到一个不小于比较值的数
            while(arr[l] < pivot){
                //l++;
                l += 1;
            }
            //从数组最右边开始找到一个不大于比较值的数
            while(arr[r] > pivot){
               //r--;
                r -= 1;
            }
            //l >= r时，说明l右边全是比pivot大的值，r的左边全是比pivot小的值
            if(l >= r){
                break;
            }

            //将两个值进行交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果arr[l] == pivot,说明之前的arr[r] == pivot,此时arr[r] > pivot,r--跳过本次这个元素
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果arr[r] == pivot,说明之前的arr[l] == pivot,此时arr[l] < pivot,l++跳过本次这个元素
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果不移动，会出现栈溢出？？？
        //因为递归到两个元素的时候，必然会出现l==r的情况这时递归时会出现
        //比如sort(arr,0,1)时，l = 0, r = 1,
        //当进行到l = r = 0时，调用向右递归，即sort(arr,l,right),即sort(arr,0,1)
        //当进行到l = r = 1时，调用向左递归，即sort(arr,left, r),即sort(arr,0,1)
        //以上两种情况都会导致无限重复执行sort(arr, 0, 1)导致栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            sort(arr, left, r);
        }

        //向右递归
        if(right > l){
            sort(arr, l, right);
        }
    }
}
