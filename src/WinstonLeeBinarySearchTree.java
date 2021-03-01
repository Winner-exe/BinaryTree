import java.util.*;

public class WinstonLeeBinarySearchTree<E extends Comparable<E>> implements Iterable<E>
{
    protected Node head;
    public static final int INORDER_MODE = 0;
    public static final int PREORDER_MODE = 1;
    public static final int POSTORDER_MODE = 2;

    protected Node search(E data)
    {
        if (head == null)
            return null;
        else if (head.obj.compareTo(data) == 0)
            return head;
        else
        {
            WinstonLeeBinarySearchTree<E> cursor = this;
            if (cursor.head.obj.compareTo(data) > 0)
                return cursor.head.left.search(data);
            else
                return cursor.head.right.search(data);
        }
    }

    public void add(E data)
    {
        if (head == null)
            head = new Node(data);
        else if (search(data) == null)
        {
            WinstonLeeBinarySearchTree<E> cursor = this;
            if (cursor.head.obj.compareTo(data) > 0)
                cursor.head.left.add(data);
            else
                cursor.head.right.add(data);
        }
    }

    public Iterator<E> transverse()
    {
        LinkedList<E> elements = new LinkedList<>();
        inorderTransverse().forEachRemaining(node -> elements.add(node.obj));
        return elements.iterator();
    }

    public Iterator<E> iterator()
    {
        return transverse();
    }

    public Iterator<E> transverse(int mode)
    {
        LinkedList<E> elements = new LinkedList<>();
        switch (mode) {
            case INORDER_MODE -> inorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            case PREORDER_MODE -> preorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            case POSTORDER_MODE -> postorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            default -> throw new IllegalArgumentException();
        }
        return elements.iterator();
    }

    protected Iterator<Node> inorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<>();
        if (head.left.head != null && head.left.head.obj != null)
            head.left.inorderTransverse().forEachRemaining(nodes::add);
        nodes.add(head);
        if (head.right.head != null && head.right.head.obj != null)
            head.right.inorderTransverse().forEachRemaining(nodes::add);
        return nodes.iterator();
    }

    protected Iterator<Node> preorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.add(head);
        if (head.left.head != null && head.left.head.obj != null)
            head.left.preorderTransverse().forEachRemaining(nodes::add);
        if (head.right.head != null && head.right.head.obj != null)
            head.right.preorderTransverse().forEachRemaining(nodes::add);
        return nodes.iterator();
    }

    protected Iterator<Node> postorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<>();
        if (head.left.head != null && head.left.head.obj != null)
            head.left.postorderTransverse().forEachRemaining(nodes::add);
        if (head.right.head != null && head.right.head.obj != null)
            head.right.postorderTransverse().forEachRemaining(nodes::add);
        nodes.add(head);
        return nodes.iterator();
    }

    public void remove(E data)
    {
        Node cursor = search(data);
        if (cursor != null)
        {
            switch(cursor.deg())
            {
                case 0:
                    cursor.left = null;
                    cursor.right = null;
                    cursor.obj = null;
                    break;
                case 1:
                    if (cursor.left.head != null && cursor.left.head.obj != null)
                    {
                        cursor.obj = cursor.left.head.obj;
                        cursor.right = cursor.left.head.right;
                        cursor.left = cursor.left.head.left;
                    }
                    else
                    {
                        cursor.obj = cursor.right.head.obj;
                        cursor.left = cursor.right.head.left;
                        cursor.right = cursor.right.head.right;
                    }
                    break;
                case 2:
                    E successor = cursor.right.transverse().next();
                    cursor.obj = successor;
                    cursor.right.remove(successor);
                    break;
                default:
                    break;
            }
        }
    }

    protected class Node
    {
        public E obj;
        public WinstonLeeBinarySearchTree<E> left;
        public WinstonLeeBinarySearchTree<E> right;

        public Node(E value)
        {
            obj = value;
            left = new WinstonLeeBinarySearchTree<>();
            right = new WinstonLeeBinarySearchTree<>();
        }

        public int deg()
        {
            int degree = 0;
            if (left.head != null)
                degree++;
            if (right.head != null)
                degree++;
            return degree;
        }
    }
}
