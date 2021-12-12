import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    public static Integer[] array;
    public static final Integer SIZE = 8;

    private static Set<Integer> computerMoves;

    public Grid() {
        array = new Integer[SIZE];
        computerMoves = new HashSet<>();

        IntStream.rangeClosed(1, SIZE).forEachOrdered(i -> array[i - 1] = i);
    }

    public void makeMove(int d) {
        int best = minimax(new ArrayList<>(List.of(array)), d, true);

        System.out.println("Best: " + best);

        for (int i = 0; i < SIZE - 1; ++i) {
            for (int j = i + 1; j < SIZE - 1; ++j) {
                if (checkIfWeCanMakeMove(i, j)) {
                    swapOnPositions(i, j);
                    if (f(new ArrayList<>(List.of(array))) >= best) {
                        computerMoves.addAll(List.of(i, j));
                        return;
                    } else swapOnPositions(i, j);
                }
            }
        }
    }

    private static int f(List<Integer> list) {
        int count = 0;
        List<Integer> evens = list.stream().filter(p -> p % 2 == 0)
                .collect(Collectors.toList());
        int temp = 0;
        for (int i = 0; i < evens.size() - 1; ++i) {
            if (evens.get(i) > evens.get(i + 1)) {
                temp++;
            }
        }
        count += (temp > 0) ? temp + 1 : 0;
        temp = 0;

        List<Integer> odds = list.stream().filter(p -> p % 2 == 1)
                .collect(Collectors.toList());
        for (int i = 0; i < odds.size() - 1; ++i) {
            if(odds.get(i) > odds.get(i + 1)) {
                temp++;
            }
        }
        count -= (temp > 0) ? temp + 1 : 0;

        return count;
    }

    public boolean madeByComputer(int x) {
        return computerMoves.contains(x);
    }

    public void clearComputerMoves() {
        computerMoves.clear();
    }

    public static void swapOnPositions(int firstPosition, int secondPosition) {
        int x = array[firstPosition];
        array[firstPosition] = array[secondPosition];
        array[secondPosition] = x;
    }

    public static boolean checkEvenNumbers(List<Integer> a) {
        List<Integer> list = a.stream()
                .filter(element -> element % 2 == 0)
                .collect(Collectors.toList());
        return IntStream.range(0, list.size() - 1).noneMatch(i -> list.get(i) < list.get(i + 1));
    }

    public boolean checkEvenNumbers() {
        List<Integer> list = Arrays.stream(array)
                .filter(element -> element % 2 == 0)
                .collect(Collectors.toList());
        return IntStream.range(0, list.size() - 1).noneMatch(i -> list.get(i) < list.get(i + 1));
    }

    public boolean checkOddNumbers() {
        List<Integer> list = Arrays.stream(array)
                .filter(element -> element % 2 == 1)
                .collect(Collectors.toList());
        return IntStream.range(0, list.size() - 1).noneMatch(i -> list.get(i) < list.get(i + 1));
    }

    public static boolean checkOddNumbers(List<Integer> a) {
        List<Integer> list = a.stream()
                .filter(element -> element % 2 == 1)
                .collect(Collectors.toList());
        return IntStream.range(0, list.size() - 1).noneMatch(i -> list.get(i) < list.get(i + 1));
    }

    public static boolean checkIfWeCanMakeMove(int firstPosition, int secondPosition) {
        return array[Math.min(firstPosition, secondPosition)] <
                array[Math.max(secondPosition, firstPosition)];
    }

    private static boolean hasWinner(List<Integer> list) {
        return checkEvenNumbers(list) || checkOddNumbers(list);
    }

    private static int minimax(List<Integer> position, int depth, boolean maximizingPlayer) {
        if (depth == 0 || hasWinner(position)) {
            return f(position);
        }

        if (maximizingPlayer) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < SIZE - 1; ++i) {
                for (int j = i + 1; j < SIZE; ++j) {
                    if (position.get(i) < position.get(j)) {
                        List<Integer> child = new ArrayList<>(position);
                        int x = child.get(i);
                        child.set(i, child.get(j));
                        child.set(j, x);
                        int eval = minimax(new ArrayList<>(position), depth - 1, false);
                        maxEval = Math.max(maxEval, eval);
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;

            for (int i = 0; i < SIZE - 1; ++i) {
                for (int j = i + 1; j < SIZE; ++j) {
                    if (position.get(i) < position.get(j)) {
                        List<Integer> child = new ArrayList<>(position);
                        int x = child.get(i);
                        child.set(i, child.get(j));
                        child.set(j, x);
                        int eval = minimax(new ArrayList<>(position), depth - 1, true);
                        minEval = Math.min(minEval, eval);
                    }
                }
            }

            return minEval;

        }

    }

}
