import java.util.Iterator;

public final class WinstonLeeBSTDriver
{
    public static void main(String[] args)
    {
        WinstonLeeBinarySearchTree<Integer> tree = new WinstonLeeBinarySearchTree<>();
        tree.add(4);
        tree.add(2);
        tree.add(6);
        tree.add(0);
        tree.add(1);
        tree.add(3);
        tree.add(5);
        tree.add(7);
        tree.add(8);

        System.out.println("INORDER:");
        Iterator<Integer> transversal = tree.transverse(WinstonLeeBinarySearchTree.INORDER_MODE);
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");

        System.out.println("PREORDER:");
        transversal = tree.transverse(WinstonLeeBinarySearchTree.PREORDER_MODE);
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");

        System.out.println("POSTORDER:");
        transversal = tree.transverse(WinstonLeeBinarySearchTree.POSTORDER_MODE);
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");

        tree.remove(1);
        System.out.println("INORDER (REMOVED 1):");
        transversal = tree.transverse();
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");

        tree.remove(7);
        System.out.println("INORDER (REMOVED 7):");
        transversal = tree.transverse();
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");


        tree.remove(2);
        System.out.println("INORDER (REMOVED 2):");
        transversal = tree.transverse();
        while(transversal.hasNext())
        {
            System.out.println(transversal.next());
        }
        System.out.println("-----------------------------------------------------------");
    }
}
