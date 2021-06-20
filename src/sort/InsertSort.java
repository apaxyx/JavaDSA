package sort;
//插入排序
/*
  第一个元素为有序，其余元素为无序
  开始比较时记录下来需要判断是否需要执行插入操作的无序表中的元素
  拿无序表中的第一个元素（即从第二个元素开始）和有序表比较（从有序表最后一个元素开始），当无序表的比有序表的
元素大时不作操作，若小，则将大的那个有序表中的元素放在无序表中的这个元素的位置，然后再将无序表中的这个元素值
和有序表倒数第二个元素进行比较，若仍然小，则将有序表中倒数第二个元素放在有序表倒数第一的位置，依次比较，直到
比较到有序表第一个元素或者找到一个元素比无序表中的元素大，则把这个元素位置放上无序表中的这个元素（此时，该位
置上的原元素已经移动到它的下一位，其后的有序表中的元素也都在之前的过程中后移了一位）
*/

public class InsertSort {
    public static void sort(int[] arr) {
        int insertVal = 0;//从第二个元素开始被判断是否需要执行插入操作的元素
        int insertIndex = 0;//待插入位置的索引
        for (int i = 1; i < arr.length; i++) {//从第二个开始到数组最后一个元素
            insertVal = arr[i];
            insertIndex = i - 1;//总是和该元素之前的一个元素开始比较

            //保证在数组索引范围内的同时，当右比左小时再进入循环
            //注意是已经保存的insertVal,即arr[i]的值，insertIndex + 1 = i,虽然arr[i]被下一行代码改变
            //但是已经提前存下来，之后也是拿这个值进行判断
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];//满足条件，把左值放在右值的位置上
                insertIndex--;//进行有序表中前一位元素的判断
            }

            //当没有进入上在的while循环说明右值比有序表中最大的元素还大，此时insertIndex + 1 = i，
            //即不需要进入插入操作
            //该条件语句判断insertIndex + 1 != i，说明右值比左值某一位小，即进行插入，就是把需要操作的
            //值放到insertIndex + 1那个位置上
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }
    }

    public static void sort_c(int[] arr) {
        int temp;
        int j;


        for (int i = 1; i < arr.length; i++) {
            temp = arr[i];
            for (j = i; j > 0 && temp < arr[j - 1]; j--){
                arr[j] = arr[j - 1];
            }
            if(j != i){
                arr[j] = temp;
            }
        }
    }
}
