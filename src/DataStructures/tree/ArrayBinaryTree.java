package DataStructures.tree;

public class ArrayBinaryTree {
    private int[] arr;
    public ArrayBinaryTree(int[] arr){
        this.arr = arr;
    }
    //前序
    public void preOrder(){
        this.preOrder(0);
    }
    /*
    一个节点的编号（从0开始）为n,
    则它的左子节点为2 * n + 1,
    右子节点为2 * n + 2
     */
    public void preOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        //输出当前这个元素
        System.out.println(arr[index]);

        //左递归
        if((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        //右递归
        if((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

    //中序
    public void infixOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的中序遍历");
        }

        //左递归
        if((index * 2 + 1) < arr.length){
            infixOrder(2 * index + 1);
        }

        //输出当前这个元素
        System.out.println(arr[index]);

        //右递归
        if((index * 2 + 2) < arr.length){
            infixOrder(2 * index + 2);
        }
    }

    //后序
    public void postOrder(int index){
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的后序遍历");
        }

        //左递归
        if((index * 2 + 1) < arr.length){
            postOrder(2 * index + 1);
        }

        //右递归
        if((index * 2 + 2) < arr.length){
            postOrder(2 * index + 2);
        }

        //输出当前这个元素
        System.out.println(arr[index]);
    }
}
