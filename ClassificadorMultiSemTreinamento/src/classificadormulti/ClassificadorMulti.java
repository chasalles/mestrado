/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classificadormulti;

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

            // Inicializar o sistema de votação com a regra da soma
            Vote vote1 = tipoCombina(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            vote1.setCombinationRule(new SelectedTag(Vote.AVERAGE_RULE, Vote.TAGS_RULES));

            Vote vote2 = tipoCombina(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            vote2.setCombinationRule(new SelectedTag(Vote.MAJORITY_VOTING_RULE, Vote.TAGS_RULES));

            Vote vote3 = tipoCombina(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            vote3.setCombinationRule(new SelectedTag(Vote.PRODUCT_RULE, Vote.TAGS_RULES));

            // Avaliar o sistema de votação usando validação cruzada
            Evaluation eval1 = new Evaluation(testData);
            eval1.crossValidateModel(vote1, testData, 10, new Random(1)); // 10-fold cross-validation
            System.out.println("Regra da Média: " + eval1.pctCorrect() + "%");

            // Avaliar o sistema de votação usando validação cruzada
            Evaluation eval2 = new Evaluation(testData);
            eval2.crossValidateModel(vote2, testData, 10, new Random(1)); // 10-fold cross-validation
            System.out.println("Voto Majoritário: " + eval2.pctCorrect() + "%");

            // Avaliar o sistema de votação usando validação cruzada
            Evaluation eval3 = new Evaluation(testData);
            eval3.crossValidateModel(vote3, testData, 10, new Random(1)); // 10-fold cross-validation
            System.out.println("Regra do produto: " + eval3.pctCorrect() + "%");

            System.out.println("Regra da Soma: Nó com falha executado 20 vezes");

            double acuraciaSoma = 0;

            for (int i = 0; i < 20; i++) {
                acuraciaSoma += falhaSoma(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            }

            System.out.println("Acurácia Regra da Soma (falha): " + (acuraciaSoma / 20));

            System.out.println("Regra da Majoritario: Nó com falha executado 20 vezes");

            double acuraciaMajoritario = 0;

            for (int i = 0; i < 20; i++) {
                acuraciaMajoritario += falhaMajoritario(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            }

            System.out.println("Acurácia Regra da Majoritario (falha): " + (acuraciaMajoritario / 20));

            System.out.println("Regra da Produto: Nó com falha executado 20 vezes");

            double acuraciaProduto = 0;

            for (int i = 0; i < 20; i++) {
                acuraciaProduto += falhaProduto(mlp0, mlp1, mlp2, mlp3, mlp4, mlp5, mlp6, mlp7);
            }

            System.out.println("Acurácia Regra da Produto (falha): " + (acuraciaProduto / 20));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Vote tipoCombina(MultilayerPerceptron mlp0, MultilayerPerceptron mlp1, MultilayerPerceptron mlp2, MultilayerPerceptron mlp3, MultilayerPerceptron mlp4, MultilayerPerceptron mlp5, MultilayerPerceptron mlp6, MultilayerPerceptron mlp7) {
        Vote vote = new Vote();

        // Adicionar os classificadores individuais ao sistema de votação
        vote.addPreBuiltClassifier(mlp0);
        vote.addPreBuiltClassifier(mlp1);
        vote.addPreBuiltClassifier(mlp2);
        vote.addPreBuiltClassifier(mlp3);
        vote.addPreBuiltClassifier(mlp4);
        vote.addPreBuiltClassifier(mlp5);
        vote.addPreBuiltClassifier(mlp6);
        vote.addPreBuiltClassifier(mlp7);

        return vote;
    }

    public static double falhaSoma(MultilayerPerceptron mlp0, MultilayerPerceptron mlp1, MultilayerPerceptron mlp2, MultilayerPerceptron mlp3, MultilayerPerceptron mlp4, MultilayerPerceptron mlp5, MultilayerPerceptron mlp6, MultilayerPerceptron mlp7) throws Exception {
        Vote vote = new Vote();

        Random random = new Random();
        int aleatorio = random.nextInt(8);

        System.out.println("Nó excluído: " + aleatorio);

        // Adicionar os classificadores individuais ao sistema de votação
        if (aleatorio != 0) {
            vote.addPreBuiltClassifier(mlp0);
        }
        if (aleatorio != 1) {
            vote.addPreBuiltClassifier(mlp1);
        }
        if (aleatorio != 2) {
            vote.addPreBuiltClassifier(mlp2);
        }
        if (aleatorio != 3) {
            vote.addPreBuiltClassifier(mlp3);
        }
        if (aleatorio != 4) {
            vote.addPreBuiltClassifier(mlp4);
        }
        if (aleatorio != 5) {
            vote.addPreBuiltClassifier(mlp5);
        }
        if (aleatorio != 6) {
            vote.addPreBuiltClassifier(mlp6);
        }
        if (aleatorio != 7) {
            vote.addPreBuiltClassifier(mlp7);
        }

        vote.setCombinationRule(new SelectedTag(Vote.AVERAGE_RULE, Vote.TAGS_RULES));

        Evaluation eval = new Evaluation(testData);
        eval.crossValidateModel(vote, testData, 10, new Random(1)); // 10-fold cross-validation
        System.out.println("Soma: " + eval.pctCorrect() + "%");

        return eval.pctCorrect();
    }

    public static double falhaMajoritario(MultilayerPerceptron mlp0, MultilayerPerceptron mlp1, MultilayerPerceptron mlp2, MultilayerPerceptron mlp3, MultilayerPerceptron mlp4, MultilayerPerceptron mlp5, MultilayerPerceptron mlp6, MultilayerPerceptron mlp7) throws Exception {
        Vote vote = new Vote();

        Random random = new Random();
        int aleatorio = random.nextInt(8);

        System.out.println("Nó excluído: " + aleatorio);

        // Adicionar os classificadores individuais ao sistema de votação
        if (aleatorio != 0) {
            vote.addPreBuiltClassifier(mlp0);
        }
        if (aleatorio != 1) {
            vote.addPreBuiltClassifier(mlp1);
        }
        if (aleatorio != 2) {
            vote.addPreBuiltClassifier(mlp2);
        }
        if (aleatorio != 3) {
            vote.addPreBuiltClassifier(mlp3);
        }
        if (aleatorio != 4) {
            vote.addPreBuiltClassifier(mlp4);
        }
        if (aleatorio != 5) {
            vote.addPreBuiltClassifier(mlp5);
        }
        if (aleatorio != 6) {
            vote.addPreBuiltClassifier(mlp6);
        }
        if (aleatorio != 7) {
            vote.addPreBuiltClassifier(mlp7);
        }

        vote.setCombinationRule(new SelectedTag(Vote.MAJORITY_VOTING_RULE, Vote.TAGS_RULES));

        Evaluation eval = new Evaluation(testData);
        eval.crossValidateModel(vote, testData, 10, new Random(1)); // 10-fold cross-validation
        System.out.println("Majoritário: " + eval.pctCorrect() + "%");

        return eval.pctCorrect();
    }

    public static double falhaProduto(MultilayerPerceptron mlp0, MultilayerPerceptron mlp1, MultilayerPerceptron mlp2, MultilayerPerceptron mlp3, MultilayerPerceptron mlp4, MultilayerPerceptron mlp5, MultilayerPerceptron mlp6, MultilayerPerceptron mlp7) throws Exception {
        Vote vote = new Vote();

        Random random = new Random();
        int aleatorio = random.nextInt(8);

        System.out.println("Nó excluído: " + aleatorio);

        // Adicionar os classificadores individuais ao sistema de votação
        if (aleatorio != 0) {
            vote.addPreBuiltClassifier(mlp0);
        }
        if (aleatorio != 1) {
            vote.addPreBuiltClassifier(mlp1);
        }
        if (aleatorio != 2) {
            vote.addPreBuiltClassifier(mlp2);
        }
        if (aleatorio != 3) {
            vote.addPreBuiltClassifier(mlp3);
        }
        if (aleatorio != 4) {
            vote.addPreBuiltClassifier(mlp4);
        }
        if (aleatorio != 5) {
            vote.addPreBuiltClassifier(mlp5);
        }
        if (aleatorio != 6) {
            vote.addPreBuiltClassifier(mlp6);
        }
        if (aleatorio != 7) {
            vote.addPreBuiltClassifier(mlp7);
        }

        vote.setCombinationRule(new SelectedTag(Vote.PRODUCT_RULE, Vote.TAGS_RULES));

        Evaluation eval = new Evaluation(testData);
        eval.crossValidateModel(vote, testData, 10, new Random(1)); // 10-fold cross-validation
        System.out.println("Produto: " + eval.pctCorrect() + "%");

        return eval.pctCorrect();
    }
}
