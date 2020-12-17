//A class to implement Binary search tree without using Java data structures
//Values should only be stored in the tree once.


import java.util.HashMap;
import java.util.Map;

public class SearchTree {

    //rootnode is the starting element of the tree structure
    //size stores the size of the binary search tree
    //isAdded is used to store the boolean value whether element is added into the tree

    private Node rootNode;
    private int size;
    private boolean isAdded;
    private int ZERO = 0;
    private String EXCEPTIONMSG = "System has caught unexpected exception.";
    private String EMPTYMSG = "Binary search tree is either null or empty.";
    private String empty = "";
    private String addOrFind = empty;
    private String add = "add";

    /*
    SearchTree constructor
    to initialize the value
     */
    public SearchTree(){
        rootNode = null;
        size = ZERO;
    }

    /*
    printMethod method
    gets the string to be printed as input
    prints the inputted string
     */
    private void printMethod(String printString){
        System.out.println(printString);
    }

    /*
    add method
    receives the key from user as parameter
    return boolean value as output whether the element is added or not
     */
    public boolean add(String key) {
        addOrFind = add;
        isAdded = false;
        try {
            //basic string validation on input
            if (key != null && !key.isEmpty()) {
                int find = find(key);
                if(find < 1){
                    rootNode = addNode(rootNode, key.toLowerCase(), ZERO);  //call the addNode to add the key
                } else{
                    printMethod("Key is already available in the binary search tree. Please provide a valid key to add.");
                }

            } else {
                //prints the message if key is invalid
                printMethod("Key is either null or empty. Please provide valid key to add.");
            }
        }
        catch (Exception e){
            printMethod(EXCEPTIONMSG);      //catch all exceptions and prints a message in screen
        }
        addOrFind = empty;
        return isAdded;
    }

    /*
    addNode method
    receives the rootNode, key and depth as parameter
    returns the rootNode after adding the new element
     */
    private Node addNode(Node currentNode, String key, int depth){

        //to add the element into the tree
        if (currentNode == null) {
            currentNode = new Node(key);
            isAdded = true;
            size = size + 1;        //size is incremented after each node addition
            depth = depth + 1;      //depth is incremented after each node addition
            currentNode.setDepth(depth);       //depth is set while adding the node
            return currentNode;
        }
        //to add elements into tree with rootNode as base
        else {

            depth = depth + 1;
            //comparing the key with currentNode key to store in left or right side
            int compare = currentNode.getValue().compareTo(key);
            if (compare > 0) {
                //key value is less then add to left side
                currentNode.setLeft(addNode(currentNode.getLeft(), key, depth));
            } else {
                //key value is more then add to right side
                currentNode.setRight(addNode(currentNode.getRight(), key, depth));
            }

            return currentNode;
        }
    }

    /*
     find method
     gets a key as input
     returns 0 if the key is not found in tree
     or depth of the key if the key is found
     */
    int find (String key) {
        int depth = ZERO;
        try {
            //condition to check the key is valid
            if (key != null && !key.isEmpty()) {
                Node currentNode = rootNode;
                Node previousNode = null;
                key = key.toLowerCase();

                //if the tree is empty
                if (currentNode == null && !addOrFind.equalsIgnoreCase(add)) {
                    printMethod(EMPTYMSG);
                    return depth;
                } else {
                    //loop the tree to find the node
                    while (currentNode != null && currentNode.getValue() != null
                            && !currentNode.getValue().equalsIgnoreCase(key)) {
                        previousNode = currentNode;
                        int compare = key.compareTo(currentNode.getValue());
                        if (compare < 0) {
                            //if the key is less than current node value then go left side
                            currentNode = currentNode.getLeft();
                        } else {
                            //if the key is more than current node value then go right side
                            currentNode = currentNode.getRight();
                        }
                    }
                }

                //if the loop exited with currentNode as null or value is null
                if (currentNode == null || currentNode.getValue() == null) {
                    depth = ZERO;
                } else {
                    //return value is set
                    depth = currentNode.getDepth();

                    if(!addOrFind.equalsIgnoreCase(add)) {
                        //search counter is incremented
                        currentNode.setSearchCounter(currentNode.getSearchCounter() + 1);
                        if (previousNode != null) {
                            if (previousNode.getSearchCounter() < currentNode.getSearchCounter()) {
                                //if search counter of child is more than parent then rearrange
                                rearrange(previousNode, currentNode);
                            }
                        }
                    }
                }
            }
        }
        catch (Exception e){
            //catch all exceptions and show an error message to user
            printMethod(EXCEPTIONMSG);
        }
        return depth;
    }

    /*
    rearrange method
    gets the previous node and current node as input
    rearranges the tree
     */
    private void rearrange(Node previousNode, Node currentNode){
        //if the root node is to be shifted
        if(previousNode.getParent() != null){
            currentNode.setParent(previousNode.getParent());
        }else{
            rootNode = currentNode;
            currentNode.setParent(null);
        }

        int comparePreviousCurrent = previousNode.getValue().compareTo(currentNode.getValue());
        if(comparePreviousCurrent > 0){
            //compare the previous node value and child node value to check shift is left or right
            if(previousNode.getParent() != null){
                int compareParentCurrent = previousNode.getParent().getValue().compareTo(currentNode.getValue());
                //compare the parent of previous node and current node values
                if(compareParentCurrent > 0) {
                    previousNode.getParent().setLeft(currentNode);
                }else {
                    previousNode.getParent().setRight(currentNode);
                }
            }
            previousNode.setParent(currentNode);
            //child of current node to be moved to previous node
            if(currentNode.getRight() != null) {
                previousNode.setLeft(currentNode.getRight());
                currentNode.getRight().setParent(previousNode);
            }
            else {
                previousNode.setLeft(null);
            }
            currentNode.setRight(previousNode);
        }
        else{
            //compare the previous node value and child node value to check shift is left or right
            if(previousNode.getParent() != null){
                int compareParentCurrent = previousNode.getParent().getValue().compareTo(currentNode.getValue());
                //compare the parent of previous node and current node values
                if(compareParentCurrent > 0) {
                    previousNode.getParent().setLeft(currentNode);
                }else {
                    previousNode.getParent().setRight(currentNode);
                }
            }
            previousNode.setParent(currentNode);
            //child of current node to be moved to previous node
            if(currentNode.getLeft() != null) {
                previousNode.setRight(currentNode.getLeft());
                currentNode.getLeft().setParent(previousNode);
            }
            else {
                previousNode.setRight(null);
            }
            currentNode.setLeft(previousNode);
        }
        depthCalculator(rootNode, ZERO);      //after rearranging calculating the depth of all nodes
    }

    /*
    calcDepth method
    gets the current node and depth as inputs
    sets the depth using recursive calls on all nodes
     */
    private void depthCalculator(Node currentNode, int depth){
        if(currentNode == null){
            return;
        }
        depth = depth + 1;      //depth is calculated
        currentNode.setDepth(depth);    //depth is set to current node
        depthCalculator(currentNode.getLeft(), depth);    //to set depth of left side nodes of root node
        depthCalculator(currentNode.getRight(), depth);   //to set depth of right side nodes of root node
        return;
    }

    /*
    reset method
    will reset the search counters stored in the map
     */
    public void reset(){
        try {
            if(rootNode != null) {
                Node currentNode = rootNode;
                resetAll(currentNode);
            }else{
                printMethod(EMPTYMSG);      //if tree is empty then throws an error message
            }
        }catch (Exception e){
            printMethod(EXCEPTIONMSG);  // catch all exception and print an error message
        }
    }

    /*
    resetAll method
    gets the current node as input
    resets the search counter of all node by recursive calls
     */
    private void resetAll(Node currentNode){
        if(currentNode == null){
            return;
        }
        currentNode.setSearchCounter(ZERO);     //search counter is resetted
        resetAll(currentNode.getLeft());        //to reset left side nodes of root node
        resetAll(currentNode.getRight());       //to reset right side nodes of root node
        return;
    }

    /*
    printTree method
    returns the tree elements in a string form
     */
    public String printTree(){
        String returnString = null;
        try {
            if(rootNode != null) {
                Node currentNode = rootNode;
                returnString = formString(currentNode);
            }else {
                printMethod(EMPTYMSG);      //error message to print if tree is empty
            }
        }catch (Exception e){
            printMethod(EXCEPTIONMSG);      //catch all exceptions and prints an error message
        }
        return returnString;
    }

    /*
    formString method
    gets the currentNode and depth as input parameters
    returns the string with values of each node and its depth
     */
    private String formString(Node currentNode){
        String space = " ";
        String newLine = "\n";
        String returnString = "";

        if(currentNode == null){
            return "";
        }

        //form the string to return for current call
        returnString = currentNode.getValue() + space + currentNode.getDepth() + newLine;

        //recursive call to add all nodes in left side
        //return string is added at last to print the lowest value at first
        returnString = formString(currentNode.getLeft()) + returnString;

        //recursive call to add all nodes in right side
        //return string is added at first to print the highest value at last
        returnString = returnString + formString(currentNode.getRight());
        return returnString;
    }


}
