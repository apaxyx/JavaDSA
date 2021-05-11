package Algorithm.dijkstra;
/*
迪杰斯特拉算法

应用场景-最短路径问题
1.战争时期，胜利乡有7个村庄（A,B,C,D,E,F,G）,现在有六个邮差，从
G点出发，需要分别把邮件分别送到A,B,C,D,E,F六个村庄
2.各个村庄的距离用边线表示（权），比如A-B距离5公里
3.问：如何计算出G村庄到其它各个村庄的最短距离？
4.如果从其它点出发到各个点的最短距离又是多少？

迪杰斯特拉算法介绍
迪杰斯特拉（Dijkstra）算法是典型最短路径算法，用于计算一个结点到
其它结点的最短路径，它的主要特点是以起始点为中心向外层层扩展（广度
优先搜索思想），直到扩展到终点为止。

迪杰斯特拉算法过程
设置出发顶点v，顶点集合V{v1,v2,vi...}，v到V中各顶点的距离构成距离
集合Dis，Dis{d1,d2,di...}，Dis集合记录着v到图中各顶点的距离（到自
身可以看作0，v到vi距离对应为di）
1.从Dis中选择值最小的di并移出Dis集合，同时移出V集合中对应的顶点vi，
此时的v到vi即为最短路径
2.更新Dis集合，更新规则为：比较v到V集合中顶点的距离值，与v通过vi到V
集合中顶点的距离值，保留值较小的一个（同时也应该更新顶点的前驱结点为vi，
表明是通过vi到达的）
3.重复执行两步骤，直到最短路径顶点为目标顶点即可结束
 */
public class DijkstraAlgorithm {

    public static void main(String[] args) {
        char[] vertex = {'A','B','C','D','E','F','G'};
        //邻接矩阵
        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;//表示不可以连接
        matrix[0]=new int[]{N,5,7,N,N,N,2};
        matrix[1]=new int[]{5,N,N,9,N,N,3};
        matrix[2]=new int[]{7,N,N,N,8,N,N};
        matrix[3]=new int[]{N,9,N,N,N,4,N};
        matrix[4]=new int[]{N,N,8,N,N,5,4};
        matrix[5]=new int[]{N,N,N,4,5,N,6};
        matrix[6]=new int[]{2,3,N,N,4,6,N};
        //创建Graph对象
        Graph graph = new Graph(vertex, matrix);
        //测试，看看图的邻接矩阵是否ok
        graph.showGraph();
        //测试迪杰斯特拉算法
        graph.dsj(6);//G
        graph.showDijkstra();
        graph.dsj(2);//C
        graph.showDijkstra();
    }
}
