package com.dawang.androidsample.algorithm;

import org.junit.Test;

public class QuickUnionUF {
    int count;
    int[] IDs;

    public QuickUnionUF( ){
    }

    private QuickUnionUF(int N){
        count = N;
        IDs = new int[N];
        for (int i = 0; i < N; i++){
            IDs[i] = i;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q){
        int pID = IDs[p];
        int qID = IDs[q];

        if(pID == qID){
            return true;
        }

        return false;
    }


    public int find(int x){
        return IDs[x];
    }

    public void union(int p, int q){
        int pID = IDs[p];
        int qID = IDs[q];

        if(pID == qID) return;

        for (int i = 0; i < count; i++){
            if(IDs[i] == qID){
                IDs[i] = pID;
            }
        }
        count--;
    }


    @Test
    public void test(){

        QuickUnionUF uf = new QuickUnionUF(8);
        for (int i = 0; i < 8 - 1; i++, i++) {

            int p = i;
            int q = i+1;

            if (uf.connected(p, q)) {
                continue;
            }

            uf.union(p, q);
            System.err.println(p + "---" + q);
        }
        System.err.print(uf.count);
    }
}
