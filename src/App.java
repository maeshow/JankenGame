import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);

    private static final String[] HAND = { "グー", "チョキ", "パー" };
    private static final int GUU_INDEX = 0;
    private static final int CHOKI_INDEX = 1;
    private static final int PAA_INDEX = 2;
    private static final int FIRST_HAND_INDEX = 0;
    private static final int LAST_HAND_INDEX = HAND.length - 1;

    public static void main(String[] args) {
        showWithNewLine(Messages.TITLE);
        showWithNewLine(Messages.JANKEN_START);
        showInputDefine();

        onGameStart();

        STDIN.close();
    }

    private static int getComputerHand() {
        Random random = new Random();

        return random.nextInt(HAND.length);
    }

    private static int getPlayerHand(boolean isDraw) {
        if (isDraw) {
            showWithoutNewLine(Messages.WAITING_INPUT_FOR_DRAW);
        } else {
            showWithoutNewLine(Messages.WAITING_INPUT);
        }

        String input = STDIN.next();
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showWithNewLine(Messages.ENTER_NUMBER_WARN);
            showNewLine();
            return getPlayerHand(isDraw);
        }

        if (isCorrectRange(inputNumber)) {
            return inputNumber;
        }
        showFormattedMessage(Messages.NUMBER_MUST_BETWEEN_WARN, FIRST_HAND_INDEX, LAST_HAND_INDEX);
        showNewLine();
        return getPlayerHand(isDraw);
    }

    private static void onGameStart() {
        boolean isDraw = false;
        while (!isOverMaxChallengeTimes()) {
            int playerHand = getPlayerHand(isDraw);
            int computerHand = getComputerHand();
            showFormattedMessage(Messages.RESULT, HAND[computerHand], HAND[playerHand]);
            if (isDraw(playerHand, computerHand)) {
                showWithNewLine(Messages.DRAW);
                continue;
            }
            if (isPlayerWin(playerHand, computerHand)) {
                showWithNewLine(Messages.WIN);
                break;
            } else {
                showWithNewLine(Messages.LOSE);
                break;
            }
        }
    }

    private static boolean isDraw(int player, int computer) {
        return player == computer;
    }

    private static boolean isPlayerWin(int player, int computer) {
        switch (player) {
        case GUU_INDEX:
            return computer == CHOKI_INDEX;
        case CHOKI_INDEX:
            return computer == PAA_INDEX;
        case PAA_INDEX:
            return computer == GUU_INDEX;
        default:
            return false;
        }
    }

    private static boolean isCorrectRange(int a) {
        return 0 <= a && a < HAND.length;
    }

    private static boolean isOverMaxChallengeTimes() {
        return false;
    }

    private static void showInputDefine() {
        int count = 0;
        for (String str : HAND) {
            showFormattedMessage(Messages.INPUT_DEFINE, count, str);
            count++;
        }
    }

    private static void showWithNewLine(String message) {
        System.out.println(message);
    }

    private static void showWithoutNewLine(String message) {
        System.out.printf(message);
    }

    private static void showNewLine() {
        System.out.println();
    }

    private static void showFormattedMessage(String message, int a, String b) {
        System.out.format(message, a, b);
    }

    private static void showFormattedMessage(String message, int a, int b) {
        System.out.format(message, a, b);
    }

    private static void showFormattedMessage(String message, String a, String b) {
        System.out.format(message, a, b);
    }
}