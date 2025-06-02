package backend;

import java.util.List;


public class Item {
    private String enunciado;
    private List<String> opciones;
    private int indiceRespuestaCorrecta;
    private TaxoBloom nivelTaxonomico;
    private String tipo;

    public Item(String enunciado, List<String> opciones, int indiceRespuestaCorrecta, TaxoBloom nivelTaxonomico, String tipo) {
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.indiceRespuestaCorrecta = indiceRespuestaCorrecta;
        this.nivelTaxonomico = nivelTaxonomico;
        this.tipo = tipo;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public int getIndiceRespuestaCorrecta() {
        return indiceRespuestaCorrecta;
    }

    public TaxoBloom getNivelTaxonomico() {
        return nivelTaxonomico;
    }

    public String getTipo() {
        return tipo;
    }
    public int getRespuestaCorrecta() {
        return indiceRespuestaCorrecta;
    }
}

