package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;


/*
 请实现两个函数，分别用来序列化和反序列化二叉树
*/

public class SerializeAndDeserializeTree {

    private int deIndex = 0;

    @Test
    public void test(){
        // {8,6,10,5,7,9,11}
        TreeNode root = new TreeNode(8);
        TreeNode left = root.left = new TreeNode(6);
        TreeNode right = root.right = new TreeNode(10);
        TreeNode rootNode = root;

        root = left;
        root.left = new TreeNode(5);
        root.right = new TreeNode(7);

        root = right;
        root.left = new TreeNode(9);
        root.right = new TreeNode(11);

        String result = Serialize(rootNode);
        Deserialize(result);

    }

    String Serialize(TreeNode root) {
        StringBuffer  buffer = new StringBuffer();

        if(root == null) {
            return buffer.append("#,").toString();
        }

        buffer.append(root.val+",");
        buffer.append(Serialize(root.left));
        buffer.append(Serialize(root.right));

        return buffer.toString();
    }


    TreeNode Deserialize(String str) {

        String[] A = str.split(",");

        return DeserializeArray(A);
    }

    TreeNode DeserializeArray(String[] A){

        String  str = A[deIndex++];
        TreeNode node = null;
        if(!str.equals("#")){
            int value = Integer.valueOf(str);
            node = new TreeNode(value);

            node.left = DeserializeArray(A);
            node.right = DeserializeArray(A);

            return node;
        }

        return  node;
    }


    public class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }
}
