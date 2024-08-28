/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class Rede {

    private Integer numeroDeNos;
    private ArrayList<NoLabel> listaDeNos;
    private String filePath;
    private Integer numeroClasses;

    public Rede(Integer numeroDeNos, String string) {
        this.numeroDeNos = numeroDeNos;
        this.listaDeNos = new ArrayList<>();
        this.filePath = string;
        this.numeroClasses = 6;
    }

    public Integer getNumeroDeNos() {
        return numeroDeNos;
    }

    public void setNumeroDeNos(Integer numeroDeNos) {
        this.numeroDeNos = numeroDeNos;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<NoLabel> getListaDeNos() {
        return listaDeNos;
    }

    public void setListaDeNos(ArrayList<NoLabel> listaDeNos) {
        this.listaDeNos = listaDeNos;
    }

    public Integer getNumeroClasses() {
        return numeroClasses;
    }

    public void setNumeroClasses(Integer numeroClasses) {
        this.numeroClasses = numeroClasses;
    }
}
