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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class GerarCenario {

    private final ArrayList<String> arquivoEntrada;
    private final ArrayList<String> arquivoEmClasse[];
    private final Rede rede;
    private final String saida;
    private final Integer contador;
    private String inputFile;
    private String outputFile[];
    private BufferedWriter[] writer;

    public GerarCenario(Rede rede, Integer contador) {
        this.arquivoEntrada = new ArrayList<>();
        this.saida = "C:\\Data\\Dados\\Cenarios\\";
        this.rede = rede;
        this.inputFile = rede.getFilePath();
        this.outputFile = new String[rede.getNumeroDeNos()];
        this.arquivoEmClasse = new ArrayList[rede.getNumeroClasses() + 1];
        this.writer = new BufferedWriter[rede.getNumeroDeNos()];
        this.contador = contador;
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

    public void gerar() {
        try {
            criarArquivos();
            cabecalho();
            corpo();
            fechaArquivos();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    public void criarArquivos() throws IOException {
        String novoDiretorio = saida + "Cenario" + contador + "\\";

        Path diretorioPath = Paths.get(novoDiretorio);

        Files.createDirectories(diretorioPath);

        for (int i = 0; i < rede.getNumeroDeNos(); i++) {
            outputFile[i] = novoDiretorio + "no" + i + ".arff";
            writer[i] = new BufferedWriter(new FileWriter(outputFile[i]));
        }
    }

    public void cabecalho() throws IOException {
        for (String linha : arquivoEmClasse[0]) {
            for (int i = 0; i < rede.getNumeroDeNos(); i++) {
                writer[i].write(linha);
                writer[i].newLine();
            }
        }
    }

    public void corpo() throws IOException {
        for (int i = 1; i < arquivoEmClasse.length; i++) {
            ArrayList<String> arrayList = arquivoEmClasse[i];
            corpo2(arrayList, i);
        }

    }

    public void corpo2(ArrayList<String> arrayList, int classe) {
        int inicio = 0;
        int fim = 0;
        double proporcao = 0;

        for (int i = 0; i < rede.getListaDeNos().size(); i++) {
            switch (classe) {
                case 1 ->
                    proporcao = rede.getListaDeNos().get(i).getDownstairs();
                case 2 ->
                    proporcao = rede.getListaDeNos().get(i).getJogging();
                case 3 ->
                    proporcao = rede.getListaDeNos().get(i).getSitting();
                case 4 ->
                    proporcao = rede.getListaDeNos().get(i).getStanding();
                case 5 ->
                    proporcao = rede.getListaDeNos().get(i).getUpstairs();
                case 6 ->
                    proporcao = rede.getListaDeNos().get(i).getWalking();
                default -> {
                    System.out.println("Ação não encontrada!!!");
                }
            }

            double quantidade = proporcao * arrayList.size() / 100.0;
            fim += quantidade;
            
            
            System.out.println("No: " + i);
            System.out.println("Classe: " + classe);
            System.out.println("Proporção: " + proporcao);
            System.out.println("Fim: " + fim);

            corpo3(arrayList, inicio, fim, i);

            inicio = fim + 1;
        }
    }

    public void corpo3(ArrayList<String> arrayList, int inicio, int fim, int no) {
        if (fim > (arrayList.size() - 1)) {
            fim = arrayList.size() - 1;
        }

        System.out.println("No escrevendo: " + no);
        
        for (int i = inicio; i <= fim; i++) {
            try {
                writer[no].write(arrayList.get(i));
                writer[no].newLine();
            } catch (IOException ex) {
                System.out.println(ex);
            }

        }
    }

    public void fechaArquivos() throws IOException {
        for (int i = 0; i < rede.getNumeroDeNos(); i++) {
            writer[i].close();
        }
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
