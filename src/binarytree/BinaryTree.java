package binarytree;

import java.io.*;
import java.util.*;
import java.io.File;

public class BinaryTree {

    /*
    Below you will see the construction of a node class that uses the song title
    as the key element.
     */
    class Node {

        String key;
        Node left, right;

        public Node(String title) {
            key = title;
            left = right = null;
        }
    }

    // Root of the binary tree
    Node root;

    // Constructor for the binary tree
    BinaryTree() {
        root = null;
    }

    // This method mainly calls insert() method below
    void insert(String title) {
        root = insert(root, title);
    }

    /* Below is a recursive funtion that will insert
    the nodes into the binary tree*/
    Node insert(Node root, String title) {

        if (root == null) {
            root = new Node(title);
            return root;
        }

        if (title.compareToIgnoreCase(root.key) < 0) {
            root.left = insert(root.left, title);
        } else if (title.compareToIgnoreCase(root.key) > 0) {
            root.right = insert(root.right, title);
        }

        return root;
    }

    // This method mainly calls InorderRec() function below
    void inorder() throws FileNotFoundException {
        inorderRec(root);
    }

    /*
    This mehthod after being called traveserses through the tree in an inorder
    path and then also prints out the tree
     */
    void inorderRec(Node root) FileNotFoundException {
      PrintStream ps = new PrintStream("PlaylistSorted.txt");
        if (root != null) {
            inorderRec(root.left);
            ps.println(root.key);
            inorderRec(root.right);
        }

    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] titles = new String[100];

        createArray(titles);
        print(titles);

        Scanner xz = new Scanner(System.in);

        System.out.println("Based on the song titles above choose where you would like you playlist to begin and end based on position number");
        int start = Integer.parseInt(xz.next());
        int end = Integer.parseInt(xz.next());

        subSet(start, end, titles);

    }

    public static void createArray(String[] titles) {

        int j = 0;
        String[] myFiles = new String[]{"viral-us-weekly-2020-10-08.txt", "viral-us-weekly-2020-09-03.txt"};
        for (int i = 0; i < myFiles.length; i++) {
            try {
                Scanner sc = new Scanner(new File(myFiles[i]));
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] lineArray = line.split(",");
                    titles[j] = lineArray[1];
                    j++;

                }

            } catch (FileNotFoundException e) {
                System.out.println("File Read Error");
            }
        }
    }

    public static void print(String[] titles) {
        for (int i = 0; i < titles.length; i++) {
            System.out.println(i + 1 + "   " + titles[i]);
        }
    }

    public static void subSet(int start, int end, String[] titles) throws FileNotFoundException {
        PrintStream ps = new PrintStream("PlaylistSorted.txt");
        BinaryTree playlist = new BinaryTree();
        for (int i = start - 1; i <= end - 1; i++) {
            playlist.insert(titles[i]);
        }
        ps.print("Your Playlist: ");
        playlist.inorder();
    }

}
