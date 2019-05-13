public class Node {
    int val;
    Node left;
    Node right;

    public Node(int val){
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public void insert(int val){
        if(val > this.val){
            if(this.right != null) this.right.insert(val);
            else this.right = new Node(val);
        }
        else if(val < this.val){
            if(this.left != null) this.left.insert(val);
            else this.left = new Node(val);
        }
        else{
            System.err.println("Could not add!");
        }
    }
}
