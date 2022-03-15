package model;

public class Filial {
    private Integer id;
    private String nome;
    private String endereco;
    private boolean eh5Estrelas;

    public Filial(){
    }

    public Filial(Integer id, String nome, String endereco, boolean eh5Estrelas) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.eh5Estrelas = eh5Estrelas;
    }

    public Filial(String nome, String endereco, boolean eh5Estrelas) {
        this.nome = nome;
        this.endereco = endereco;
        this.eh5Estrelas = eh5Estrelas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isEh5Estrelas() {
        return eh5Estrelas;
    }

    public void setEh5Estrelas(boolean eh5Estrelas) {
        this.eh5Estrelas = eh5Estrelas;
    }
}
