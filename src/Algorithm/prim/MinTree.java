package Algorithm.prim;

import java.util.Arrays;

//创建最小生成树->村庄的图
class MinTree {
    //创建图的邻接矩阵
    /*
    graph   图对象
    verxs   图对应的顶点个数
    data    图的各个顶点的值
    weight  图的邻接矩阵
    */
    public void createGraph(MGraph graph, int verxs, char data[], int[][] weight) {
        int i, j;
        for (i = 0; i < verxs; i++){//顶点
            graph.data[i] = data[i];
            for (j = 0; j < verxs; j++) {
                graph.weight[i][j] = weight[i][j];

            }
        }
    }

    //显示图的邻接矩阵
    public void showGraph(MGraph graph){
        for (int[] link : graph.weight) {
            System.out.println(Arrays.toString(link));
        }
    }

    //编写prim算法，得到最小生成树
    /*
    graph 图
    v 表示从图的第几个顶点开始生成'A' -> 0 'B' -> 1...
     */
    public void prim(MGraph graph, int v){
        //visited[] 标记结点（顶点）是否被访问过
        int visited[] = new int[graph.verxs];
        //visited[]默认元素的值都是0，表示没有访问过
        /*
        for(int i = 0; i < graph.verxs; i++){
            visited[i] = 0;
        }
        */

        //把当前这个结点标记为已访问
        visited[v] = 1;
        //h1 和 h2记录两个顶点的下标
        int h1 = -1;
        int h2 = -1;
        //将minWeight初始成一个大数，后面在遍历过程中，会被替换
        int minWeight = 10000;
        for (int k = 1; k < graph.verxs; k++) {//因为有graph.verxs个顶点，普利姆算法结束后，有graph.verxs - 1 条边
            //这个是确定每一次生成的子图，和哪个结点的距离最近
            for (int i = 0; i < graph.verxs; i++) {
                for (int j = 0; j < graph.verxs; j++) {
                    if(visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight){
                        //替换minWeight（寻找已经访问过的结点和未访问过的结点间的权值最小的边）
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            //找到一条边是最小
            System.out.println("边<" + graph.data[h1] + "," + graph.data[h2] + ">权值：" + minWeight);
            //将当前这个结点标记为已经访问
            visited[h2] = 1;
            //minWeight 重新设置为最大值10000
            minWeight = 10000;
        }
    }
}
