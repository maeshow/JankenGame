import java.util.Random;
import java.util.Scanner;

public class App {
    private static final Scanner STDIN = new Scanner(System.in);

    private static final String[] HAND = { "グー", "チョキ", "パー" };
    private static final String[] WAY = { "右", "左", "上", "下" };
    private static final int GUU_INDEX = 0;
    private static final int CHOKI_INDEX = 1;
    private static final int PAA_INDEX = 2;
    private static final int JANKEN_GAME_MODE = 0;
    private static final int LOOK_THIS_WAY_GAME_MODE = 1;
    private static final int TIMES_LIMIT = 3;

    private static boolean isDraw = false;
    private static int playerWinCount = 0;

    public static void main(String[] args) {
        showWithNewLine(Messages.TITLE);
        showFormattedMessage(Messages.GAME_TIMES_LIMIT, TIMES_LIMIT);
        showWithNewLine(Messages.JANKEN_START);
        showInputDescription(JANKEN_GAME_MODE);

        startGame();

        STDIN.close();
    }

    private static int getComputerchoice(int gameMode) {
        Random random = new Random();
        switch (gameMode) {
        case JANKEN_GAME_MODE:
            return random.nextInt(HAND.length);
        case LOOK_THIS_WAY_GAME_MODE:
            return random.nextInt(WAY.length);
        default:
            return 0;
        }
    }

    private static int getPlayerChoiceIndex(int gameMode) {
        showWaitingInput(gameMode);
        String input = STDIN.next();
        int inputNumber = 0;
        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            showWithNewLine(Messages.ENTER_NUMBER_WARN);
            showNewLine();
            return getPlayerChoiceIndex(gameMode);
        }

        if (isCorrectRange(inputNumber, gameMode)) {
            return inputNumber;
        }
        showWithNewLine(Messages.NUMBER_MUST_BETWEEN_WARN);
        showNewLine();
        return getPlayerChoiceIndex(gameMode);
    }

    private static void startGame() {
        int count = 0;
        while (!isOverMaxChallengeTimes(count)) {
            startMatch();
            count++;
        }
        showResultInfo(playerWinCount);
    }

    private static void startMatch() {
        while (true) {
            int playerHand = getPlayerChoiceIndex(JANKEN_GAME_MODE);
            int computerHand = getComputerchoice(JANKEN_GAME_MODE);
            showFormattedMessage(Messages.RESULT, HAND[computerHand], HAND[playerHand]);
            if (isDrawByJanken(playerHand, computerHand)) {
                showWithNewLine(Messages.DRAW);
                isDraw = true;
                continue;
            }
            if (isPlayerWinByJanken(playerHand, computerHand)) {
                showWithNewLine(Messages.WIN);
                isDraw = false;
                startLookThisWayMatch(true);
                break;
            }
            showWithNewLine(Messages.LOSE);
            isDraw = false;
            startLookThisWayMatch(false);
            break;
        }
    }

    private static void startLookThisWayMatch(boolean isPlayerWinByJanken) {
        showWithNewLine(Messages.LOOK_THIS_WAY_START);
        showInputDescription(LOOK_THIS_WAY_GAME_MODE);
        int playerWay = getPlayerChoiceIndex(LOOK_THIS_WAY_GAME_MODE);
        int computerWay = getComputerchoice(LOOK_THIS_WAY_GAME_MODE);
        showWithNewLine(Messages.LOOK_THIS_WAY_END);
        showFormattedMessage(Messages.RESULT, WAY[computerWay], WAY[playerWay]);
        if (isPlayerWinByLookThisWay(playerWay, computerWay, isPlayerWinByJanken)) {
            playerWinCount++;
            showWithNewLine(Messages.WIN);
        } else {
            showWithNewLine(Messages.LOSE);
        }
    }

    private static boolean isDrawByJanken(int player, int computer) {
        return player == computer;
    }

    private static boolean isPlayerWinByJanken(int player, int computer) {
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

    private static boolean isPlayerWinByLookThisWay(int player, int computer, boolean isPlayerWinByJanken) {
        if (isPlayerWinByJanken) {
            return player == computer;
        }
        return player != computer;
    }

    private static boolean isCorrectRange(int a, int gameMode) {
        switch (gameMode) {
        case JANKEN_GAME_MODE:
            return 0 <= a && a < HAND.length;
        case LOOK_THIS_WAY_GAME_MODE:
            return 0 <= a && a < WAY.length;
        default:
            return false;
        }
    }

    private static boolean isOverMaxChallengeTimes(int count) {
        return TIMES_LIMIT <= count;
    }

    private static void showInputDescription(int gameMode) {
        int count = 0;
        String[] array = new String[0];
        switch (gameMode) {
        case JANKEN_GAME_MODE:
            array = HAND;
            break;
        case LOOK_THIS_WAY_GAME_MODE:
            array = WAY;
            break;
        }

        for (String str : array) {
            showFormattedMessage(Messages.INPUT_DEFINE, count, str);
            count++;
        }
    }

    private static void showWaitingInput(int gameMode) {
        switch (gameMode) {
        case JANKEN_GAME_MODE:
            if (isDraw) {
                showWithoutNewLine(Messages.JANKEN_WAITING_INPUT_FOR_DRAW);
            } else {
                showWithoutNewLine(Messages.JANKEN_WAITING_INPUT);
            }
            break;
        case LOOK_THIS_WAY_GAME_MODE:
            showWithoutNewLine(Messages.LOOK_THIS_WAY_WAITING_INPUT);
            break;
        }
    }

    private static void showResultInfo(int playerWinCount) {
        showWithNewLine(Messages.RESULT_INFO);
        showFormattedMessage(Messages.PLAYER_WIN_COUNT, playerWinCount);
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

    private static void showFormattedMessage(String message, int a) {
        System.out.format(message, a);
    }

    private static void showFormattedMessage(String message, int a, String b) {
        System.out.format(message, a, b);
    }

    private static void showFormattedMessage(String message, String a, String b) {
        System.out.format(message, a, b);
    }
}