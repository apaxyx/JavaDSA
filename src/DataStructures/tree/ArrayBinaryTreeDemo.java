package DataStructures.tree;

public class ArrayBinaryTreeDemo {

    //用数组描述一个满二叉树
    /*
    从第一层开始依次从左至右填充，然后用前/中/后序遍历
     */
    public static void main(String[] args) {
        //int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[] arr = {1, 3, 6, 8, 10, 14};

        ArrayBinaryTree abt = new ArrayBinaryTree(arr);
       /* abt.preOrder();
        System.out.println("-----------");*/

        abt.infixOrder(0);
        System.out.println("-----------");

        abt.postOrder(0);
    }
}
