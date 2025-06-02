TAREA 2 – PTEC102 – PARADIGMAS DE PROGRAMACIÓN
Sistema de Evaluación basado en la Taxonomía de Bloom

Autores: Maximiliano Ávila (trabajo individual)

---

📌 DESCRIPCIÓN GENERAL:

Este sistema permite cargar un archivo con preguntas clasificadas según la Taxonomía de Bloom (versión revisada). El usuario puede rendir una prueba en una interfaz gráfica con navegación entre preguntas y ver sus resultados al final, desglosados por nivel taxonómico y tipo de ítem.

---

🧠 FUNCIONALIDADES:

1. **Carga de ítems desde archivo**
   - Se puede seleccionar un archivo `.txt` con las preguntas.
   - Se valida el formato y se maneja cualquier error con mensajes descriptivos.

2. **Aplicación de prueba**
   - Se muestra una pregunta a la vez.
   - Se puede avanzar y retroceder manteniendo las respuestas seleccionadas.
   - Tipos soportados: Selección múltiple y Verdadero/Falso.

3. **Revisión de respuestas**
   - Al finalizar se muestra un resumen con:
     - Porcentaje de aciertos por nivel de Bloom.
     - Porcentaje de aciertos por tipo de ítem.
   - Se puede navegar por cada ítem para ver si fue correcto o incorrecto.

---

📂 ESTRUCTURA DE PAQUETES:

- `backend`: contiene la lógica del programa y los modelos (`Item`, `ControladorPrueba`, etc.).
- `frontend`: contiene la interfaz gráfica con ventanas (`MainWindow`, `TestWindow`, etc.).
- `backend.observer`: implementación del patrón observador.

---

📄 FORMATO DEL ARCHIVO DE ÍTEMS:

Cada línea representa una pregunta con el siguiente formato:

Enunciado,opcion1;opcion2;opcion3,indiceCorrecto,NIVEL_BLOOM,TIPO

Ejemplo:

¿Cuál es el valor de PI?,3.14;2.71;1.61,0,RECORDAR,SELECCION

- `indiceCorrecto` es el índice de la opción correcta (comenzando desde 0).
- `NIVEL_BLOOM` debe ser uno de: RECORDAR, ENTENDER, APLICAR, ANALIZAR, EVALUAR, CREAR.
- `TIPO` puede ser `SELECCION` o `VF` (verdadero/falso).

---

✅ SUPUESTOS:

- Cada pregunta tiene exactamente una respuesta correcta.
- Se permite cualquier cantidad de opciones (mínimo 2).
- El índice de respuesta debe estar dentro del rango de opciones.
- El archivo debe tener formato exacto; cualquier error mostrará un mensaje.
- No se permite repetir preguntas de un año anterior (no implementado en esta versión, se asume un solo uso).

---

▶️ INSTRUCCIONES DE EJECUCIÓN:

1. Abrir el proyecto en IntelliJ IDEA o cualquier IDE compatible con Java.
2. Ejecutar la clase `Main.java`.
3. Al iniciar, cargar un archivo de preguntas válido.
4. Presionar "Iniciar prueba".
5. Al finalizar, revisar las estadísticas y resultados.

---

📌 VERSIONES Y DEPENDENCIAS:

- Java 23.0.1
- Swing (JDK incorporado)
- Sin librerías externas
