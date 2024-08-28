/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classificadormulti;

import java.util.ArrayList;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.meta.Vote;
import weka.core.Debug.Random;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author charl
 */
public class ClassificadorMulti {

    private static Instances testData;

    public static void main(String[] args) {
        try {
            DataSource sourceTest = new DataSource("C:\\Data\\DadosTratados\\arquivo_30.arff");
            testData = sourceTest.getDataSet();
            testData.setClassIndex(testData.numAttributes() - 1); // Definir o índice do atributo classe

            LerObjeto lo0 = new LerObjeto();
            MultilayerPerceptron mlp0 = lo0.lerMLP("C:\\Data\\MLPModelos\\no0.mol");

            LerObjeto lo1 = new LerObjeto();
            MultilayerPerceptron mlp1 = lo1.lerMLP("C:\\Data\\MLPModelos\\no1.mol");

            LerObjeto lo2 = new LerObjeto();
            MultilayerPerceptron mlp2 = lo2.lerMLP("C:\\Data\\MLPModelos\\no2.mol");

            LerObjeto lo3 = new LerObjeto();
            MultilayerPerceptron mlp3 = lo3.lerMLP("C:\\Data\\MLPModelos\\no3.mol");

            LerObjeto lo4 = new LerObjeto();
            MultilayerPerceptron mlp4 = lo4.lerMLP("C:\\Data\\MLPModelos\\no4.mol");

            LerObjeto lo5 = new LerObjeto();
            MultilayerPerceptron mlp5 = lo5.lerMLP("C:\\Data\\MLPModelos\\no5.mol");

            LerObjeto lo6 = new LerObjeto();
            MultilayerPerceptron mlp6 = lo6.lerMLP("C:\\Data\\MLPModelos\\no6.mol");

            LerObjeto lo7 = new LerObjeto();
            MultilayerPerceptron mlp7 = lo7.lerMLP("C:\\Data\\MLPModelos\\no7.mol");

            ArrayList<MultilayerPerceptron> nodo8 = new ArrayList<>();
            nodo8.add(mlp2);
            nodo8.add(mlp3);
            nodo8.add(mlp4);
            nodo8.add(mlp5);
            nodo8.add(mlp6);
            nodo8.add(mlp7);

            nodoEmFalhaArvore(nodo8, "FALHA - NODO 8");

            ArrayList<MultilayerPerceptron> nodo9 = new ArrayList<>();
            nodo9.add(mlp0);
            nodo9.add(mlp1);
            nodo9.add(mlp4);
            nodo9.add(mlp5);
            nodo9.add(mlp6);
            nodo9.add(mlp7);

            nodoEmFalhaArvore(nodo9, "FALHA - NODO 9");

            ArrayList<MultilayerPerceptron> nodo10 = new ArrayList<>();
            nodo10.add(mlp0);
            nodo10.add(mlp1);
            nodo10.add(mlp2);
            nodo10.add(mlp3);
            nodo10.add(mlp6);
            nodo10.add(mlp7);

            nodoEmFalhaArvore(nodo10, "FALHA - NODO 10");

            ArrayList<MultilayerPerceptron> nodo11 = new ArrayList<>();
            nodo11.add(mlp0);
            nodo11.add(mlp1);
            nodo11.add(mlp2);
            nodo11.add(mlp3);
            nodo11.add(mlp5);
            nodo11.add(mlp6);

            nodoEmFalhaArvore(nodo11, "FALHA - NODO 11");

            ArrayList<MultilayerPerceptron> nodo12 = new ArrayList<>();
            nodo12.add(mlp4);
            nodo12.add(mlp5);
            nodo12.add(mlp6);
            nodo12.add(mlp7);

            nodoEmFalhaArvore(nodo12, "FALHA - NODO 12");

            ArrayList<MultilayerPerceptron> nodo13 = new ArrayList<>();
            nodo13.add(mlp0);
            nodo13.add(mlp1);
            nodo13.add(mlp2);
            nodo13.add(mlp3);

            nodoEmFalhaArvore(nodo13, "FALHA - NODO 13");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void nodoEmFalhaArvore(ArrayList<MultilayerPerceptron> list, String grupo) throws Exception {
        System.out.println("------------------------------");
        System.out.println(grupo + "\n");

        Vote votos = tipoCombina(list);
        inicializarRegras(votos);
    }

    public static Vote tipoCombina(ArrayList<MultilayerPerceptron> mlp) {
        Vote vote = new Vote();

        // Adicionar os classificadores individuais ao sistema de votação
        for (MultilayerPerceptron multilayerPerceptron : mlp) {
            vote.addPreBuiltClassifier(multilayerPerceptron);
        }

        return vote;
    }

    public static void inicializarRegras(Vote vote) throws Exception {
        avaliarCombinacao(vote, Vote.AVERAGE_RULE);
        avaliarCombinacao(vote, Vote.MAJORITY_VOTING_RULE);
        avaliarCombinacao(vote, Vote.PRODUCT_RULE);
    }

    public static void avaliarCombinacao(Vote vote, int RULE) throws Exception {
        vote.setCombinationRule(new SelectedTag(RULE, Vote.TAGS_RULES));

        // Avaliar o sistema de votação usando validação cruzada
        Evaluation eval = new Evaluation(testData);
        eval.crossValidateModel(vote, testData, 10, new Random(1)); // 10-fold cross-validation
        System.out.println("Regra " + RULE + ": " + eval.pctCorrect() + "%");
    }
}
