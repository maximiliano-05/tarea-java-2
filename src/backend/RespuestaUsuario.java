package backend;

public class RespuestaUsuario {
    private Item item;
    private int respuestaSeleccionada;

    public RespuestaUsuario(Item item) {
        this.item = item;
        this.respuestaSeleccionada = -1;
    }

    public Item getItem() {
        return item;
    }

    public int getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(int respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public boolean esCorrecta() {
        return respuestaSeleccionada == item.getIndiceRespuestaCorrecta();
    }
}
