package org.alphacode.pacer.grupos;

import java.util.Date;

public class Sprint {
    private Integer sprint;
    private Date data_inicio;
    private Date data_fim;
    private Double pontos;
    private Integer id_grupo;

    public Sprint(Integer sprint, Integer id, Double pontos){
        this.sprint = sprint;
        this.id_grupo = id;
        this.pontos = pontos;
    }

    public Sprint(Integer sprint, Double pontos){
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
}
