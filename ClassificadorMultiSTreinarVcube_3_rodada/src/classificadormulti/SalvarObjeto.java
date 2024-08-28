/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classificadormulti;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import weka.classifiers.functions.MultilayerPerceptron;

/**
 *
 * @author charl
 */
public class SalvarObjeto {

    private final FileOutputStream arquivo;
    private final ObjectOutputStream out;

    public SalvarObjeto(String nomeArquivo, MultilayerPerceptron mlp) throws FileNotFoundException, IOException {
        this.arquivo = new FileOutputStream(nomeArquivo);
        this.out = new ObjectOutputStream(arquivo);
        
        // Grave o objeto no arquivo
        out.writeObject(mlp);

        // Feche o fluxo
        out.close();
        arquivo.close();
    }

}
