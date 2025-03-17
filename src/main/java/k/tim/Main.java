package k.tim;

import java.util.Scanner;

public class Main {
    public static Boolean[][] spielfeld = new Boolean[6][7];
    public static int[] stack_height = {0,0,0,0,0,0,0};
    public static boolean turn_x = true;
    public static int WIDTH = 7;
    public static int HEIGHT = 6;

public static void main(String[] args) throws InterruptedException {
        boolean haswon = false;
        int count = 0;
        int status = 0;
        while(!haswon) {
            count++;
            clear();
            print();
            if (turn_x) {
                System.out.println("Please enter your turn, Player X!");
            } else {
                System.out.println("Please enter your turn, Player O!");
            }
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if(input < 8 && input > 0) {
                setPos(input-1);
                for (int i = 0; i < HEIGHT; i++) {
                    for (int j = 0; j < WIDTH; j++) {
                        status = checkWinner(i, j);
                        if(status == 1){
                            clear();
                            print();
                            System.out.println("\n\n!!! Player X won !!!\n\n");
                            haswon = true;
                            break;
                        }
                        else if(status == 2){
                            clear();
                            print();
                            System.out.println("\n\n!!! Player O won !!!\n\n");
                            haswon = true;
                            break;
                        }
                        else if(count == WIDTH*HEIGHT){
                            clear();
                            print();
                            System.out.println("\n\n!!! Tie !!!\n\n");
                            haswon = true;
                            break;
                        }
                    }
                }
            }

        }

        Thread.sleep(10000);
    }


    public static void setPos( int width) {
        if (stack_height[width] == 6) {
            System.out.println("Stack already full, try again");
            return;
        }
        if (turn_x) {
            spielfeld[stack_height[width]][width] = Boolean.TRUE;
        }
        else {
            spielfeld[stack_height[width]][width] = Boolean.FALSE;
        }
        turn_x = !turn_x;
        stack_height[width]++;
    }

    public static void print(){
        for (int i = 5; i >= 0; i--) {
            System.out.print("| ");
            for (int j = 0; j < 7; j++) {
                if (spielfeld[i][j] == Boolean.TRUE) {
                    System.out.print("X | ");
                }
                else if (spielfeld[i][j] == Boolean.FALSE) {
                    System.out.print("O | ");
                }
                else {
                    System.out.print("  | ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------------");
        System.out.println("  1   2   3   4   5   6   7");
    }

    public static void clear(){
        try {
            String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based OS (Linux, Mac)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int checkWinner(int i, int j) {
        if (spielfeld[i][j] == null) {
            return 0; // Kein Stein gesetzt
        }

        Boolean player = spielfeld[i][j];

        if (j + 3 < WIDTH && player.equals(spielfeld[i][j + 1]) && player.equals(spielfeld[i][j + 2]) && player.equals(spielfeld[i][j + 3])) {
            if(player) return 1;
            else return 2;
        }
        if (i + 3 < HEIGHT && player.equals(spielfeld[i + 1][j]) && player.equals(spielfeld[i + 2][j]) && player.equals(spielfeld[i + 3][j])) {
            if(player) return 1;
            else return 2;
        }
        if (i + 3 < HEIGHT && j + 3 < WIDTH && player.equals(spielfeld[i + 1][j + 1]) && player.equals(spielfeld[i + 2][j + 2]) && player.equals(spielfeld[i + 3][j + 3])) {
            if(player) return 1;
            else return 2;
        }

        if (i - 3 >= 0 && j + 3 < WIDTH && player.equals(spielfeld[i - 1][j + 1]) && player.equals(spielfeld[i - 2][j + 2]) && player.equals(spielfeld[i - 3][j + 3])) {
            if(player) return 1;
            else return 2;
        }

        return 0;
    }
}