package com.example.helenybessa.criarcontato;

public class Pessoa {
    String nome, telefone, email, instagram, facebook, linkedin;

    Pessoa(String n, String t, String e, String i, String f, String l) {
        setNome(n);
        setEmail(e);
        setPhone(t);
        setInstagram(i);
        setFacebook(f);
        setLinkedin(l);
    } //fim construtor

    /*
        gets
    */

    public String getNome() {
        return this.nome;
    }

    public String getPhone() {
        return this.telefone;
    }

    public String getEmail() {
        return this.email;
    }

    public String getInstagram() { return this.instagram; }

    public String getFacebook() { return this.facebook; }

    public String getLinkedin() { return this.linkedin; }

    /*
        sets
    */
    private void setNome(String n) {
        this.nome = n;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String telefone) {
        this.telefone = telefone;
    }

    public void setInstagram(String instagram) { this.instagram = instagram; }

    public void setFacebook(String facebook) { this.facebook = facebook; }

    public void setLinkedin(String linkedin) { this.linkedin = linkedin; }
}//fim classe Pessoa