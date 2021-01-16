
package MKNN;

import java.util.*;


/**
 *
 * @author acer
 */
public class CrossValidation {
    
    private static double[][] matrix; // TF-IDF Matrix
    private static List<String> id; 
    private static List<String> topic; 
    
     public Map<Integer, Double> crossValidate(int kfold) {

        int len = id.size();

        // Gold labels
        Map<String, String> gold = new HashMap<String, String>();
        for (int i=0; i<len; i++) {
            gold.put(id.get(i), topic.get(i));
        }

        // Randomly shuffe document IDs for training
        List<String> shuffled = new ArrayList<String>(id);
        Collections.shuffle(shuffled);

        // Split training data into folds
        int foldSize = len / kfold;
        List<List<String>> folds = new LinkedList<List<String>>();
        for (int i=0; i<len; i+=foldSize) {
            folds.add(shuffled.subList(i, Math.min(i+foldSize, len)));
        }

        // Accuracy map
        Map<Integer, Double> accuracy = new HashMap<Integer, Double>();

        for (int k=1; k<16; k++) {
            System.out.println("k=" + k);

            // Accuracy per fold
            double[] acc = new double[kfold];

            for (int fold=0; fold<kfold; fold++) {

                // Get validation set
                List<Integer> validation = new ArrayList<Integer>();
                for (List<String> data: folds) {
                    if (folds.indexOf(data) != fold) { continue; }
                    // Get document indices
                    for (String id: data) {
                        validation.add(CrossValidation.id.indexOf(id));
                    }
                }

                // K-NN, hold out validation set
                MKNN knn = new MKNN(matrix, validation);

                // Number of correctly predicted labels
                int correct = 0;

                for (String id: folds.get(fold)) {

                    // Get document vector
                    int idx = CrossValidation.id.indexOf(id);
                    double[] vec = matrix[idx];
                    // Get k-nearest neighbors and topics
                    int[] neighbors = knn.getNearestNeighbors(vec, k, "cosine");
                    List<String> nearestTopics = new ArrayList<String>();
                    for (int i: neighbors) { nearestTopics.add(topic.get(i)); }
                    // Get label
                    String label = knn.getLabel(nearestTopics);
                    // Check if predicted label is correct
                    String trueLabel = topic.get(idx);
                    if (label.equals(trueLabel)) {
                        correct++;
                    }
                }
                // Compute accuracy
                double total = folds.get(fold).size();
                acc[fold] = correct / total;
            }
            // Average accuracy across folds
            double avg = 0.0;
            double sum = 0.0;
            double total = acc.length;
            for(int i=0; i<total; i++) {
                sum = sum + acc[i];
            }
            avg = sum / total;
            accuracy.put(k, avg);
        }
        return accuracy;
    }

}
