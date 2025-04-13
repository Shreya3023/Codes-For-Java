public class Solution {
    public String addSpaces(String s, int[] spaces) {
        StringBuilder result = new StringBuilder();
        int spaceIndex = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            // If current index matches the next space to insert
            if (spaceIndex < spaces.length && i == spaces[spaceIndex]) {
                result.append(' ');
                spaceIndex++;
            }
            result.append(s.charAt(i));
        }

        return result.toString();
    }
}
