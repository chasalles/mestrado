/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classificadormulti;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import weka.classifiers.functions.MultilayerPerceptron;

/**
 *
 * @author charl
 */
public class LerObjeto {

    // Ler o objeto a partir do arquivo
    public LerObjeto() {
    }

    public MultilayerPerceptron lerMLP(String diretorio) {
        try ( ObjectInputStream ois = new ObjectInputStream(new FileInputStream(diretorio))) {
            MultilayerPerceptron mlp = (MultilayerPerceptron) ois.readObject();
            return mlp;
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return null;
    }
}
