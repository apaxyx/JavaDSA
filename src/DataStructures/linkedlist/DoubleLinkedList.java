package DataStructures.linkedlist;

public class DoubleLinkedList {
    private DoubleLinkedHeroNode head = new DoubleLinkedHeroNode(0, "", "");

    public DoubleLinkedHeroNode getHead(){
        return head;
    }

    public void list(){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        DoubleLinkedHeroNode temp = head.next;
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    public void add(DoubleLinkedHeroNode heroNode){
        DoubleLinkedHeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void update(DoubleLinkedHeroNode newHeroNode){
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }

        DoubleLinkedHeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == newHeroNode.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }else{
            System.out.printf("没有找到编号%d的节点， 不能修改\n",newHeroNode.no);
        }
    }

    public void del(int no){
        if(head.next == null){
            System.out.println("链表为空，无法删除");
            return;
        }

        DoubleLinkedHeroNode temp = head.next;
        boolean flag = false;
        while(true){
            if(temp == null){
                break;
            }
            if(temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if(flag){
            temp.pre.next = temp.next;
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }
}
