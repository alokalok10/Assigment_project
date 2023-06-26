import java.util.*;

public class ArrayCombinations {
    public static int[][] findCombinations(int[] nums, int target) {
        List<List<Integer>> pairs = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;

            if (seen.contains(complement)) {
                pairs.add(Arrays.asList(complement, num));
            }

            seen.add(num);
        }

        int[][] combinations = new int[pairs.size()][2];
        for (int i = 0; i < pairs.size(); i++) {
            combinations[i][0] = pairs.get(i).get(0);
            combinations[i][1] = pairs.get(i).get(1);
        }

        return combinations;
    }

    public static int[] mergeAndSort(int[] nums) {
        int[] merged = Arrays.copyOf(nums, nums.length);
        Arrays.sort(merged);
        return merged;
    }

    public static int[][] findCombinationsOfDoubledTarget(int[] nums, int target) {
        int doubledTarget = target * 2;
        List<List<Integer>> combinations = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            List<Integer> combination = new ArrayList<>();
            combination.add(nums[i]);

            int remaining = doubledTarget - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] <= remaining) {
                    combination.add(nums[j]);
                    remaining -= nums[j];
                }
            }

            if (remaining == 0) {
                combinations.add(combination);
            }
        }

        int[][] combinationsArray = new int[combinations.size()][];
        for (int i = 0; i < combinations.size(); i++) {
            combinationsArray[i] = combinations.get(i).stream().mapToInt(Integer::intValue).toArray();
        }

        return combinationsArray;
    }

    public static void main(String[] args) {
        int[] inputArray = {1, 3, 2, 2, -4, -6, -2, 8};
        int targetValue = 4;

        // Step 1: Find pairs
        int[][] pairs = findCombinations(inputArray, targetValue);
        System.out.println("First Combination For \"" + targetValue + "\": " + Arrays.deepToString(pairs));

        // Step 2: Merge and sort
        int[] mergedArray = mergeAndSort(inputArray);
        System.out.println("Merge Into a single Array: " + Arrays.toString(mergedArray));

        // Step 3: Find combinations of doubled target
        int[][] combinations = findCombinationsOfDoubledTarget(mergedArray, targetValue);
        System.out.println("Second Combination For \"" + (targetValue * 2) + "\": " + Arrays.deepToString(combinations));
    }
}
