package DataStructures.avl;

//<Key extends Comparable<Key>, Value>
public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private RBNode root;

    public RedBlackTree() {
    }

    //判断结点颜色，真为红色，假为黑色
    private boolean isRed(RBNode node) {
        if (node == null) {
            return false;
        }
        return node.color == RED;
    }

    //node结点为根的子树中结点的个数，0代表node为空
    private int size(RBNode node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    //返回此红黑树结点的个数
    private int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    //
    public Integer get(Integer key) {
        if (key == null) {
            System.out.println("你要找的键名为空");
            return null;
        }
        return get(root, key);
    }

    //
    private Integer get(RBNode node, Integer key) {
        while (node != null) {
            int cmp = key.compareTo(node.key);
            if (cmp < 0) {
                node = node.left;
            } else if (cmp > 0) {
                node = node.right;
            } else {
                return node.val;
            }
        }
        System.out.println("红黑树中没有你要的键值结点");
        return -1;//只在树中添加非负数
    }

    //查询树中是否有这个键
    public boolean contains(int key) {
        return get(key) != null;
    }

    //插入
    public void put(Integer key, Integer val) {
        //如果键为空不插入，直接返回
        if (key == null) {
            System.out.println("键为空，插入失败");
            return;
        }

        //如果值为空，就删除那个键，并且返回
        if (val == null) {
            //delete(key);
            return;
        }

        //真正的插入
        root = put(root, key, val);
        //有可能根变成了红色，根据定义要把根设为黑色？？？
        root.color = BLACK;
    }

    //insert
    public RBNode put(RBNode node, Integer key, Integer val) {
        //第一次插入时都是红结点，且size为1
        if (node == null) {
            return new RBNode(key, val, RED, 1);
        }

        int cmp = key.compareTo(node.key);

        if (cmp < 0) {
            node.left = put(node.left, key, val);
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            //不支持重复的键的结点，如果重复就相当于更新这个同名键结点里面的值
            node.val = val;
        }

        //接下来因为新加结点的原因需要判断是否旋转子树以达到红黑树的平衡和定义要求

        //1)如果右子结点是红色的而左子结点是黑色的，进行左旋转
        if (isRed(node.right) && !isRed(node.left)){
            node = rotateLeft(node);
        }

        //2)如果左子结点是红色的且它的左子结点也是红色的，进行右旋转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }

        //3)如果左右子结点均为红色，进行颜色转换
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        //计算子树根结点的size
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    //删除树中最小键的结点
    public void deleteMin() {
        if(isEmpty()){
            System.out.println("树为空，无法操作");
            return;
        }

        //如果根和根结点的左右孩子都是黑色的，把根染红？？？
        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = deleteMin(root);

        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public RBNode deleteMin(RBNode node) {
        if(node.left == null){
            return null;
        }
        if(!isRed(node.left) && !isRed(node.left.left)){
            node = moveRedLeft(node);
        }
        node.left = deleteMin(node.left);
        return balance(node);
    }

    //删除树中最大键的结点
    public void deleteMax() {
        if(isEmpty()){
            System.out.println("树为空，无法操作");
        }

        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = deleteMax(root);

        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public RBNode deleteMax(RBNode node) {
        if(isRed(node.left)){
            node = rotateRight(node);
        }

        if(node.right == null){
            return null;
        }

        if(!isRed(node.right) && !isRed(node.right.left)){
            node = moveRedRight(node);
        }

        node.right = deleteMax(node.right);

        return balance(node);
    }

    //删除结点
    public void delete(Integer key) {
        if(key == null){
            System.out.println("要删阶的键为空，无法操作");
            return;
        }

        if(!isRed(root.left) && !isRed(root.right)){
            root.color = RED;
        }

        root = delete(root, key);

        if(!isEmpty()){
            root.color = BLACK;
        }
    }

    public RBNode delete(RBNode node, Integer key) {
        if(key.compareTo(node.key) < 0){
            if(!isRed(node.left) && !isRed(node.left.left)){
                node = moveRedLeft(node);
            }
            node.left = delete(node.left, key);
        } else{
            if(isRed(node.left)){
                node = rotateRight(node);
            }
            if(key.compareTo(node.key) == 0 && (node.right == null)){
                return null;
            }
            if(!isRed(node.right) && !isRed(node.right.left)){
                node = moveRedRight(node);
            }
            if(key.compareTo(node.key) == 0){
                RBNode tempNode = min(node.right);
                node.key = tempNode.key;
                node.val = tempNode.val;
                node.right = deleteMin(node.right);
            }else{
                node.right = delete(node.right, key);
            }
        }
        return balance(node);
    }

    //使一个左倾的链接向右倾斜
    private RBNode rotateRight(RBNode node) {
        RBNode tempNode = node.left;
        node.left = tempNode.right;
        tempNode.right = node;
        tempNode.color = tempNode.right.color;
        tempNode.right.color = RED;
        tempNode.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return tempNode;
    }

    //使一个右倾的链接向左倾斜
    private RBNode rotateLeft(RBNode node) {
        RBNode tempNode = node.right;
        node.right = tempNode.left;
        tempNode.left = node;
        tempNode.color = tempNode.left.color;
        tempNode.left.color = RED;
        tempNode.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return tempNode;
    }

    //翻转节点及其两个子节点的颜色
    private void flipColors(RBNode node) {
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    //假设node是红色的node的左孩子和node左孩子的左孩子是黑色的，
    //使node的左孩子和node的左孩子的孩子都是红色的
    private RBNode moveRedLeft(RBNode node) {
        flipColors(node);
        if(isRed(node.right.left)){
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            flipColors(node);
        }
        return node;
    }

    //假设node是红色的node的右孩子和node右孩子的左孩子是黑色的，
    //使node的右孩子和node的右孩子的孩子都是红色的
    private RBNode moveRedRight(RBNode node) {
        flipColors(node);
        if(isRed(node.left.left)){
            node = rotateRight(node);
            flipColors(node);
        }
        return node;
    }

    //恢复红黑树不变，平衡
    private RBNode balance(RBNode node) {
        if(isRed(node.right)){
            node = rotateLeft(node);
        }
        if(isRed(node.left) && isRed(node.left.left)){
            node = rotateRight(node);
        }
        if(isRed(node.left) && isRed(node.right)){
            flipColors(node);
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    //求高度，空为-1，叶子高度为0，只有一个结点高度也为0
    public int height() {
        return height(root);
    }

    private int height(RBNode node) {
        if (node == null) {
            return -1;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }


    //求最小值
    public Integer min() {
        if (isEmpty()) {
            System.out.println("树为空，没有结点，也没有键和值");
            return null;
        }
        return min(root).key;
    }

    //最小值是树中最左边的叶子结点
    public RBNode min(RBNode node) {
        if (node.left == null) {
            return node;
        } else {
            return min(node.left);
        }
    }

    //求最大值
    public Integer max() {
        if (isEmpty()) {
            System.out.println("树为空，没有结点，也没有键和值");
            return null;
        }
        return max(root).key;
    }

    //最大值为树中最右边的叶子结点
    public RBNode max(RBNode node) {
        if (node.right == null) {
            return node;
        } else {
            return max(node.left);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("红黑树为空，不能遍历");
        }
    }
/*
    //返回树中小于等于key的最大键
    public Integer floor(Integer key) {
        if (key == null) {
            System.out.println("无法为null值做寻找");
            return null;
        }

        if (isEmpty()) {
            System.out.println("树为空，无法操作");
            return null;
        }

        RBNode node = floor(root, key);

        if (node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private RBNode floor(RBNode node, Integer key){
        if(node == null){
            return null;
        }

        int cmp = key.compareTo(node.key);

        if(cmp == 0){
            return node;
        }

        if(cmp < 0){
            return floor(node.left, key);
        }

        RBNode targetNode = floor(node.right, key);

        //如果为空，说明要找的键比树中最大的键还大，就返回最右边的叶子结点即可
        if(targetNode != null){
            return targetNode;
        }else{
            return node;
        }
    }


    //返回树中大于小于key的最小键
    public Integer ceiling(Integer key){
        if(key == null){
            System.out.println("无法为null值做寻找");
            return null;
        }

        if(isEmpty()){
            System.out.println("树为空，无法操作");
            return null;
        }

        RBNode node = ceiling(root, key);

        if (node == null) {
            return null;
        } else {
            return node.key;
        }
    }

    private RBNode ceiling(RBNode node, Integer key){
        if(node == null){
            return null;
        }

        int cmp = key.compareTo(node.key);

        if(cmp == 0){
            return node;
        }
        if(cmp > 0){
            return ceiling(node.right, key);
        }

        RBNode targetNode = ceiling(node.left, key);

        //如果为空，说明要找的键比树中最大的键还大，就返回最右边的叶子结点即可
        if(targetNode != null){
            return targetNode;
        }else{
            return node;
        }
    }
*/

/*
    //找到树中排序为第k个的键
    public Integer select(int k){}

    //找到树中排序为第k个的结点
    private Node select(Node x, int k){}

    //找到树中键比key小的结点的个数
    public int rank(Integer key){}

    public int rank(Integer key, RBNode node){}

    //返回以树中所以结点正序排列的集合
    public Iterable<Integer> keys(){}

    public Iterable<Integer> keys(Integer lo, Integer hi){}

    private void keys(RBNode node, Collection<Integer> coll, Integer lo, Integer hi){}

    //返回在给定范围内的树的结点的个数
    public int size(Integer lo, Integer hi){}

    //检查红黑树数据结构的完整性
    private boolean check(){}

    //是否对称
    private boolean isBST(){}

    //以x为根的树的所有结点的键是否在min和max之间
    private boolean isBST(RBNode node, Integer min, Integer max){}

    //大小字段是否正确
    private boolean isSizeConsistent(){}

    private boolean isSizeConsistent(RBNode node){}

    //排序是否一致
    private boolean isRankConsistent(){}

    //树是否没有右侧红结点，即没有右边的红链接，并且至多有一条左侧的？？
    private boolean is23(){}

    private boolean is23(RBNode node){}

    //所有从根到叶的路径都有相同数量的黑边吗
    private boolean isBalanced(){}

    //从根结点到叶结点的每条路径都有给定数量的黑链吗？
    private boolean isBalanced(RBNode node, int black){}
*/
}
























