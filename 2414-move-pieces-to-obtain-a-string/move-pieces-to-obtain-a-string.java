public class Solution {
    public boolean canChange(String start, String target) {
        int n = start.length();
        
        // Remove '_' and compare character sequences
        String s1 = start.replaceAll("_", "");
        String s2 = target.replaceAll("_", "");
        if (!s1.equals(s2)) return false;

        int i = 0, j = 0;

        while (i < n && j < n) {
            // Skip blanks
            while (i < n && start.charAt(i) == '_') i++;
            while (j < n && target.charAt(j) == '_') j++;

            // If both reached the end, it's fine
            if (i == n && j == n) return true;
            // If one reached end but not the other
            if (i == n || j == n) return false;

            char c1 = start.charAt(i);
            char c2 = target.charAt(j);

            // Characters must match
            if (c1 != c2) return false;

            // Check movement constraints
            if (c1 == 'L' && i < j) return false; // L can only move left
            if (c1 == 'R' && i > j) return false; // R can only move right

            i++;
            j++;
        }

        return true;
    }
}
