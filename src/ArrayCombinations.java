import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayCombination {
    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, -4, -6, -2, 8};
        int target = 4;

        // Find combinations for the target value
        List<List<Integer>> firstCombinations = findCombinations(nums, target);
        System.out.println("First Combination for " + target + ": " + firstCombinations);

        // Merge the array into a single array and sort it
        int[] mergedArray = mergeArray(nums);
        System.out.println("Merge into a single array: " + Arrays.toString(mergedArray));

        // Find combinations for the double of the target value
        int doubleTarget = target * 2;
        List<List<Integer>> secondCombinations = findDoubleCombinations(mergedArray, doubleTarget);
        System.out.println("Second Combination for " + doubleTarget + ": " + secondCombinations);
    }

    private static List<List<Integer>> findCombinations(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>();

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    combinations.add(Arrays.asList(nums[i], nums[j]));
                }
            }
        }

        return combinations;
    }

    private static int[] mergeArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }

    private static List<List<Integer>> findDoubleCombinations(int[] nums, int target) {
        List<List<Integer>> combinations = new ArrayList<>();

        findCombinationsHelper(nums, target, 0, new ArrayList<>(), combinations);

        return combinations;
    }

    private static void findCombinationsHelper(int[] nums, int target, int start, List<Integer> currentCombination, List<List<Integer>> combinations) {
        if (target == 0) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }

            currentCombination.add(nums[i]);
            findCombinationsHelper(nums, target - nums[i], i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }
}
