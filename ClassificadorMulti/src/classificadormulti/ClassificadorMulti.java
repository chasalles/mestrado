/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classificadormulti;

import weka.classifiers.Evaluation;
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

    public static void main(String[] args) {
        try {
            DataSource sourceTest = new DataSource("C:\\Data\\DadosTratados\\arquivo_30.arff");
            Instances testData = sourceTest.getDataSet();
            testData.setClassIndex(testData.numAttributes() - 1); // Definir o índice do atributo classe

            NoMLP mlp0 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no0.arff", testData);
            SalvarObjeto so0 = new SalvarObjeto("C:\\Data\\MLPModelos\\no0.mol", mlp0.getMlp());

            NoMLP mlp1 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no1.arff", testData);
            SalvarObjeto so1 = new SalvarObjeto("C:\\Data\\MLPModelos\\no1.mol", mlp1.getMlp());

            NoMLP mlp2 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no2.arff", testData);
            SalvarObjeto so2 = new SalvarObjeto("C:\\Data\\MLPModelos\\no2.mol", mlp2.getMlp());

            NoMLP mlp3 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no3.arff", testData);
            SalvarObjeto so3 = new SalvarObjeto("C:\\Data\\MLPModelos\\no3.mol", mlp3.getMlp());

            NoMLP mlp4 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no4.arff", testData);
            SalvarObjeto so4 = new SalvarObjeto("C:\\Data\\MLPModelos\\no4.mol", mlp4.getMlp());

            NoMLP mlp5 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no5.arff", testData);
            SalvarObjeto so5 = new SalvarObjeto("C:\\Data\\MLPModelos\\no5.mol", mlp5.getMlp());

            NoMLP mlp6 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no6.arff", testData);
            SalvarObjeto so6 = new SalvarObjeto("C:\\Data\\MLPModelos\\no6.mol", mlp6.getMlp());

            NoMLP mlp7 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario1\\no7.arff", testData);
            SalvarObjeto so7 = new SalvarObjeto("C:\\Data\\MLPModelos\\no7.mol", mlp7.getMlp());
         
            // Inicializar o sistema de votação com a regra da soma
            Vote vote1 = new Vote();
            vote1.setCombinationRule(new SelectedTag(Vote.AVERAGE_RULE, Vote.TAGS_RULES));

            // Adicionar os classificadores individuais ao sistema de votação
            vote1.addPreBuiltClassifier(mlp0.getMlp());
            //vote1.addPreBuiltClassifier(mlp1.getMlp());
            vote1.addPreBuiltClassifier(mlp2.getMlp());
            vote1.addPreBuiltClassifier(mlp3.getMlp());
            vote1.addPreBuiltClassifier(mlp4.getMlp());
            vote1.addPreBuiltClassifier(mlp5.getMlp());
            vote1.addPreBuiltClassifier(mlp6.getMlp());
            vote1.addPreBuiltClassifier(mlp7.getMlp());

            Vote vote2 = new Vote();
            vote2.setCombinationRule(new SelectedTag(Vote.MAJORITY_VOTING_RULE, Vote.TAGS_RULES));

            // Adicionar os classificadores individuais ao sistema de votação
            vote2.addPreBuiltClassifier(mlp0.getMlp());
            //vote2.addPreBuiltClassifier(mlp1.getMlp());
            vote2.addPreBuiltClassifier(mlp2.getMlp());
            vote2.addPreBuiltClassifier(mlp3.getMlp());
            vote2.addPreBuiltClassifier(mlp4.getMlp());
            vote2.addPreBuiltClassifier(mlp5.getMlp());
            vote2.addPreBuiltClassifier(mlp6.getMlp());
            vote2.addPreBuiltClassifier(mlp7.getMlp());

            Vote vote3 = new Vote();
            vote3.setCombinationRule(new SelectedTag(Vote.PRODUCT_RULE, Vote.TAGS_RULES));

            // Adicionar os classificadores individuais ao sistema de votação
            vote3.addPreBuiltClassifier(mlp0.getMlp());
            //vote3.addPreBuiltClassifier(mlp1.getMlp());
            vote3.addPreBuiltClassifier(mlp2.getMlp());
            vote3.addPreBuiltClassifier(mlp3.getMlp());
            vote3.addPreBuiltClassifier(mlp4.getMlp());
            vote3.addPreBuiltClassifier(mlp5.getMlp());
            vote3.addPreBuiltClassifier(mlp6.getMlp());
            vote3.addPreBuiltClassifier(mlp7.getMlp());

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

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
