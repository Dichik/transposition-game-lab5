import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Grid {

    public static Integer[] array;
    public static final Integer SIZE = 8;

    public Grid() {
        array = new Integer[SIZE];
        IntStream.rangeClosed(1, SIZE).forEachOrdered(i -> array[i - 1] = i);
    }

    public void swapOnPositions(int firstPosition, int secondPosition) {
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

    public boolean checkIfWeCanMakeMove(int firstPosition, int secondPosition) {
        return array[firstPosition] < array[secondPosition];
    }

}
