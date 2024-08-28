/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estruturas;

/**
 *
 * @author charl
 */
public class No {

    private String nome;
    private Integer porcentagem;
    private Boolean downstairs;
    private Boolean upstairs;
    private Boolean jogging;
    private Boolean sitting;
    private Boolean standing;
    private Boolean walking;

    public No(String nome, Integer porcentagem, Boolean downstairs, Boolean upstairs, Boolean jogging, Boolean sitting, Boolean standing, Boolean walking) {
        this.nome = nome;
        this.porcentagem = porcentagem;
        this.downstairs = downstairs;
        this.upstairs = upstairs;
        this.jogging = jogging;
        this.sitting = sitting;
        this.standing = standing;
        this.walking = walking;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getDownstairs() {
        return downstairs;
    }

    public void setDownstairs(Boolean downstairs) {
        this.downstairs = downstairs;
    }

    public Boolean getUpstairs() {
        return upstairs;
    }

    public void setUpstairs(Boolean upstairs) {
        this.upstairs = upstairs;
    }

    public Boolean getJogging() {
        return jogging;
    }

    public void setJogging(Boolean jogging) {
        this.jogging = jogging;
    }

    public Boolean getSitting() {
        return sitting;
    }

    public void setSitting(Boolean sitting) {
        this.sitting = sitting;
    }

    public Boolean getStanding() {
        return standing;
    }

    public void setStanding(Boolean standing) {
        this.standing = standing;
    }

    public Boolean getWalking() {
        return walking;
    }

    public void setWalking(Boolean walking) {
        this.walking = walking;
    }

    public Integer getPorcentagem() {
        return porcentagem;
    }

    public void setPorcentagem(Integer porcentagem) {
        this.porcentagem = porcentagem;
    }

    public void print(){
        System.out.println("Nome: " + nome);
        System.out.println("Porcentagem: " + porcentagem);
        System.out.println("Downstairs: " + downstairs);
        System.out.println("Upstairs: " + upstairs);
        System.out.println("Jogging: " + jogging);
        System.out.println("Sitting: " + sitting);
        System.out.println("Standing: " + standing);
        System.out.println("Walking: " + walking);
    }
}
