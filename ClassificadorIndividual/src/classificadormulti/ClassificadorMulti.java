/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package classificadormulti;

import weka.core.Instances;
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

            NoMLP mlp0 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no0.arff", testData);
            SalvarObjeto so0 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no0.mol", mlp0.getMlp());

            NoMLP mlp1 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no1.arff", testData);
            SalvarObjeto so1 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no1.mol", mlp1.getMlp());

            NoMLP mlp2 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no2.arff", testData);
            SalvarObjeto so2 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no2.mol", mlp2.getMlp());

            NoMLP mlp3 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no3.arff", testData);
            SalvarObjeto so3 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no3.mol", mlp3.getMlp());

            NoMLP mlp4 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no4.arff", testData);
            SalvarObjeto so4 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no4.mol", mlp4.getMlp());

            NoMLP mlp5 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no5.arff", testData);
            SalvarObjeto so5 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no5.mol", mlp5.getMlp());

            NoMLP mlp6 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no6.arff", testData);
            SalvarObjeto so6 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no6.mol", mlp6.getMlp());

            NoMLP mlp7 = new NoMLP("C:\\Data\\Dados\\Cenarios\\Cenario\\no7.arff", testData);
            SalvarObjeto so7 = new SalvarObjeto("C:\\Data\\MLPIndividual\\no7.mol", mlp7.getMlp()); 
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
