package backend;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemLoader {

    public static List<Item> cargarDesdeArchivo(File archivo) throws Exception {
        List<Item> items = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;

        while ((linea = br.readLine()) != null) {
            if (linea.trim().isEmpty()) continue;

            String[] partes = linea.split(",", -1);
            if (partes.length != 5) throw new Exception("Formato inválido en línea: " + linea);

            String enunciado = partes[0].trim();
            List<String> opciones = Arrays.asList(partes[1].split(";"));
            int indiceCorrecto = Integer.parseInt(partes[2].trim());
            TaxoBloom nivel = TaxoBloom.valueOf(partes[3].trim().toUpperCase());
            String tipo = partes[4].trim();

            items.add(new Item(enunciado, opciones, indiceCorrecto, nivel, tipo));
        }

        br.close();
        return items;
    }
}
