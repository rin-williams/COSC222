// code start from https://www.geeksforgeeks.org/deletion-in-red-black-tree/?ref=lbp
// under license: https://creativecommons.org/licenses/by-sa/4.0/
// which states that "You are free to: Share — copy and redistribute the material in any medium or format
// Java Code for the above approach

import java.util.LinkedList;
import java.util.Queue;

enum COLOR {
    RED, BLACK
}

// modified to accept strings instead of ints.
class Node {
    String val;
    COLOR color;
    Node left, right, parent;

    Node(String val) {
        this.val = val;
        parent = left = right = null;

        // Node is created during insertion
        // Node is red at insertion
        color = COLOR.RED;
    }

    // returns pointer to uncle
    Node uncle() {

        // If no parent or grandparent, then no uncle
        if (parent == null || parent.parent == null)
            return null;

        if (parent.isOnLeft())
            // uncle on right
            return parent.parent.right;
        else
            // uncle on left
            return parent.parent.left;
    }

    // check if node is left child of parent
    boolean isOnLeft() {
        return this == parent.left;
    }

    // returns pointer to sibling
    Node sibling() {

        // sibling null if no parent
        if (parent == null)
            return null;

        if (isOnLeft())
            return parent.right;

        return parent.left;
    }

    // moves node down and moves given node in its place
    void moveDown(Node nParent) {
        if (parent != null) {
            if (isOnLeft())
                parent.left = nParent;
            else
                parent.right = nParent;
        }
        nParent.parent = parent;
        parent = nParent;
    }

    boolean hasRedChild() {
        return (left != null && left.color == COLOR.RED) ||
                (right != null && right.color == COLOR.RED);
    }
}

class RBTree {
    Node root;

    // left rotates the given node
    void leftRotate(Node x) {
        // new parent will be node's right child
        Node nParent = x.right;

        // update root if current node is root
        if (x == root)
            root = nParent;

        x.moveDown(nParent);

        // connect x with new parent's left element
        x.right = nParent.left;

        // connect new parent's left element with node
        // if it is not null
        if (nParent.left != null)
            nParent.left.parent = x;

        // connect new parent with x
        nParent.left = x;
    }

    void rightRotate(Node x) {

        // new parent will be node's left child
        Node nParent = x.left;

        // update root if current node is root
        if (x == root)
            root = nParent;

        x.moveDown(nParent);

        // connect x with new parent's right element
        x.left = nParent.right;

        // connect new parent's right element with node
        // if it is not null
        if (nParent.right != null)
            nParent.right.parent = x;

        // connect new parent with x
        nParent.right = x;
    }

    void swapColors(Node x1, Node x2) {
        COLOR temp = x1.color;
        x1.color = x2.color;
        x2.color = temp;
    }

    void swapValues(Node u, Node v) {
        String temp = u.val;
        u.val = v.val;
        v.val = temp;
    }

    // fix red red at given node
    void fixRedRed(Node x) {

        // if x is root color it black and return
        if (x == root) {
            x.color = COLOR.BLACK;
            return;
        }

        // initialize parent, grandparent, uncle
        Node parent = x.parent, grandparent = parent.parent, uncle = x.uncle();

        if (parent.color != COLOR.BLACK) {
            if (uncle != null && uncle.color == COLOR.RED) {

                // uncle red, perform recoloring and recurse
                parent.color = COLOR.BLACK;
                uncle.color = COLOR.BLACK;
                grandparent.color = COLOR.RED;
                fixRedRed(grandparent);
            } else {
                // Else perform LR, LL, RL, RR
                if (parent.isOnLeft()) {
                    if (x.isOnLeft())
                        // for left right
                        swapColors(parent, grandparent);
                    else {
                        leftRotate(parent);
                        swapColors(x, grandparent);
                    }
                    // for left left and left right
                    rightRotate(grandparent);
                } else {
                    if (x.isOnLeft()) {
                        // for right left
                        rightRotate(parent);
                        swapColors(x, grandparent);
                    } else
                        swapColors(parent, grandparent);

                    // for right right and right left
                    leftRotate(grandparent);
                }
            }
        }
    }

    // find node that do not have a left child
    // in the subtree of the given node
    Node successor(Node x) {
        Node temp = x;
        while (temp.left != null)
            temp = temp.left;
        return temp;
    }

    // find node that replaces a deleted node in BST
    Node BSTreplace(Node x) {
        // when node have 2 children
        if (x.left != null && x.right != null)
            return successor(x.right);

        // when leaf
        if (x.left == null && x.right == null)
            return null;

        // when single child
        if (x.left != null)
            return x.left;
        else
            return x.right;
    }

    void fixDoubleBlack(Node x) {
        // Reached root
        if (x == root)
            return;

        Node sibling = x.sibling(), parent = x.parent;

        if (sibling == null)
            // No sibling, double black pushed up
            fixDoubleBlack(parent);
        else {
            if (sibling.color == COLOR.RED) {
                // sibling red
                parent.color = COLOR.RED;
                sibling.color = COLOR.BLACK;

                if (sibling.isOnLeft())
                    // right case
                    rightRotate(parent);
                else
                    // right case
                    leftRotate(parent);

                fixDoubleBlack(x);
            } else {
                // Sibling black
                if (sibling.hasRedChild()) {
                    // at least 1 red children
                    if (sibling.left != null && sibling.left.color == COLOR.RED) {
                        if (sibling.isOnLeft()) {
                            // left left
                            sibling.left.color = sibling.color;
                            sibling.color = parent.color;
                            rightRotate(parent);
                        } else {
                            // right right
                            sibling.left.color = parent.color;
                            rightRotate(sibling);
                            leftRotate(parent);
                        }
                    } else {
                        if (sibling.isOnLeft()) {
                            // left right
                            sibling.right.color = parent.color;
                            leftRotate(sibling);
                            rightRotate(parent);
                        } else {
                            // right right
                            sibling.right.color = sibling.color;
                            sibling.color = parent.color;
                            leftRotate(parent);
                        }
                    }
                    parent.color = COLOR.BLACK;
                } else {
                    // 2 black children
                    sibling.color = COLOR.RED;
                    if (parent.color == COLOR.BLACK)
                        fixDoubleBlack(parent);
                    else
                        parent.color = COLOR.BLACK;
                }
            }
        }
    }

    // prints level order for given node
    void levelOrder(Node x) {
        if (x == null)
            return;

        // queue for level order
        Queue<Node> q = new LinkedList<>();
        Node curr;

        q.add(x);

        while (!q.isEmpty()) {
            curr = q.poll();
            // print node value
            System.out.print(curr.val + " ");

            // push children to queue
            if (curr.left != null)
                q.add(curr.left);
            if (curr.right != null)
                q.add(curr.right);
        }
    }

    // prints inorder recursively
    void inorder(Node x) {
        if (x == null)
            return;

        inorder(x.left);
        System.out.print(x.val + " ");
        inorder(x.right);
    }

    // constructor
    // initialize root
    RBTree() {
        root = null;
    }

    Node getRoot() {
        return root;
    }

    // searches for given value
    // if found returns the node (used for delete)
    // else returns the last node while traversing (used in insert)
    Node search(String n) {
        Node temp = root;
        while (temp != null) {
            if (temp.val.compareTo(n) > 0) {
                if (temp.left == null)
                    break;
                else
                    temp = temp.left;

            } else if (temp.val.equals(n)) {
                break;
            } else {
                if (temp.right == null)
                    break;
                else
                    temp = temp.right;
            }
        }

        return temp;
    }

    // modified/added for usage in Comparison.java
    boolean searchFor(String n) {
        return search(n).val.equals(n);

    }

    // inserts the given value to tree
    void insert(String n) {
        Node newNode = new Node(n);
        if (root == null) {
            // when root is null
            // simply insert value at root
            newNode.color = COLOR.BLACK;
            root = newNode;
        } else {
            Node temp = search(n);

            // return if value already exists
            if (temp.val == n)
                return;

            // if value is not found, search returns the node
            // where the value is to be inserted

            // connect new node to correct node
            newNode.parent = temp;

            if (temp.val.compareTo(n) > 0)
                temp.left = newNode;
            else
                temp.right = newNode;

            // fix red red violation if exists
            fixRedRed(newNode);
        }
    }

    // deleted a lot of functions related to delete because they were not needed for
    // this lab
}

// This code is contributed by Abhinav Mahajan (abhinav_m22).
// code end ----------------------------------------------