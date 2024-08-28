/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classificadormulti;

import java.util.ArrayList;
import weka.classifiers.Classifier;
import weka.core.Instance;
import weka.core.Utils;

/**
 *
 * @author charl
 */
public class Combinacao {

    private ArrayList<Classifier> classifiers;

    public Combinacao(ArrayList<Classifier> classifiers) {
        this.classifiers = classifiers;
    }

    public int classifyInstanceUsingSumRule(Instance instance) throws Exception {
        double[] sumOfConfidences = new double[instance.numClasses()];

        for (Classifier classifier : classifiers) {
            double[] confidences = classifier.distributionForInstance(instance);
            for (int i = 0; i < confidences.length; i++) {
                sumOfConfidences[i] += confidences[i];
            }
        }

        return Utils.maxIndex(sumOfConfidences);
    }
}
