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

            // grupo 1 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo1 = new ArrayList<>();
            grupo1.add(mlp0);
            grupo1.add(mlp1);
            criarGrupos(grupo1, "Grupo: Nodo 0, Nodo 1");

            // grupo 2 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo2 = new ArrayList<>();
            grupo2.add(mlp2);
            grupo2.add(mlp3);
            criarGrupos(grupo2, "Grupo: Nodo 2, Nodo 3");

            // grupo 3 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo3 = new ArrayList<>();
            grupo3.add(mlp4);
            grupo3.add(mlp5);
            criarGrupos(grupo3, "Grupo: Nodo 4, Nodo 5");

            // grupo 4 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo4 = new ArrayList<>();
            //grupo4.add(mlp6);
            grupo4.add(mlp7);
            criarGrupos(grupo4, "Grupo: Nodo 6, Nodo 7");

            // grupo 5 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo5 = new ArrayList<>();
            grupo5.add(mlp0);
            grupo5.add(mlp1);
            grupo5.add(mlp2);
            grupo5.add(mlp3);
            criarGrupos(grupo5, "Grupo: Nodo 0, Nodo 1, Nodo 2, Nodo 3");

            // grupo 6 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo6 = new ArrayList<>();
            grupo6.add(mlp4);
            grupo6.add(mlp5);
            //grupo6.add(mlp6);
            grupo6.add(mlp7);
            criarGrupos(grupo6, "Grupo: Nodo 4, Nodo 5, Nodo 6, Nodo 7");

            // grupo 5 vcube primeira rodada
            ArrayList<MultilayerPerceptron> grupo7 = new ArrayList<>();
            grupo7.add(mlp0);
            grupo7.add(mlp1);
            grupo7.add(mlp2);
            grupo7.add(mlp3);
            grupo7.add(mlp4);
            grupo7.add(mlp5);
            //grupo7.add(mlp6);
            grupo7.add(mlp7);
            criarGrupos(grupo7, "Grupo: Nodo 0, Nodo 1, Nodo 2, Nodo 3, Nodo 4, Nodo 5, Nodo 6, Nodo 7");

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
