import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public boolean madeByComputer(int x) {
        return computerMoves.contains(x);
    }

    public static void makeFirstPossibleMove() {
        for(int i = 0; i < SIZE - 1; ++ i) {
            for(int j = i + 1; j < SIZE - 1; ++ j) {
                if(checkIfWeCanMakeMove(i, j)) {
                    swapOnPositions(i, j);
                    computerMoves.addAll(List.of(i, j));
                    return;
                }
            }
        }
    }

    public void clearComputerMoves() {
        computerMoves.clear();
    }

    public static void swapOnPositions(int firstPosition, int secondPosition) {
        int x = array[firstPosition];
        array[firstPosition] = array[secondPosition];
        array[secondPosition] = x;
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

    public static boolean checkIfWeCanMakeMove(int firstPosition, int secondPosition) {
        return array[Math.min(firstPosition, secondPosition)] <
                array[Math.max(secondPosition, firstPosition)];
    }

}
