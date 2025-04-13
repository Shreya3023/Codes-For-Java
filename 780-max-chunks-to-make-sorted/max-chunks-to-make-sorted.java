class Solution {
    public int maxChunksToSorted(int[] arr) {
        int max_so_far = -1;  // Initially, no element is considered.
        int chunks = 0;
        
        for (int i = 0; i < arr.length; i++) {
            max_so_far = Math.max(max_so_far, arr[i]);
            if (max_so_far == i) {
                chunks++;  // We can partition the array at this point
            }
        }
        
        return chunks;
    }
}
