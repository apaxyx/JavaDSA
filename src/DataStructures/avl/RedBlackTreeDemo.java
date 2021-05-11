package DataStructures.avl;

import java.text.SimpleDateFormat;
import java.util.*;

/*
红黑树

---------------------------------------------------------------

《数据结构与算法分析C语言描述》

红黑树是具有下列着色性质的二叉查找树：
1.每一个节点或者着成红色，或者着成黑色。
2.根是黑色的。
3.如果一个节点是红色的，那么它的子节点必须是黑色的。
4.从一个节点到一个null指针的每一条路径必须包含相同数目的黑色节点。

---------------------------------------------------------------

《算法导论》

一棵红黑树是满足下面红黑性质的二叉搜索树
1)每个结点或是红色的，或是黑色的
2)根结点是黑色的
3)每个叶结点(NIL)是黑色的
4)如果一个结点是红色的，则它的两个子结点都是黑色的
5)对每个结点，从该结点到其所有后代叶结点的简单路径上，均包含相同数目的黑色结点

---------------------------------------------------------------

《算法Java语言描述第四版》

红黑二叉查找树背后的基本思想是用标准的二叉查找树（完全由2-结点构成）
和一些额外的信息（替换3-结点）来表示2-3树
我们将树中的链接分为两种类型：红链接将两个2-结点连接起来构成一个3-结点
，黑链接则是2-3树中的普通链接。确切地说，我们将3-结点表示为由一条左斜的
红色链接（两个2-结点其中之一是另一个的左子结点）相连的两个2-结点

红黑树的另一种定义是含有红黑链接并满足下列条件的二叉查找树：
1)红链接均为左链接
2)没有任何一个结点同时和两条红链接相连
3)该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑链接数量相同
满足这样定义的红黑树和相应的2-3树是一一对应的

一一对应
如果我们将一棵红黑树中的红链接画平，那么所有的空链接到根结点的距离都
将是机同的。
如果我们将由红链接相连的结点合并，得到的是一棵2-3树。
相反，如果将一棵2-3树中的3-结点画作由红色左链接相连的的两个2-结点，那
么不会存在能够和两条红链接相连的结点，且树必然是完美黑色平衡的，因为黑
链接即2-3树中的普通链接，根据定义这些链接必然是完美平衡的。

颜色表示
方便起见，因为每个结点都只会有一条指向自己的链接（从它的父结点指向它）
，我们将链接的颜色保存在表示结点的Node数据类型的布尔变量color中，如果
指向它的链接是红色的，那么该变量为true，黑色则为false。我们约定空链接
为黑色。
为了代码的清晰我们定义了两个常量RED和BLACK来设置和测试这个变量
我们使用私有方法isRed()来测试一个结点和它的父结点之间的链接的颜色
当我们提到一个结点的颜色时，我们反映的是指向该结点的链接的颜色，反之亦然

颜色转换
我们专门用一个方法flipColors()来转换一个结点的两个红色子结点的颜色，除了
将子结点的颜色由红变黑之外，我们同时还需要将父结点的颜色由黑变红。

在沿着插入点到根结点的路径向上移动时在所经过的每个结点中顺序完成以下操作，
我们就完成插入操作：
1)如果右子结点是红色的而左子结点是黑色的，进行左旋转
2)如果左子结点是红色的且它的左子结点也是红色的，进行右旋转
3)如果左右子结点均为红色，进行颜色转换



 */
public class RedBlackTreeDemo {

    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }

        Collections.shuffle(list);
       // System.out.println(list);

        Date date1 = new Date();
        SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = s1.format(date1);

        for (Integer i : list) {
            rbt.put(i,i);
        }

        /*for(int i = 10; i > 0; i--){
            rbt.put(i, i);
        }*/

        Date date2 = new Date();
        String date2Str = s1.format(date2);

        //rbt.infixOrder();

        System.out.println("\n插入前的时间——" + date1Str);
        System.out.println("插入后的时间——" + date2Str);

        TreeSet<Object> objects = new TreeSet<>();
        TreeMap<Object, Object> objectObjectTreeMap = new TreeMap<>();
        HashMap hashMap = new HashMap();
        LinkedHashMap<Object, Object> lhm = new LinkedHashMap<>();

    }

}



















