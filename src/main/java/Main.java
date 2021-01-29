import java.util.Random;
import java.util.Scanner;

public class Main {
    private static char[][] gameMap;
    private static Scanner sc = new Scanner(System.in);
    private static final char EMPTY_DOTS = '•';
    private static final char X_DOT = 'X';
    private static final char O_DOT = '0';

    public static void main(String[] args) {
        gameMap();
        paintGameMap();
        gameMoves();
    }

    private static void gameMoves() {
        int count = 0;
        while (true) {
            count++;
            humanStroke();
            paintGameMap();
            if (winCheck(X_DOT)) {
                System.out.println(" Вы победили!!! ");
                break;
            }
            if (count == 9) {
                System.out.println(" Ничья. ");
            }
            compucterStroke();
            count++;
            paintGameMap();
            if (winCheck(O_DOT)) {
                System.out.println(" Вы проиграли ");
                break;
            }
        }

    }

    private static void paintGameMap() {

        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                System.out.print("|" + gameMap[i][j]);
            }
            System.out.println("|");
        }
        System.out.println();
    }

    private static void gameMap() {
        gameMap = new char[3][3];
        for (int i = 0; i < gameMap.length; i++) {
            for (int j = 0; j < gameMap[i].length; j++) {
                gameMap[i][j] = EMPTY_DOTS;
            }
        }
    }

    private static void humanStroke() {
        int x = 0;
        int y = 0;
        boolean checkCell = false;
        do {
            System.out.println("Введите кординаты в формате X Y");
            if (sc.hasNextInt()) {
                x = sc.nextInt() - 1;
            }
            if (sc.hasNextInt()) {
                y = sc.nextInt() - 1;
            }
            checkCell = isValidCell(x, y);
            sc.hasNextLine();

        } while (!checkCell);
        gameMap[x][y] = X_DOT;
    }

    private static boolean isValidCell(int x, int y) {
        if (x < 0 || y < 0 || x > gameMap.length || y > gameMap.length) {
            return false;
        }
        return gameMap[x][y] == EMPTY_DOTS;

    }

    private static void compucterStroke() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }

        int x = 0;
        int y = 0;
        boolean checkCell;
        do {
            Random random = new Random();
            x = random.nextInt(3);
            y = random.nextInt(3);
            checkCell = isValidCell(x, y);
        } while (!checkCell);
        gameMap[x][y] = O_DOT;
    }

    private static boolean winCheck(char c) {

        for (int i = 0; i < gameMap.length; i++) {
            if ((gameMap[i][0] == c && gameMap[i][1] == c && gameMap[i][2] == c) ||
                    (gameMap[0][i] == c && gameMap[1][i] == c && gameMap[2][i] == c))
                return true;
            if ((gameMap[0][0] == c && gameMap[1][1] == c && gameMap[2][2] == c) ||
                    (gameMap[2][0] == c && gameMap[1][1] == c && gameMap[0][2] == c))
                return true;
        }
        return false;

    }
}