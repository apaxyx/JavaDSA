package DataStructures.linkedlist;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        DoubleLinkedHeroNode hero1 = new DoubleLinkedHeroNode(1, "宋江", "及时雨");
        DoubleLinkedHeroNode hero2 = new DoubleLinkedHeroNode(2, "卢俊义", "玉麒麟");
        DoubleLinkedHeroNode hero3 = new DoubleLinkedHeroNode(3, "吴用", "智多星");
        DoubleLinkedHeroNode hero4 = new DoubleLinkedHeroNode(4, "林冲", "豹子头");
        // 创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.list();

        // 修改
        DoubleLinkedHeroNode newHeroNode = new DoubleLinkedHeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.list();
    }
}
