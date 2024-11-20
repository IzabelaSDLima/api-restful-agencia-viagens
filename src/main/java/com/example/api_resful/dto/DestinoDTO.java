package com.example.api_resful.dto;

public class DestinoDTO {

    private Long id;
    private String nome;
    private String localizacao;
    private String descricao;
    private double mediaAvaliacao;

    public DestinoDTO() {
    }

    public DestinoDTO(Long id, String nome, String localizacao, String descricao, double mediaAvaliacao) {
        this.id = id;
        this.nome = nome;
        this.localizacao = localizacao;
        this.descricao = descricao;
        this.mediaAvaliacao = mediaAvaliacao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}
