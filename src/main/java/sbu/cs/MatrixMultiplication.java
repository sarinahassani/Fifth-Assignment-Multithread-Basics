package sbu.cs;

import java.util.ArrayList;
import java.util.List;

public class MatrixMultiplication {

    // You are allowed to change all code in the BlockMultiplier class
    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> tempMatrixProduct;
        List<List<Integer>> matrix_B;
        List<List<Integer>> block;
        public BlockMultiplier(List<List<Integer>> matrix_B, List<List<Integer>> block) {
            // TODO
            this.matrix_B = matrix_B;
            this.block = block;
        }

        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
            for (int i=0; i<block.size(); i++) {
                List<Integer> line = new ArrayList<>();
                for (int k=0; k<matrix_B.get(i).size(); k++) {
                    int sum = 0;
                    for (int j = 0; j < block.get(i).size(); j++) {
                        sum += (block.get(i).get(j)) * (matrix_B.get(j).get(k));
                    }
                    line.set(k, sum);
                }
                tempMatrixProduct.set(i, line);
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B) throws InterruptedException {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        List<List<Integer>> matrix_C = new ArrayList<>();
        int num = (matrix_A.size())/4;
        List<List<Integer>> block1 = new ArrayList<>();
        for (int i=0; i<num; i++) {
            block1.set(i, matrix_A.get(i));
        }
        BlockMultiplier Block1 = new BlockMultiplier(matrix_B, block1);
        Thread thread1 = new Thread(Block1);

        List<List<Integer>> block2 = new ArrayList<>();
        for (int i=0; i<num; i++) {
            block2.set(i, matrix_A.get(i+num));
        }
        BlockMultiplier Block2 = new BlockMultiplier(matrix_B, block2);
        Thread thread2 = new Thread(Block1);

        List<List<Integer>> block3 = new ArrayList<>();
        for (int i=0; i<num; i++) {
            block3.set(i, matrix_A.get(i+(2*num)));
        }
        BlockMultiplier Block3 = new BlockMultiplier(matrix_B, block3);
        Thread thread3 = new Thread(Block1);

        List<List<Integer>> block4 = new ArrayList<>();
        for (int i=0; i<num; i++) {
            block3.set(i, matrix_A.get(i+(3*num)));
        }
        BlockMultiplier Block4 = new BlockMultiplier(matrix_B, block3);
        Thread thread4 = new Thread(Block1);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i=0; i<num; i++) {
            matrix_C.add(Block1.tempMatrixProduct.get(i));
        }
        for (int i=0; i<num; i++) {
            matrix_C.add(Block2.tempMatrixProduct.get(i));
        }
        for (int i=0; i<num; i++) {
            matrix_C.add(Block3.tempMatrixProduct.get(i));
        }
        for (int i=0; i<num; i++) {
            matrix_C.add(Block4.tempMatrixProduct.get(i));
        }

        return matrix_C;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
