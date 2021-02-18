import java.util.*;

public class BinarySearchTree<E extends Comparable<E>>
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
            BinarySearchTree<E> cursor = this;
            if (cursor.head.obj.compareTo(data) > 0)
                return cursor.head.right.search(data);
            else
                return cursor.head.left.search(data);
        }
    }

    public void add(E data)
    {
        if (head == null)
            head = new Node(data);
        else if (search(data) == null)
        {
            BinarySearchTree<E> cursor = this;
            if (cursor.head.obj.compareTo(data) > 0)
                cursor.head.right.add(data);
            else
                cursor.head.left.add(data);
        }
    }

    public Iterator<E> transverse()
    {
        LinkedList<E> elements = new LinkedList<E>();
        inorderTransverse().forEachRemaining(node -> elements.add(node.obj));
        return elements.iterator();
    }

    public Iterator<E> transverse(int mode)
    {
        LinkedList<E> elements = new LinkedList<E>();
        switch (mode)
        {
            case INORDER_MODE:
                inorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            case PREORDER_MODE:
                preorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            case POSTORDER_MODE:
                postorderTransverse().forEachRemaining(node -> elements.add(node.obj));
            default:
                break;
        }
        return elements.iterator();
    }

    protected Iterator<Node> inorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<Node>();
        if (head.left.head != null)
            head.left.inorderTransverse().forEachRemaining(nodes::add);
        nodes.add(head);
        if (head.right.head != null)
            head.right.inorderTransverse().forEachRemaining(nodes::add);
        return nodes.iterator();
    }

    protected Iterator<Node> preorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.add(head);
        if (head.left.head != null)
            head.left.preorderTransverse().forEachRemaining(nodes::add);
        if (head.right.head != null)
            head.right.preorderTransverse().forEachRemaining(nodes::add);
        return nodes.iterator();
    }

    protected Iterator<Node> postorderTransverse()
    {
        LinkedList<Node> nodes = new LinkedList<Node>();
        if (head.left.head != null)
            head.left.postorderTransverse().forEachRemaining(nodes::add);
        if (head.right.head != null)
            head.right.postorderTransverse().forEachRemaining(nodes::add);
        nodes.add(head);
        return nodes.iterator();
    }

    protected class Node
    {
        public E obj;
        public BinarySearchTree<E> left;
        public BinarySearchTree<E> right;

        public Node(E value)
        {
            obj = value;
            left = new BinarySearchTree<E>();
            right = new BinarySearchTree<E>();
        }
    }
}
