/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dividetreinamentoteste;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;

/**
 *
 * @author charl
 */
public class DivideTreinamentoTeste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            // Carregar o arquivo de entrada
            File inputFile = new File("C:\\Data\\treinamento.arff");
            ArrayList<String> lines;

            // Ler linhas do arquivo e armazená-las em uma lista
            try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                // Ler linhas do arquivo e armazená-las em uma lista
                lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
            }

            // Criar novos arquivos para os conjuntos
            File file70 = new File("C:\\Data\\DadosTratados\\arquivo_70.arff");
            File file30 = new File("C:\\Data\\DadosTratados\\arquivo_30.arff");

            int cont = 0;

            try ( BufferedWriter writer70 = new BufferedWriter(new FileWriter(file70));  BufferedWriter writer30 = new BufferedWriter(new FileWriter(file30))) {

                for (String string : lines) {
                    if (string.startsWith("@attribute") || string.startsWith("@data") || string.startsWith("@relation")) {
                        writer70.write(string);
                        writer70.newLine();
                        writer30.write(string);
                        writer30.newLine();
                    } else {
                        if (string.endsWith("dws") || string.endsWith("jog") || string.endsWith("sit") || string.endsWith("std") || string.endsWith("ups") || string.endsWith("wlk")) {
                            if (cont % 10 < 7) {
                                writer70.write(string);
                                writer70.newLine();
                            } else {
                                writer30.write(string);
                                writer30.newLine();
                            }
                        }
                    }
                    cont++;
                }

            }

            System.out.println("Divisão concluída com sucesso: " + cont);
        } catch (IOException ex) {
            Logger.getLogger(DivideTreinamentoTeste.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
