package DataStructures.avl;
//<Key extends Comparable<Key>, Value>
public class RBNode {
    Integer key;
    Integer val;

    RBNode left;
    RBNode right;
    boolean color;

    int size;

    public RBNode(int key, int val, boolean color, int size) {
        this.key = key;
        this.val = val;
        this.color = color;
        this.size = size;
    }

    //中序遍历，即从小到大遍历输出
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    @Override
    public String toString() {
        return "RBNode{" +
                "key=" + key +
                ", val=" + val +
                ", color=" + color +
                ", size=" + size +
                '}';
    }
}
