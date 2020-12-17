//Node class is used to represent one node/index/element of binary search tree.

public class Node {

    //variables to store the value of Node object(each node of binary search tree)
    private Node left;      //to store left side node
    private Node right;     //to store right side node
    private String value;       //to store value of the node
    private Node parent;        //to store parent node
    private int searchCounter = 0;      //to store search counter
    private int depth = 0;          //to store depth of the node

    //Constructor to initialize all values to null
    public Node(){    }

    //Constructor to initialize an object with value
    public Node(String value){
        this.value = value;
    }

    /*
    getValue method
    returns the value of the Node object
     */
    public String getValue(){
        return this.value;
    }

    /*
    setLeft method
    gets the left node of this object
    set the input as left node and set the parent as this
     */
    public void setLeft(Node left){
        this.left = left;
        if(left != null) {
            this.left.parent = this;
        }
    }

    /*
    getLeft method
    returns the left node of this object
     */
    public Node getLeft(){
        return this.left;
    }

    /*
    setRight method
    gets the right node of this object
    set the input as right node and set the parent as this
    */
    public void setRight(Node right){
        this.right = right;
        if(right != null) {
            this.right.parent = this;
        }
    }

    /*
    getRight method
    returns the right node of this object
     */
    public Node getRight(){
        return this.right;
    }

    /*
    getSearchCounter method
    returns the search counter of this object
     */
    public int getSearchCounter(){
        return this.searchCounter;
    }

    /*
    setSearchCounter method
    gets the search counter as input
    sets the input as search counter of this object
    */
    public void setSearchCounter(int searchCounter){
        this.searchCounter = searchCounter;
    }

    /*
    getParent method
    returns the parent node of this object
     */
    public Node getParent(){
        return this.parent;
    }

    /*
    setParent method
    gets the parent node
    sets the input as parent of this object
     */
    public void setParent(Node parent){
        this.parent = parent;
    }

    /*
    setDepth method
    gets the depth as input
    sets the input as depth of this object
     */
    public void setDepth (int depth){
        this.depth = depth;
    }

    /*
    getDepth method
    returns the depth of this object
     */
    public int getDepth(){
        return this.depth;
    }

}
