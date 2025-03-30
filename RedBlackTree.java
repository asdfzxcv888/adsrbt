// package mypack;
// public class RedBlackTree {

//     private static final int RED = 0;
//     private static final int BLACK = 1;

//     private class Node {
//         int data, color;
//         Node left, right, parent;

//         Node(int data) {
//             this.data = data;
//             color = RED;
//             left = TNULL;
//             right = TNULL;
//             parent = null;
//         }
//     }

//     private final Node TNULL;
//     private Node root;

//     // Constructor to initialize TNULL and root
//     public RedBlackTree() {
//         TNULL = new Node(0);
//         TNULL.color = BLACK;
//         root = TNULL;
//     }

//     // Left rotate
//     private void leftRotate(Node x) {
//         Node y = x.right;
//         x.right = y.left;

//         if (y.left != TNULL) {
//             y.left.parent = x;
//         }

//         y.parent = x.parent;

//         if (x.parent == null) {
//             root = y;
//         } else if (x == x.parent.left) {
//             x.parent.left = y;
//         } else {
//             x.parent.right = y;
//         }

//         y.left = x;
//         x.parent = y;
//     }

//     // Right rotate
//     private void rightRotate(Node x) {
//         Node y = x.left;
//         x.left = y.right;

//         if (y.right != TNULL) {
//             y.right.parent = x;
//         }

//         y.parent = x.parent;

//         if (x.parent == null) {
//             root = y;
//         } else if (x == x.parent.right) {
//             x.parent.right = y;
//         } else {
//             x.parent.left = y;
//         }

//         y.right = x;
//         x.parent = y;
//     }

//     // Fix violations after insertion
//     private void fixInsert(Node k) {
//         Node u;
//         while (k.parent.color == RED) {
//             if (k.parent == k.parent.parent.right) {
//                 u = k.parent.parent.left;
//                 if (u.color == RED) {
//                     // Case 1: Uncle is red
//                     u.color = BLACK;
//                     k.parent.color = BLACK;
//                     k.parent.parent.color = RED;
//                     k = k.parent.parent;
//                 } else {
//                     // Case 2: Node is left child
//                     if (k == k.parent.left) {
//                         k = k.parent;
//                         rightRotate(k);
//                     }
//                     // Case 3: Node is right child
//                     k.parent.color = BLACK;
//                     k.parent.parent.color = RED;
//                     leftRotate(k.parent.parent);
//                 }
//             } else {
//                 u = k.parent.parent.right;
//                 if (u.color == RED) {
//                     // Case 1: Uncle is red
//                     u.color = BLACK;
//                     k.parent.color = BLACK;
//                     k.parent.parent.color = RED;
//                     k = k.parent.parent;
//                 } else {
//                     // Case 2: Node is right child
//                     if (k == k.parent.right) {
//                         k = k.parent;
//                         leftRotate(k);
//                     }
//                     // Case 3: Node is left child
//                     k.parent.color = BLACK;
//                     k.parent.parent.color = RED;
//                     rightRotate(k.parent.parent);
//                 }
//             }
//             if (k == root) {
//                 break;
//             }
//         }
//         root.color = BLACK;
//     }

//     // Insert into the tree
//     public void insert(int key) {
//         Node node = new Node(key);
//         Node y = null;
//         Node x = root;

//         while (x != TNULL) {
//             y = x;
//             if (node.data < x.data) {
//                 x = x.left;
//             } else {
//                 x = x.right;
//             }
//         }

//         node.parent = y;
//         if (y == null) {
//             root = node;
//         } else if (node.data < y.data) {
//             y.left = node;
//         } else {
//             y.right = node;
//         }

//         node.left = TNULL;
//         node.right = TNULL;

//         if (node.parent == null) {
//             node.color = BLACK;
//             return;
//         }

//         if (node.parent.parent == null) {
//             return;
//         }

//         fixInsert(node);
//     }

//     // Search the tree
//     public Node searchTree(int key) {
//         return searchTreeHelper(this.root, key);
//     }

//     private Node searchTreeHelper(Node node, int key) {
//         if (node == TNULL || key == node.data) {
//             return node;
//         }
//         if (key < node.data) {
//             return searchTreeHelper(node.left, key);
//         }
//         return searchTreeHelper(node.right, key);
//     }

//     // Minimum node
//     private Node minimum(Node node) {
//         while (node.left != TNULL) {
//             node = node.left;
//         }
//         return node;
//     }

//     // Fix delete violations
//     private void fixDelete(Node x) {
//         Node s;
//         while (x != root && x.color == BLACK) {
//             if (x == x.parent.left) {
//                 s = x.parent.right;
//                 if (s.color == RED) {
//                     s.color = BLACK;
//                     x.parent.color = RED;
//                     leftRotate(x.parent);
//                     s = x.parent.right;
//                 }

//                 if (s.left.color == BLACK && s.right.color == BLACK) {
//                     s.color = RED;
//                     x = x.parent;
//                 } else {
//                     if (s.right.color == BLACK) {
//                         s.left.color = BLACK;
//                         s.color = RED;
//                         rightRotate(s);
//                         s = x.parent.right;
//                     }

//                     s.color = x.parent.color;
//                     x.parent.color = BLACK;
//                     s.right.color = BLACK;
//                     leftRotate(x.parent);
//                     x = root;
//                 }
//             } else {
//                 s = x.parent.left;
//                 if (s.color == RED) {
//                     s.color = BLACK;
//                     x.parent.color = RED;
//                     rightRotate(x.parent);
//                     s = x.parent.left;
//                 }

//                 if (s.right.color == BLACK && s.left.color == BLACK) {
//                     s.color = RED;
//                     x = x.parent;
//                 } else {
//                     if (s.left.color == BLACK) {
//                         s.right.color = BLACK;
//                         s.color = RED;
//                         leftRotate(s);
//                         s = x.parent.left;
//                     }

//                     s.color = x.parent.color;
//                     x.parent.color = BLACK;
//                     s.left.color = BLACK;
//                     rightRotate(x.parent);
//                     x = root;
//                 }
//             }
//         }
//         x.color = BLACK;
//     }

//     // Delete node from tree
//     public void delete(int key) {
//         deleteNodeHelper(this.root, key);
//     }

//     private void deleteNodeHelper(Node node, int key) {
//         Node z = TNULL;
//         Node x, y;

//         while (node != TNULL) {
//             if (node.data == key) {
//                 z = node;
//             }

//             if (node.data <= key) {
//                 node = node.right;
//             } else {
//                 node = node.left;
//             }
//         }

//         if (z == TNULL) {
//             System.out.println("Couldn't find key in the tree.");
//             return;
//         }

//         y = z;
//         int yOriginalColor = y.color;

//         if (z.left == TNULL) {
//             x = z.right;
//             transplant(z, z.right);
//         } else if (z.right == TNULL) {
//             x = z.left;
//             transplant(z, z.left);
//         } else {
//             y = minimum(z.right);
//             yOriginalColor = y.color;
//             x = y.right;

//             if (y.parent == z) {
//                 x.parent = y;
//             } else {
//                 transplant(y, y.right);
//                 y.right = z.right;
//                 y.right.parent = y;
//             }

//             transplant(z, y);
//             y.left = z.left;
//             y.left.parent = y;
//             y.color = z.color;
//         }

//         if (yOriginalColor == BLACK) {
//             fixDelete(x);
//         }
//     }

//     // Replace node u with v
//     private void transplant(Node u, Node v) {
//         if (u.parent == null) {
//             root = v;
//         } else if (u == u.parent.left) {
//             u.parent.left = v;
//         } else {
//             u.parent.right = v;
//         }

//         v.parent = u.parent;
//     }

//     // In-order traversal
//     public void inOrder() {
//         inOrderHelper(this.root);
//     }

//     private void inOrderHelper(Node node) {
//         if (node != TNULL) {
//             inOrderHelper(node.left);
//             System.out.print(node.data + " ");
//             inOrderHelper(node.right);
//         }
//     }

//     // Pre-order traversal
//     public void preOrder() {
//         preOrderHelper(this.root);
//     }

//     private void preOrderHelper(Node node) {
//         if (node != TNULL) {
//             System.out.print(node.data + " ");
//             preOrderHelper(node.left);
//             preOrderHelper(node.right);
//         }
//     }

//     // Post-order traversal
//     public void postOrder() {
//         postOrderHelper(this.root);
//     }

//     private void postOrderHelper(Node node) {
//         if (node != TNULL) {
//             postOrderHelper(node.left);
//             postOrderHelper(node.right);
//             System.out.print(node.data + " ");
//         }
//     }

//     // Get root
//     public Node getRoot() {
//         return this.root;
//     }
// }






import java.util.*;

class Node {
    String key;
    Node left, right, parent;
    boolean color; // true for RED, false for BLACK

    Node(String key) {
        this.key = key;
        left = right = parent = null;
        color = true; // New nodes are RED
    }
}

public class RedBlackTree {
    private Node root;
    private Node TNULL;

    public RedBlackTree() {
        TNULL = new Node("");
        TNULL.color = false;
        root = TNULL;
    }

    // Insertion
    public boolean insert(String key) {
        if (search(key)) return false; // Duplicate

        Node node = new Node(key);
        node.left = TNULL;
        node.right = TNULL;

        Node parent = null;
        Node current = root;
        
        while (current != TNULL) {
            parent = current;
            if (key.compareTo(current.key) < 0)
                current = current.left;
            else
                current = current.right;
        }
        
        node.parent = parent;
        if (parent == null)
            root = node;
        else if (key.compareTo(parent.key) < 0)
            parent.left = node;
        else
            parent.right = node;

        fixInsert(node);
        return true;
    }

    // Search
    public boolean search(String key) {
        Node result = searchTree(root, key);
        return result != TNULL;
    }

    private Node searchTree(Node node, String key) {
        if (node == TNULL || key.equals(node.key))
            return node;
        
        if (key.compareTo(node.key) < 0)
            return searchTree(node.left, key);
        
        return searchTree(node.right, key);
    }

    // Delete
    public boolean delete(String key) {
        if (!search(key)) return false;
        deleteNode(root, key);
        return true;
    }

    private void deleteNode(Node node, String key) {
        // Node to be deleted
        Node z = searchTree(node, key);
        Node x, y = z;
        boolean yOriginalColor = y.color;

        if (z.left == TNULL) {
            x = z.right;
            transplant(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            transplant(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;

            if (y.parent == z)
                x.parent = y;
            else {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (!yOriginalColor)
            fixDelete(x);
    }

    // Get predecessor
    public String getPrev(String key) {
        Node node = searchTree(root, key);
        if (node == TNULL) return "N/A";

        if (node.left != TNULL)
            return maximum(node.left).key;

        Node parent = node.parent;
        while (parent != null && node == parent.left) {
            node = parent;
            parent = parent.parent;
        }
        return parent == null ? "N/A" : parent.key;
    }

    // Get successor
    public String getNext(String key) {
        Node node = searchTree(root, key);
        if (node == TNULL) return "N/A";

        if (node.right != TNULL)
            return minimum(node.right).key;

        Node parent = node.parent;
        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }
        return parent == null ? "N/A" : parent.key;
    }

    // Range search
    public void rangeSearch(String lo, String hi, List<String> result) {
        rangeSearchHelper(root, lo, hi, result);
    }

    private void rangeSearchHelper(Node node, String lo, String hi, List<String> result) {
        if (node == TNULL) return;
        
        if (lo.compareTo(node.key) < 0)
            rangeSearchHelper(node.left, lo, hi, result);
        
        if (lo.compareTo(node.key) <= 0 && hi.compareTo(node.key) >= 0)
            result.add(node.key);
        
        if (hi.compareTo(node.key) > 0)
            rangeSearchHelper(node.right, lo, hi, result);
    }

    // Minimum and Maximum
    private Node minimum(Node node) {
        while (node.left != TNULL)
            node = node.left;
        return node;
    }

    private Node maximum(Node node) {
        while (node.right != TNULL)
            node = node.right;
        return node;
    }

    // Helper methods for transplant
    private void transplant(Node u, Node v) {
        if (u.parent == null)
            root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.right = v;
        v.parent = u.parent;
    }

    // Insertion fix
    private void fixInsert(Node k) {
        Node uncle;
        while (k.parent != null && k.parent.color) {
            if (k.parent == k.parent.parent.right) {
                uncle = k.parent.parent.left;
                if (uncle.color) {
                    uncle.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    leftRotate(k.parent.parent);
                }
            } else {
                uncle = k.parent.parent.right;
                if (uncle.color) {
                    uncle.color = false;
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = false;
                    k.parent.parent.color = true;
                    rightRotate(k.parent.parent);
                }
            }
        }
        root.color = false;
    }

    // Delete fix
    private void fixDelete(Node x) {
        Node s;
        while (x != root && !x.color) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color) {
                    s.color = false;
                    x.parent.color = true;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (!s.left.color && !s.right.color) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (!s.right.color) {
                        s.left.color = false;
                        s.color = true;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.right.color = false;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color) {
                    s.color = false;
                    x.parent.color = true;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (!s.right.color && !s.left.color) {
                    s.color = true;
                    x = x.parent;
                } else {
                    if (!s.left.color) {
                        s.right.color = false;
                        s.color = true;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = false;
                    s.left.color = false;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = false;
    }

    // Rotate left
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != TNULL)
            y.left.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        y.left = x;
        x.parent = y;
    }

    // Rotate right
    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != TNULL)
            y.right.parent = x;
        y.parent = x.parent;
        if (x.parent == null)
            root = y;
        else if (x == x.parent.right)
            x.parent.right = y;
        else
            x.parent.left = y;
        y.right = x;
        x.parent = y;
    }
}
