/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datasplitter;

import estruturas.Rede;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class Splitter {

    private int quantidadeNos;
    private String inputFile;
    private String outputFile[];
    private BufferedWriter[] writer;
    private final String cenario1;
    private final String cenario2;
    private final String cenario3;
    private final String cenario4;
    private int numeroNos;
    private float proporcao;
    private ArrayList<String> arquivoEntrada;
    private ArrayList<String> arquivoEmClasse[];
    private int numeroClasses;

    public Splitter(Rede rede) {
        this.numeroClasses = 6;
        this.arquivoEntrada = new ArrayList<>();
        this.arquivoEmClasse = new ArrayList[numeroClasses + 1];
        this.quantidadeNos = rede.getNumeroDeNos();
        this.inputFile = rede.getFilePath();
        this.outputFile = new String[quantidadeNos];
        this.writer = new BufferedWriter[quantidadeNos];
        this.cenario1 = "C:\\Data\\Dados\\Cenario1\\";
        this.cenario2 = "C:\\Data\\Dados\\Cenario2\\";
        this.cenario3 = "C:\\Data\\Dados\\Cenario3\\";
        this.cenario4 = "C:\\Data\\Dados\\Cenario4\\";
    }
    
    public Splitter(int quantidadeNos, String inputFile) {
        this.numeroClasses = 6;
        this.arquivoEntrada = new ArrayList<>();
        this.arquivoEmClasse = new ArrayList[numeroClasses + 1];
        this.quantidadeNos = quantidadeNos;
        this.inputFile = inputFile;
        this.outputFile = new String[quantidadeNos];
        this.writer = new BufferedWriter[quantidadeNos];
        this.cenario1 = "C:\\Data\\Dados\\Cenario1\\";
        this.cenario2 = "C:\\Data\\Dados\\Cenario2\\";
        this.cenario3 = "C:\\Data\\Dados\\Cenario3\\";
        this.cenario4 = "C:\\Data\\Dados\\Cenario4\\";
    }

    public void leituraArquivo() throws FileNotFoundException, IOException {
        try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;

            while ((line = reader.readLine()) != null) {
                this.arquivoEntrada.add(line);
            }

            reader.close();
        }
    }

    public void divideClasses() {
        for (int i = 0; i < arquivoEmClasse.length; i++) {
            arquivoEmClasse[i] = new ArrayList<>();
        }

        for (String string : arquivoEntrada) {
            if (string.startsWith("@attribute") || string.startsWith("@data") || string.startsWith("@relation")) {
                arquivoEmClasse[0].add(string);
            } else {
                if (string.endsWith("dws")) {
                    arquivoEmClasse[1].add(string);
                } else {
                    if (string.endsWith("jog")) {
                        arquivoEmClasse[2].add(string);
                    } else {
                        if (string.endsWith("sit")) {
                            arquivoEmClasse[3].add(string);
                        } else {
                            if (string.endsWith("std")) {
                                arquivoEmClasse[4].add(string);
                            } else {
                                if (string.endsWith("ups")) {
                                    arquivoEmClasse[5].add(string);
                                } else {
                                    if (string.endsWith("wlk")) {
                                        arquivoEmClasse[6].add(string);
                                    } else {
                                        System.out.println("Entrada não detectada: " + string);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (ArrayList<String> classe : arquivoEmClasse) {
            System.out.println("Número de registros: " + classe.size());
        }
    }

    public void criarArquivos(String cenario) throws IOException {
        for (int i = 0; i < quantidadeNos; i++) {
            outputFile[i] = cenario + "no" + i + ".arff";
            writer[i] = new BufferedWriter(new FileWriter(outputFile[i]));
        }
    }

    public void escreve(String line) throws IOException {
        for (int i = 0; i < quantidadeNos; i++) {
            writer[i].write(line);
            writer[i].newLine();
        }
    }

    public void fechaArquivos() throws IOException {
        for (int i = 0; i < quantidadeNos; i++) {
            writer[i].close();
        }
    }

    public void gerarCenario1() throws IOException {
        criarArquivos(cenario1);

        int index = 0;

        for (String string : arquivoEntrada) {
            if (string.startsWith("@attribute") || string.startsWith("@data") || string.startsWith("@relation")) {
                escreve(string);
            } else {
                writer[index].write(string);
                writer[index].newLine();

                index++;

                if (index == quantidadeNos) {
                    index = 0;
                }
            }
        }

        fechaArquivos();
    }

    public void gerarCenario2(Integer registrosMais, Float proporcaoRegistros) throws IOException {
        criarArquivos(cenario2);

        for (String string : arquivoEmClasse[0]) {
            escreve(string);
        }
        
        

        fechaArquivos();
    }

    // Revisão de código
    //
    //
    public void dadosCenario2(String line, BufferedReader reader) throws IOException {
        float soma_proporcao = proporcao;

        for (int i = 0; i < quantidadeNos; i++) {
            if (i < numeroNos) {
                while (soma_proporcao > 1) {
                    writer[i].write(line);
                    writer[i].newLine();
                    soma_proporcao--;

                    if ((line = reader.readLine()) != null) {

                    }
                }
            }
            writer[i].write(line);
            writer[i].newLine();

            soma_proporcao += proporcao;
        }
    }

    public void cenario1() { // Atributo usado para dividir os dados
        writer = new BufferedWriter[quantidadeNos];
        outputFile = new String[quantidadeNos];

        try {
            try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                for (int i = 0; i < quantidadeNos; i++) {
                    outputFile[i] = cenario1 + "no" + i + ".arff";
                    writer[i] = new BufferedWriter(new FileWriter(outputFile[i]));
                }

                String line;
                int index = 0;

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("@attribute") || line.startsWith("@data") || line.startsWith("@relation")) {
                        escreve(line);
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
    }

    public void cenario2(int n, float p) { // Atributo usado para dividir os dados
        writer = new BufferedWriter[quantidadeNos];
        outputFile = new String[quantidadeNos];
        this.numeroNos = n;
        this.proporcao = p;

        try {
            try ( BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
                for (int i = 0; i < quantidadeNos; i++) {
                    outputFile[i] = cenario2 + "no" + i + ".arff";
                    writer[i] = new BufferedWriter(new FileWriter(outputFile[i]));
                }

                String line;

                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("@attribute") || line.startsWith("@data") || line.startsWith("@relation")) {
                        escreve(line);
                    } else {
                        dadosCenario2(line, reader);
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
    }

    public int getQuantidadeNos() {
        return quantidadeNos;
    }

    public void setQuantidadeNos(int quantidadeNos) {
        this.quantidadeNos = quantidadeNos;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public String[] getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(String[] outputFile) {
        this.outputFile = outputFile;
    }

    public BufferedWriter[] getWriter() {
        return writer;
    }

    public void setWriter(BufferedWriter[] writer) {
        this.writer = writer;
    }
}
