/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package csvreader;

/**
 *
 * @author charl
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class CSVReader {

    public static String[] aumentarArray(String[] aux, String valor) {
        String[] vetorString = new String[aux.length + 1];

        // Copiar elementos do vetor original para o novo vetor
        System.arraycopy(aux, 0, vetorString, 0, aux.length);

        // Inserir novo valor no final do novo vetor
        vetorString[aux.length] = valor;

        return vetorString;
    }

    public static void main(String[] args) {
        Treinamento treinamento = new Treinamento();

        String csvFile;
        String arffFile = "C:\\Data\\treinamento.arff";
        String line;
        String csvSeparator = ",";
        String relationName = "activity";

        // Criação do objeto Instances
        Instances data = new Instances(relationName, treinamento.getAtributos(), 0);

        for (String path : treinamento.getCaminho()) {
            String diretorio = "C:\\Data\\MotionSense\\" + path;

            for (int i = 1; i <= 24; i++) {
                csvFile = diretorio + "\\sub_" + i + ".csv";

                try ( BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
                    // Leitura do cabeçalho do CSV
                    String header = br.readLine();

                    System.out.println(csvFile);
                    System.out.println(header);
                    /*
                    // String[] aux = header.split(csvSeparator);
                    String[] attributeNames = aumentarArray(header.split(csvSeparator), pasta.containsActivity(path));

                    // Criação dos atributos
                    // Attribute[] attributes = new Attribute[attributeNames.length];
                    ArrayList<Attribute> attributes = new ArrayList<>();

                    for (int j = 1; j < attributeNames.length - 1; j++) {
                        attributes.add(new Attribute(attributeNames[j]));
                    }

                    attributes.add(new Attribute(attributeNames[attributeNames.length - 1], "label"));
                     */
                    // Criação do objeto Instances
                    // Instances data = new Instances(relationName, attributes, 0);
                    // Definindo o atributo classe (opcional)
                    // Attribute classAttribute = new Attribute("classe");
                    // data.setClass(classAttribute);
                    // Leitura das linhas do CSV
                    while ((line = br.readLine()) != null) {
                        //String[] values = line.split(csvSeparator);
                        //String[] values = aumentarArray(line.split(csvSeparator), "dws");

                        String[] values = line.split(csvSeparator);

                        for (int j = 1; j < values.length; j++) {
                            values[j - 1] = values[j];
                        }
                        values[values.length - 1] = treinamento.containsActivity(path);

                        Instance instance = new DenseInstance(treinamento.getAtributos().size());

                        // Preenchimento da instância com os valores
                        for (int j = 0; j < values.length - 1; j++) {
                            instance.setValue(treinamento.getAtributos().get(j), Double.parseDouble(values[j]));
                        }                       
                        instance.setValue(treinamento.getAtributos().get(values.length - 1), values[values.length - 1]);
                        
                        // Adição da instância aos dados
                        data.add(instance);
                    }

                    // Salvando os dados em um arquivo ARFF
                    try ( BufferedWriter writer = new BufferedWriter(new FileWriter(arffFile))) {
                        writer.write(data.toString());
                    }

                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
    }
}
