/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

/**
 *
 * @author charl
 */
public class NoLabel {

    private String nome;
    private Double downstairs;
    private Double upstairs;
    private Double jogging;
    private Double sitting;
    private Double standing;
    private Double walking;

    public NoLabel(String nome, Double porcentagem) {
        this.nome = nome;
        this.downstairs = porcentagem;
        this.upstairs = porcentagem;
        this.jogging = porcentagem;
        this.sitting = porcentagem;
        this.standing = porcentagem;
        this.walking = porcentagem;
    }

    public void print() {
        System.out.println("Nome: " + nome);
        System.out.println("Downstairs: " + downstairs);
        System.out.println("Upstairs: " + upstairs);
        System.out.println("Jogging: " + jogging);
        System.out.println("Sitting: " + sitting);
        System.out.println("Standing: " + standing);
        System.out.println("Walking: " + walking);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getDownstairs() {
        return downstairs;
    }

    public void setDownstairs(Double downstairs) {
        this.downstairs = downstairs;
    }

    public Double getUpstairs() {
        return upstairs;
    }

    public void setUpstairs(Double upstairs) {
        this.upstairs = upstairs;
    }

    public Double getJogging() {
        return jogging;
    }

    public void setJogging(Double jogging) {
        this.jogging = jogging;
    }

    public Double getSitting() {
        return sitting;
    }

    public void setSitting(Double sitting) {
        this.sitting = sitting;
    }

    public Double getStanding() {
        return standing;
    }

    public void setStanding(Double standing) {
        this.standing = standing;
    }

    public Double getWalking() {
        return walking;
    }

    public void setWalking(Double walking) {
        this.walking = walking;
    }

    
}
