public class Solution {
    public boolean canMakeSubsequence(String str1, String str2) {
        int i = 0, j = 0;
        while (i < str1.length() && j < str2.length()) {
            char c1 = str1.charAt(i);
            char c2 = str2.charAt(j);

            // If characters match directly or with one cyclic increment
            if (c1 == c2 || (c1 + 1 - 'a') % 26 + 'a' == c2) {
                j++;  // move in str2 (matched)
            }
            i++;  // always move in str1
        }

        return j == str2.length();
    }
}
