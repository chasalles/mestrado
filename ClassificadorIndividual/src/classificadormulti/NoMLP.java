/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classificadormulti;

import java.util.concurrent.TimeUnit;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

/**
 *
 * @author charl
 */
public class NoMLP {

    private MultilayerPerceptron mlp;

    public NoMLP(String diretorio, Instances testData) {
        try {
            long startTime = System.nanoTime(); // Captura o tempo inicial

            // Carregar o conjunto de dados (substitua "caminho/para/seu/arquivo.arff" pelo caminho correto)
            ConverterUtils.DataSource sourceTrain = new ConverterUtils.DataSource(diretorio);
            Instances trainData = sourceTrain.getDataSet();
            trainData.setClassIndex(trainData.numAttributes() - 1); // Definir o índice do atributo classe

            mlp = new MultilayerPerceptron();
            mlp.setHiddenLayers("16,32,64");
            mlp.buildClassifier(trainData);

            long endTime = System.nanoTime(); // Captura o tempo final

            // Calcule a diferença de tempo em nanossegundos
            long duration = endTime - startTime;

            // Converta para horas, minutos e segundos
            long hours = TimeUnit.NANOSECONDS.toHours(duration);
            long minutes = TimeUnit.NANOSECONDS.toMinutes(duration - TimeUnit.HOURS.toNanos(hours));
            long seconds = TimeUnit.NANOSECONDS.toSeconds(duration - TimeUnit.HOURS.toNanos(hours) - TimeUnit.MINUTES.toNanos(minutes));

            System.out.println("Tempo de execução do treinamento: " + hours + " horas, " + minutes + " minutos e " + seconds + " segundos");

            Evaluation eval = new Evaluation(trainData);
            eval.evaluateModel(mlp, testData);
            System.out.println(eval.toSummaryString());

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public MultilayerPerceptron getMlp() {
        return mlp;
    }
}
