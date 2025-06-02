package frontend;

import backend.Item;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {

    public static Map<String, double[]>calcularPorcentajePorNivel(List<Item> items, List<Integer> respuestas) {
        Map<String, double[]> resumen = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String nivel = item.getNivelTaxonomico().name();
            int correcta = item.getIndiceRespuestaCorrecta();
            int respuestaUsuario = respuestas.get(i);

            resumen.putIfAbsent(nivel, new double[]{0, 0});
            resumen.get(nivel)[1]++; // total

            if (respuestaUsuario == correcta) {
                resumen.get(nivel)[0]++; // correctas
            }
        }

        return resumen;
    }

    public static Map<String, double[]> calcularPorcentajePorTipo(List<Item> items, List<Integer> respuestas) {
        Map<String, double[]> resumen = new HashMap<>();

        for (int i = 0; i < items.size(); i++) {
            Item item = items.get(i);
            String tipo = item.getTipo();
            int correcta = item.getIndiceRespuestaCorrecta();
            int respuestaUsuario = respuestas.get(i);

            resumen.putIfAbsent(tipo, new double[]{0, 0});
            resumen.get(tipo)[1]++; // total

            if (respuestaUsuario == correcta) {
                resumen.get(tipo)[0]++; // correctas
            }
        }

        return resumen;
    }
}
