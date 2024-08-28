/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package csvreader;

import java.util.ArrayList;
import weka.core.Attribute;

/**
 *
 * @author charl
 */
public class Treinamento {

    private ArrayList<String> caminho;
    private ArrayList<String> labels;
    private ArrayList<Attribute> atributos;

    public Treinamento() {
        inicializaCaminho();
        inicializaLabels();
        inicializaAtributos();
    }

    public ArrayList<String> getCaminho() {
        return caminho;
    }

    private void inicializaCaminho() {
        this.caminho = new ArrayList<>();
        this.caminho.add("dws_1");
        this.caminho.add("dws_2");
        this.caminho.add("dws_11");
        this.caminho.add("jog_9");
        this.caminho.add("jog_16");
        this.caminho.add("sit_5");
        this.caminho.add("sit_13");
        this.caminho.add("std_6");
        this.caminho.add("std_14");
        this.caminho.add("ups_3");
        this.caminho.add("ups_4");
        this.caminho.add("ups_12");
        this.caminho.add("wlk_7");
        this.caminho.add("wlk_8");
        this.caminho.add("wlk_15");
    }

    public ArrayList<String> getLabels() {
        return labels;
    }

    private void inicializaLabels() {
        this.labels = new ArrayList<>();
        this.labels.add("dws");
        this.labels.add("jog");
        this.labels.add("sit");
        this.labels.add("std");
        this.labels.add("ups");
        this.labels.add("wlk");
    }

    public ArrayList<Attribute> getAtributos() {
        return atributos;
    }

    private void inicializaAtributos() {
        this.atributos = new ArrayList<>();
        this.atributos.add(new Attribute("attitude.roll"));
        this.atributos.add(new Attribute("attitude.pitch"));
        this.atributos.add(new Attribute("attitude.yaw"));
        this.atributos.add(new Attribute("gravity.x"));
        this.atributos.add(new Attribute("gravity.y"));
        this.atributos.add(new Attribute("gravity.z"));
        this.atributos.add(new Attribute("rotationRate.x"));
        this.atributos.add(new Attribute("rotationRate.y"));
        this.atributos.add(new Attribute("rotationRate.z"));
        this.atributos.add(new Attribute("userAcceleration.x"));
        this.atributos.add(new Attribute("userAcceleration.y"));
        this.atributos.add(new Attribute("userAcceleration.z"));
        this.atributos.add(new Attribute("label", this.labels));
    }

    public String containsActivity(String activity) {
        if (activity.contains("dws")) {
            return "dws";
        }
        if (activity.contains("jog")) {
            return "jog";
        }
        if (activity.contains("sit")) {
            return "sit";
        }
        if (activity.contains("std")) {
            return "std";
        }
        if (activity.contains("ups")) {
            return "ups";
        }
        if (activity.contains("wlk")) {
            return "wlk";
        }

        return "ERRO";
    }
}
