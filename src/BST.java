public class BinaryTree {
    Node root;

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.root = new Node(5);
        bt.root.insert(0);
        bt.root.insert(15);
        bt.root.insert(-1);
        bt.root.insert(12);
        bt.root.insert(10);
        bt.root.insert(4);
        bt.root.insert(9);
        bt.root.insert(90);
        bt.root.insert(7);

        //deleting a node
        bt.delete(bt.root, 4);
        bt.print(bt.root);
        System.out.println();

        //inserting
        bt.root.insert(-5);

        //deleting another node
        bt.delete(bt.root, 7);
        bt.print(bt.root);
        System.out.println();

        //inserting more new nodes
        bt.root.insert(80);
        bt.root.insert(6);
        bt.print(bt.root);
        System.out.println();

        //deleting another non-existent node
        bt.delete(bt.root, 101);
        bt.print(bt.root);
        System.out.println();

        //inserting another node
        bt.root.insert(-10);

        //deleting the root
        bt.delete(bt.root, bt.root.val);

        //print final bst
        bt.print(bt.root);
        System.out.println();
    }

    public void print(Node root){
        if(root == null) return;
        print(root.left);
        System.out.print(root.val + " ");
        print(root.right);
    }

    public void delete(Node node, int val){
        if(node == null){
            System.err.println("Could not delete");
        }
        else if(this.root.val == val){
            Node swap;
            //if root has a right branch
            if(this.root.right != null){
                //and no left branch
                if(this.root.left == null) this.root = root.right; //simply redirect
                else{
                    //search for the next successor in the left branch of the right branch
                    Node curr = this.root.right;
                    if(curr.left != null){ //if right branch has a left branch, commence search
                        while(curr.left.left != null){
                            curr = curr.left;
                        }
                        this.root.val = curr.left.val; //change values
                        curr.left = null; //delete
                    }
                    else{
                        //simply redirect the nodes nad swap the left branch
                        swap = this.root.left;
                        this.root = this.root.right;
                        this.root.left = swap;
                    }
                }
            }
            else{
                //if no children
                if(this.root.left == null) this.root = null;
                //if only left, simply redirect
                else this.root = this.root.left;
            }
        }
        //check for right branch
        else if(val > node.val){
            //if this does not have a valid node
            if(node.right == null){
                System.err.println("Could not delete");
            }
            //if found
            else if(node.right.val == val){
                //basic redirection if right node does not has a left/right branch
                if(node.right.left == null) node.right = node.right.right;
                else if(node.right.right == null) node.right = node.right.left;
                //make the next successor's value the right node's value and delete the successor
                else{
                    Node curr = node.right.right;
                    if(curr.left != null){ //if it has a left branch, commence successor search
                        while(curr.left.left != null){
                            curr = curr.left;
                        }
                        node.right.val = curr.left.val;
                        curr.left = null;
                    }
                    //else, simply redirect the node.
                    else node.right = node.right.right;
                }
            }
            //recur on the right branch
            else delete(node.right, val);
        }
        //check in left branch
        //and do the same operations as above.
        else if(val < node.val){
            //if this does not have a valid node
            if(node.left == null){
                System.err.println("Could not delete");
            }
            else if(node.left.val == val){
                if(node.left.left == null) node.left = node.left.right;
                else if(node.left.right == null) node.left = node.left.left;
                else{
                    Node curr = node.left.right;
                    if(curr.left != null){
                        while(curr.left.left != null){
                            curr = curr.left;
                        }
                        node.left.val = curr.left.val;
                        curr.left = null;
                    }
                    else node.left = node.left.right;
                }
            }
            else delete(node.left, val);
        }
    }
}
