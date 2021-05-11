package Algorithm.kruskal;

import java.util.Arrays;

/*
克鲁斯卡尔算法

应用场景-公交站问题
1.某城市新增7个站点（A,B,C,D,E,F,G）,现在需要修路把7个站点连通
2.各个站点的距离用边线表示（权），比如A-B距离12公里
3.问：如何修路保证各个站点都能连通，并且总的修建公路总里程最短？

克鲁斯卡尔算法介绍
1.克鲁斯卡尔算法（Kruskal），用来求加权连通图的最小生成树的算法
2.基本思想：按照权值从小到大的顺序选择n-1条边，并保证这n-1条边不构成回路
3.具体做法：首先构成一个只含n个顶点的森林，然后依权值从小到大从连通网中
选择边加入到森林中，并使森林中不产生回路，直到森林变成一棵树为止

克鲁斯卡尔算法分析
克鲁斯卡尔算法需要解决以下两个问题
问题一：对图的所有边按照权值大小进行排序
问题二：将边添加到最小生成树中时，怎么样判断是否形成了回路

问题一很好解决，采用排序算法进行排序即可
问题二，处理方式是：记录顶点在“最小生成树”中的终点，顶点的终点是“在最小生成树
中与它连通的最大顶点”。然后每次需要将一条边添加到最小生成树时，判断该边的两个
顶点的终点是否重合，重合的话则会构成回路
 */
public class KruskalCase {
    private int edgeNum;//边的个数
    private char[] vertexs;//顶点数组
    private int[][] matrix;//邻接矩阵

    //使用INF表示两个顶点不能连通
    //public static final int INF = Integer.MAX_VALUE;
    public static final int INF = 999;


    public KruskalCase(char[] vertexs, int[][] matrix) {
        //初始化顶点数和边的个数
        int vlen = vertexs.length;

        //初始化顶点，复制拷贝的方式
        this.vertexs = new char[vlen];
        for (int i = 0; i < vertexs.length; i++) {
            this.vertexs[i] = vertexs[i];
        }

        //初始化边，使用的是复制拷贝的方式
        this.matrix = new int[vlen][vlen];
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        //统计边的条数
        for (int i = 0; i < vlen; i++) {
            for (int j = 0; j < vlen; j++) {
                //注意这里统计的边方向问题，同一条边统计了两次，所以有24条边
                //不统计自己并且达不到的也不统计，一条边统计两个方向，所以有24条边
                //也可以让j = i + 1，这样统计的就是所有边，不重复的，也就是有12条边
                //而且不用判断 matrix[i][j] != 0
                if (this.matrix[i][j] != INF && this.matrix[i][j] != 0) {
                    edgeNum++;
                }
            }
        }
        //edgeNum /= 2;
    }

    //打印邻接矩阵
    public void print() {
        System.out.println("邻接矩阵为：\n");
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();
        }
    }

    //对边进行排序处理，冒泡排序
    private void sortEdges(EData[] edges) {//edges 边的集合
        for (int i = 0; i < edges.length - 1; i++) {
            for (int j = 0; j < edges.length - 1 - i; j++) {
                if (edges[j].weight > edges[j + 1].weight) {//交换
                    EData tmp = edges[j];
                    edges[j] = edges[j + 1];
                    edges[j + 1] = tmp;
                }
            }
        }
    }

    //返回ch顶点对应的下标，如果找不到，返回-1
    //ch 顶点的值
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {//找到
                return i;
            }
        }
        //找不到，返回-1
        return -1;
    }

    /*
    功能：获取图中边，放到EData[]数组中，后面我们需要遍历该数组
    是能过matrix邻接矩阵来获取
    EData[]形式[['A','B',12],['B','F',7],......]
    */
    private EData[] getEdges() {
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = 0; j < vertexs.length; j++) {
                if (matrix[i][j] != INF && matrix[i][j] != 0) {
                    //不统计自己并且达不到的也不统计，一条边统计两个方向，所以有24条边
                    //也可以让j = i + 1，这样统计的就是所有边，不重复的，也就是有12条边
                    //而且不用判断 matrix[i][j] != 0
                    edges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /*
    功能：获取下标为i的顶点的终点()，用于后面判断两个顶点的终点是否相同
    ends 数组就是记录了各个顶点的终点是哪个，ends数组是在遍历过程中，逐步形成
    i 表示传入的顶点对应的下标
    return 返回的就是下标为 i 的这个顶点对应的终点的下标
    */
    private int getEnd(int[] ends, int i) {// i = 4 [0,0,0,0,5,0,0,0,0,0,0,0]
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }

    public void kruskal() {
        int index = 0;//表示最后结果数组的索引
        int[] ends = new int[edgeNum];//用于保存“已有最小生成树”中的每个顶点在最小生成树中的终点
        //创建结果数组，保存最后的最小生成树
        EData[] rets = new EData[edgeNum];

        //获取图中 所有边的集合， 一共有12条边
        EData[] edges = getEdges();
        System.out.println("图的边的集合=" + Arrays.toString(edges) + "\n共" + edges.length);//12、24

        //按照边的权值大小进行排序（从小到大）
        sortEdges(edges);

        /*
        遍历edges数组，将边添加到最小生成树时，判断是准备加入的边是否形成了回路
        如果没有形成回路，就加入rets,否则就不能加入
         */
        for(int i = 0; i < edgeNum; i++){
            //获取到第i条边的第一个顶点（起点）
            int p1 = getPosition(edges[i].start);//p1 = 4
            //获取到第i条边的第二个顶点
            int p2 = getPosition(edges[i].end);//p2 = 5
            //获取p1这个顶点在已有最小生成树中的终点
            int m = getEnd(ends, p1);// m = 4
            //获取p2这个顶点在已有最小生成树的终点
            int n = getEnd(ends, p2);// n = 5
            //是否构成回路
            if(m != n){//没有构成回路
                ends[m] = n;//设置m在“已有最小生成树”中的终点，<E,F> [0,0,0,0,5,0,0,0,0,0,0,0]
                rets[index++] = edges[i];//有一条边加入到rets数组
            }
        }
        //<E,F> <C,D> <D,E> <B,F> <E,G> <A,B>
        //统计并打印“最小生成树”，输出rets
        System.out.println("最小生成树为");
        for (int i = 0; i < index; i++) {
            System.out.println(rets[i]);
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        //克鲁斯卡尔算法的邻接矩阵
        int matrix[][] ={
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*/
        /*A*/ {   0,  12, INF, INF, INF,  16,  14},
        /*B*/ {  12,   0,  10, INF, INF,   7, INF},
        /*C*/ { INF,  10,   0,   3,   5,   6, INF},
        /*D*/ { INF, INF,   3,   0,   4, INF, INF},
        /*E*/ { INF, INF,   5,   4,   0,   2,   8},
        /*F*/ {  16,   7,   6, INF,   2,   0,   9},
        /*G*/ {  14, INF, INF, INF,   8,   9,   0}};

        //大家可以去测试其它的邻接矩阵，结果都可以得到最小生成树
        //创建KruskalCase对象实例
        KruskalCase kc = new KruskalCase(vertexs, matrix);
        //System.out.println(kc.edgeNum);

        //输出构建的
        kc.print();
        kc.kruskal();

    }
}
