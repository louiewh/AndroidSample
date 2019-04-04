package com.dawang.androidsample.algorithm;

import org.junit.Test;

public class DepthOfBinTree {


    @Test
    public void test(){
        Bst2List.TreeNode root = new Bst2List.TreeNode(10);

        root.mLeft = new Bst2List.TreeNode(6);
        root.mRight = new Bst2List.TreeNode(15);

        root.mLeft.mLeft = new Bst2List.TreeNode(5);
        root.mLeft.mRight = new Bst2List.TreeNode(7);

        root.mRight.mLeft = new Bst2List.TreeNode(11);
        root.mRight.mRight = new Bst2List.TreeNode(16);

        int result = maxDepthOfTree(root);

        System.err.print(result);
    }

    int maxDepthOfTree(Bst2List.TreeNode root){

        if(root == null) return  0;

        int leftDept = maxDepthOfTree(root.mLeft);
        int rightDept = maxDepthOfTree(root.mRight);

        return leftDept > rightDept ? (leftDept+1):(rightDept+1);
    }
}
