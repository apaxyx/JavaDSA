package DataStructures.tree;

import java.io.*;
import java.util.*;

//霍夫曼编码
public class HuffmanCode {
    public static void main(String[] args) {
        /*String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); //40

        byte[] huffmanCodesBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是:" + Arrays.toString(huffmanCodesBytes) + " 长度= " + huffmanCodesBytes.length);

        //测试一把byteToBitString方法
        //System.out.println(byteToBitString((byte)1));
        byte[] sourceBytes = decode(huffmanCodes, huffmanCodesBytes);

        System.out.println("原来的字符串=" + new String(sourceBytes));*/ // "i like like like java do you like a java"


        //测试压缩文件
	/*	String srcFile = "d://pic.png";
		String dstFile = "d://demo.zip";

		zipFile(srcFile, dstFile);
		System.out.println("压缩文件ok~~");
*/
        //测试解压文件
        String zipFile2 = "d://demo.zip";
        String dstFile2 = "d://test2.jpg";
        unZipFile(zipFile2, dstFile2);
        System.out.println("解压成功!");
    }

    //解压文件
    public static void unZipFile(String zipFile, String dstFile){
        InputStream is = null;
        ObjectInputStream ois = null;
        OutputStream os = null;
        try{
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffmanBytes = (byte[])ois.readObject();
            Map<Byte, String> huffmanCodes = (Map<Byte, String>)ois.readObject();

            byte[] bytes = decode(huffmanCodes, huffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        }catch(Exception e){


        }finally{
            try{
                os.close();
                ois.close();
                is.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    //压缩文件
    public static void zipFile(String srcFile, String dstFile){
        OutputStream os = null;
        ObjectOutputStream oos = null;
        FileInputStream is = null;
        try{
            is = new FileInputStream(srcFile);
            //avaiable()方法返回可以不受阻塞地从此输入流中读取（或跳过）的估计剩余字节数。
            byte[] b = new byte[is.available()];

            is.read(b);
            byte[] huffmanBytes = huffmanZip(b);
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                is.close();
                oos.close();
                os.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //byte数组转成二进制的字符串
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            boolean flag = (i == huffmanBytes.length - 1);
            sb.append(byteToBitString(!flag, b));
        }

        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询
        Map<String, Byte> map = new HashMap<>();
        for(Map.Entry<Byte, String> entry : huffmanCodes.entrySet()){
            map.put(entry.getValue(), entry.getKey());
        }

        //创建集合，存放byte
        List<Byte> list = new ArrayList<>();
        for(int i = 0; i < sb.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while(flag){
                String key = sb.substring(i, i + count);//移动count找匹配
                b = map.get(key);
                if(b == null){//说明没有匹配到
                    count++;
                }else{
                    flag = false;//匹配到
                }
            }
            list.add(b);
            i += count;//i直接移动到count
        }

        //当for循环结束后，我们list中就存放了所有的字符
        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length; i++){
            b[i] = list.get(i);
        }
        return b;
    }

    //将一个byte转成一个二进制的字符串
    private static String byteToBitString(boolean flag, byte b) {
        int temp = b;
        if (flag) {
            //正数需要补高位
            temp |= 256;//按位或 256 1 0000 0000 | 0000 0001 = 1 0000 0001
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    private static byte[] huffmanZip(byte[] bytes) {
        //根据字符所形成的字节数组生成Node链表
        List<CodeNode> nodes = getNodes(bytes);

        //根据链表生成赫夫曼树
        CodeNode huffmanTreeRoot = createHuffmanTree(nodes);

        //根据赫夫曼树生成赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //根据郝夫曼编码表生成对应压缩数据的字节数组并返回
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            //根据霍夫曼编码表生成对应数据的霍夫曼编码串
            sb.append(huffmanCodes.get(b));
        }

        //int len = (sb.length() + 8) / 8 一句解决计算长度问题
        //每8位对应一个字符的二进制编码
        //不足8位另加一位
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        //用一个byte数组接收压缩数据
        byte[] huffmanCodeBytes = new byte[len];

        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            String strByte;
            if (i + 8 > sb.length()) {
                strByte = sb.substring(i);
            } else {
                strByte = sb.substring(i, i + 8);
            }
            //将strByte转成一个byte,放入到huffmanCodeBytes中，以二进制的方式
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }
        return huffmanCodeBytes;
    }

    static Map<Byte, String> huffmanCodes = new HashMap<>();
    static StringBuilder sb = new StringBuilder();

    //重载getCodes()方法，方便调用
    private static Map<Byte, String> getCodes(CodeNode root) {
        if (root == null) {
            return null;
        }
        getCodes(root.left, "0", sb);
        getCodes(root.right, "1", sb);
        return huffmanCodes;
    }

    //根据赫夫曼树的叶子结点路径产生哈夫曼编码，存到map中
    private static void getCodes(CodeNode node, String code, StringBuilder sb) {
        StringBuilder sb2 = new StringBuilder(sb);
        sb2.append(code);
        if (node != null) {
            if (node.data == null) {
                getCodes(node.left, "0", sb2);
                getCodes(node.right, "1", sb2);
            } else {
                huffmanCodes.put(node.data, sb2.toString());
            }
        }
    }


    //前序遍历
    private static void preOrder(CodeNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("赫夫曼树为空");
        }
    }

    //统计字符串中每个字符出现的次数，并用CodeNode存下来放进链表中
    private static List<CodeNode> getNodes(byte[] bytes) {
        ArrayList<CodeNode> nodes = new ArrayList<CodeNode>();
        HashMap<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if (count == null) {
                counts.put(b, 1);
            } else {
                counts.put(b, count + 1);
            }
        }

        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new CodeNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //用List创建对应的HuffmanTree
    private static CodeNode createHuffmanTree(List<CodeNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            CodeNode leftNode = nodes.get(0);
            CodeNode rightNode = nodes.get(1);

            CodeNode parent = new CodeNode(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }
}
