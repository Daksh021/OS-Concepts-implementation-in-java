public class MatrixMultiplication {

    static int[][] A = {{1, 2}, {3, 4}};
    static int[][] B = {{1, 2}, {3, 4}};

    static int[][] result = new int[2][2];

    static class Multiply extends Thread {

        int row;
        int col;



        Multiply(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public void run() {
            int sum = 0;
            for (int i = 0; i < A[0].length; i++) {
                sum += A[row][i] * B[i][col];
            }

            result[row][col] = sum;
        }


        public static void main(String[] args) throws InterruptedException {

            Multiply[][] threads = new Multiply[2][2];

            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    threads[i][j] = new Multiply(i, j);
                    threads[i][j].start();
                }
            }


            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    threads[i][j].join();
                }
            }


            System.out.println("Result matrix:");
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    System.out.print(result[i][j] + " ");
                }
                System.out.println();
            }
        }

    }

}