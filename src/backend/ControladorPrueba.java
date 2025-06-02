package backend;

import backend.observer.Observador;
import backend.observer.Sujeto;

import java.util.ArrayList;
import java.util.List;

public class ControladorPrueba implements Sujeto {
    private List<Item> items = new ArrayList<>();
    private List<Integer> respuestasUsuario = new ArrayList<>();
    private List<Observador> observadores = new ArrayList<>();

    public ControladorPrueba() {
    }

    public static ControladorPrueba createControladorPrueba() {
        return new ControladorPrueba();
    }

    public void cargarItems(List<Item> nuevosItems) {
        this.items = nuevosItems;
        this.respuestasUsuario.clear();
        for (int i = 0; i < nuevosItems.size(); i++) {
            respuestasUsuario.add(-1);
        }
        notificarObservadores();
    }

    public List<Item> getItems() {
        return items;
    }

    public List<Integer> getRespuestasUsuario() {
        return respuestasUsuario;
    }

    public void responder(int indice, int respuesta) {
        respuestasUsuario.set(indice, respuesta);
        notificarObservadores();
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    public int getCantidadItems() {
        return items.size();
    }

    public int getTiempoEstimadoTotal() {
        return items.size() * 60; // 1 minuto por ítem
    }
}
