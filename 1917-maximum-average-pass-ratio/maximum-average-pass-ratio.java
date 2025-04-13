import java.util.*;

class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Priority queue to store the classes based on the ratio of improvement
        PriorityQueue<Class> pq = new PriorityQueue<>((a, b) -> {
            // Compare based on the potential increase in pass ratio when one extra student is added
            return Double.compare(b.improvementRatio(), a.improvementRatio());
        });
        
        // Initialize the priority queue with all classes
        for (int[] c : classes) {
            pq.offer(new Class(c[0], c[1]));
        }
        
        // Distribute the extra students to the classes that benefit the most
        for (int i = 0; i < extraStudents; i++) {
            Class topClass = pq.poll(); // Get the class with the highest improvement ratio
            topClass.addStudent(); // Add one more student to that class
            pq.offer(topClass); // Push the updated class back into the queue
        }
        
        // Calculate the final average pass ratio
        double totalPassRatio = 0.0;
        int numClasses = classes.length;
        while (!pq.isEmpty()) {
            totalPassRatio += pq.poll().getPassRatio();
        }
        
        return totalPassRatio / numClasses;
    }
}

class Class {
    private int pass;
    private int total;
    
    public Class(int pass, int total) {
        this.pass = pass;
        this.total = total;
    }
    
    // Add one extra student to this class
    public void addStudent() {
        total++;
        pass++;
    }
    
    // Get the current pass ratio of this class
    public double getPassRatio() {
        return (double) pass / total;
    }
    
    // Calculate the improvement ratio: How much adding 1 student will improve the pass ratio
    public double improvementRatio() {
        return (double) (pass + 1) / (total + 1) - (double) pass / total;
    }
}
