/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datasplitter;

import telas.Principal;

/**
 *
 * @author Charles
 */
public class DataSplitter {

    public static void main(String[] args) {
        Principal principal = new Principal();
        principal.setVisible(true);
        
/*
        int quantidadeNos = 10;
        String inputFile = "C:\\Data\\treinamento.arff"; // Nome do arquivo ARFF de entrada
        String outputFile[] = new String[10];
        BufferedWriter[] writer = new BufferedWriter[10];
        // Nome do segundo arquivo de saída

        String splitAttribute = "class"; // Atributo usado para dividir os dados

        try {
            try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                for (int i = 0; i < quantidadeNos; i++) {
                    outputFile[i] = "C:\\Data\\Dados\\no" + i + ".arff";
                    writer[i] = new BufferedWriter(new FileWriter(outputFile[i]));
                }

                String line;
                int index = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("@attribute") || line.startsWith("@data") || line.startsWith("@relation")) {
                        for (int i = 0; i < quantidadeNos; i++) {
                            writer[i].write(line);
                            writer[i].newLine();
                        }
                    } else {
                        if (index == quantidadeNos) {
                            index = 0;
                        }
                        writer[index].newLine();
                        writer[index++].write(line);
                    }
                }
                for (int i = 0; i < quantidadeNos; i++) {
                    writer[i].close();
                }

                System.out.println("Arquivos de saída foram criados com sucesso.");
            }
        } catch (IOException e) {
            System.err.println("Ocorreu um erro ao processar o arquivo: " + e.getMessage());
        }
*/
    }

}
