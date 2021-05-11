package Algorithm.search;
//插值查找
//基于二分查找，要查找的索引由另一个公式得出
//对于数据量较大，关键字分布比较均匀的查找来说，采用插值查找，速度较快
//关键字分布不均匀的情况下，该方法不一定比二分查找要好
//公式为，int mid = low + (high - low) * (key - arr[low]) / (arr[high] - arr[low])
//可以理解为 int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };

        int index = insertValueSearch(arr, 0, arr.length - 1, 1234);
        //int index = binarySearch(arr, 0, arr.length, 1);
        System.out.println("index = " + index);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int findVal){
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){
            return insertValueSearch(arr, mid + 1, right, findVal);
        }else if(findVal < midVal){
            return insertValueSearch(arr, left, mid - 1, findVal);
        }else{
            return mid;
        }
    }
}
