package org.alphacode.pacer.grupos;

import java.time.LocalDate;
import java.util.Date;

public class Sprint {
    private Integer sprint;
    private LocalDate data_inicio;
    private LocalDate data_fim;
    private Double pontos;
    private Integer id_grupo;
    private LocalDate fim_avaliacao;




    public Sprint(Integer sprint, LocalDate data_inicio, LocalDate data_fim, LocalDate fim_avaliacao) {
        this.sprint = sprint;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.fim_avaliacao = fim_avaliacao;
    }

    public Sprint(Integer sprint, Integer id, Double pontos) {
        this.sprint = sprint;
        this.id_grupo = id;
        this.pontos = pontos;
    }

    public Sprint(Integer sprint, Double pontos) {
        this.sprint = sprint;
        this.pontos = pontos;
    }

    public Integer getSprint() {
        return sprint;
    }

    public void setSprint(Integer sprint) {
        this.sprint = sprint;
    }

    public Double getPontos() {
        return pontos;
    }

    public void setPontos(Double pontos) {
        this.pontos = pontos;
    }

    public LocalDate getData_inicio() {
        return data_inicio;
    }

    public void setData_inicio(LocalDate data_inicio) {
        this.data_inicio = data_inicio;
    }

    public LocalDate getData_fim() {
        return data_fim;
    }

    public void setData_fim(LocalDate data_fim) {
        this.data_fim = data_fim;
    }

    public LocalDate getFim_avaliacao() {
        return fim_avaliacao;
    }

    public void setFim_avaliacao(LocalDate fim_avaliacao) {
        this.fim_avaliacao = fim_avaliacao;
    }

}
