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

            ArrayList<MultilayerPerceptron> grupo = new ArrayList<>();
            grupo.add(mlp0);
            grupo.add(mlp1);
            grupo.add(mlp2);
            grupo.add(mlp3);
            grupo.add(mlp4);
            grupo.add(mlp5);
            grupo.add(mlp6);
            grupo.add(mlp7);

            for (int i = 0; i < grupo.size(); i++) {
                ArrayList<MultilayerPerceptron> aux = new ArrayList<>();
                String nomeGrupo = "";

                for (int j = 0; j <= 7; j++) {
                    if (i != j) {
                        nomeGrupo += "nodo " + j + ", ";
                        aux.add(grupo.get(j));
                    }
                }
                
                criarGrupos(aux, nomeGrupo);
            }
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void criarGrupos(ArrayList<MultilayerPerceptron> list, String grupo) throws Exception {
        System.out.println("------------------------------");
        System.out.println(grupo + "\n");

        Vote votos = tipoCombina(list);

        inicializarRegras(votos);
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

    public static Vote tipoCombina(ArrayList<MultilayerPerceptron> mlp) {
        Vote vote = new Vote();

        // Adicionar os classificadores individuais ao sistema de votação
        for (MultilayerPerceptron multilayerPerceptron : mlp) {
            vote.addPreBuiltClassifier(multilayerPerceptron);
        }

        return vote;
    }
}
