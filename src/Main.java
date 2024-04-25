import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
public class Main {
    public static void main(String[] args) {
        // Se definen los colores ANSI para mejorar la presentacion en consola
        String colorVerde = "\u001B[32m";
        String colorRojo = "\u001B[31m";
        String resetColor = "\u001B[0m";

        // Declaración e inicialización de arrays y ArrayList

        String[] preguntasSobreF1;
        String[] preguntasSobreIA;
        String[] respuestasSobreF1;
        String[] respuestasSobreIA;
        String[] posiblesRespuestasF1;
        String[] posiblesRespuestasIA;
        ArrayList<Jugador>jugadores=new ArrayList<>();
        int resultado;
        int decisionCategoria;
        int decisionNumPreguntas;
        boolean decisionVolverAJugar;

        // Inicialización de los arrays con preguntas y respuestas sobre F1 e IA
        preguntasSobreF1 = indicarPreguntasF1();
        preguntasSobreIA = indicarPreguntasIA();
        respuestasSobreF1 = indicarRespuestasF1();
        respuestasSobreIA = indicarRespuestaIA();
        posiblesRespuestasF1 =posiblesRespuestasF1();
        posiblesRespuestasIA = posiblesRespuestasIA();
        do {


        String nombre;
        System.out.println("Bienvenido al mejor quizz del mundo" + "\n tendrás que responder que verdadero falso a las siguientes preguntas, indicanos tu alias");
        nombre = Teclat.llegirString();
        Jugador jugador = new Jugador(nombre);
        jugadores.add(jugador);
        // Elección de categoría y número de preguntas
            decisionCategoria = decisionCategoria();
        decisionNumPreguntas = decisionNumPreguntas();
        switch (decisionCategoria) {
            case 1:
                resultado= eleccionPeguntasAleatorias(preguntasSobreF1, decisionNumPreguntas, respuestasSobreF1, posiblesRespuestasF1, colorVerde, colorRojo, resetColor,jugador);
                calcularResultado(resultado, decisionNumPreguntas, colorVerde, colorRojo,resetColor, jugador);
                break;
            case 2:
                resultado = eleccionPeguntasAleatorias(preguntasSobreIA, decisionNumPreguntas, respuestasSobreIA, posiblesRespuestasIA, colorVerde, colorRojo, resetColor,jugador);
                calcularResultado(resultado, decisionNumPreguntas, colorVerde, colorRojo,resetColor, jugador);
                break;
        }


            // Mostrar clasificación de jugadores
                mostrarClaificacion(jugadores);
            // Pedir al jugador si desea volver a jugar
            System.out.println("Indica respondiendo true si quieres volver a jugar, si quieres salir coloca false");
                decisionVolverAJugar = Teclat.llegirBoolean();

        }while (decisionVolverAJugar);
    }

    // Método para obtener preguntas sobre Fórmula 1

    public static String[] indicarPreguntasF1() {
        String[] preguntasArrayF1 = new String[10];
       preguntasArrayF1= extraerInfoFicheros(0,10,preguntasArrayF1);


        return preguntasArrayF1;
    }

    // Método para obtener posibles respuestas sobre Fórmula 1
    private static String[] posiblesRespuestasF1(){
        String[] RespuestasF1 = new String[100];
    RespuestasF1  = extraerPosiblidades(22,RespuestasF1);

        return RespuestasF1;
    }

    // Método para obtener preguntas sobre Inteligencia Artificial
    private static String[] indicarPreguntasIA() {
        String[] PreguntasIA = new String[10];
        PreguntasIA = extraerInfoFicheros(11,21,PreguntasIA);

        return PreguntasIA;
    }
    // Método para obtener posibles respuestas sobre Inteligencia Artificial
    public static String[] posiblesRespuestasIA() {
        String[] PosiblidadesIA = new String[100];
        PosiblidadesIA = extraerPosiblidades(34,PosiblidadesIA);
        return PosiblidadesIA;
    }
    // Método para obtener respuestas correctas sobre Fórmula 1
    private static String[] indicarRespuestasF1() {
        String[] RespuestasF1 = new String[9];
      RespuestasF1 =  extraerRespuestas(45,RespuestasF1);

        return RespuestasF1;
    }

    // Método para obtener respuestas correctas sobre Inteligencia Artificial

    private static String[] indicarRespuestaIA() {
        String[] RespuestasIA = new String[9];
        RespuestasIA = extraerRespuestas(48,RespuestasIA);
        return RespuestasIA;
    }

    // Método para que el jugador elija la categoría de las preguntas (F1 o IA)
    private static int decisionCategoria() {
        int DecisionIAoF1;
        do {
        System.out.println("pero antes, elige si quieres que las preguntas sean de F1 (1) o de sobre IA (2)");
        DecisionIAoF1 = Teclat.llegirInt();
        }while (DecisionIAoF1!=1 && DecisionIAoF1!=2);
        return DecisionIAoF1;
    }

    // Método para que el jugador elija el número de preguntas que quiere responder
    private static int decisionNumPreguntas() {
        int DecisionNum;
        System.out.println("Tambien elige cuantas preguntas quieres responder");
        do {
            System.out.println("Elige un numero entre el 5 y el 10");
            DecisionNum = Teclat.llegirInt();
        } while (DecisionNum < 5 || DecisionNum > 10);
        return DecisionNum;
    }

    // Método para que el jugador responda preguntas aleatorias
    public static int eleccionPeguntasAleatorias(String[] Preguntas, int NumPreguntas, String[] RespuestaPreguntas, String[]Posiblidades, String colorVerde, String colorRojo, String resetColor, Jugador jugador) {
        int NumAleatorio;
        int RespuestaCorrecta = 0;
        int [] NumAleatorioRep= new int[NumPreguntas];
        String[] RespuestaElegida = new String[NumPreguntas];
        String[] PreguntasElegidas = new String[NumPreguntas];
        String[] PosiblidadElegida = new String[NumPreguntas];
        Arrays.fill(NumAleatorioRep, -1);
        // Iteración para mostrar y procesar preguntas
        for (int i = 0; i < NumPreguntas; i++) {
            boolean repetida=false;
            do {
                // Generar un número aleatorio para seleccionar una pregunta
                NumAleatorio = (int) (Math.random() * 10);
                // Verificar si la pregunta ya ha sido seleccionada
                repetida= preguntaRepetida(NumAleatorioRep,NumAleatorio);
            }while (repetida);
            NumAleatorioRep[i]=NumAleatorio;
            PreguntasElegidas[i] = Preguntas[NumAleatorio];
            PosiblidadElegida[i] = Posiblidades[NumAleatorio];
            System.out.println(PreguntasElegidas[i]);
            System.out.println(PosiblidadElegida[i]);
            do {
                System.out.println("Introduce A,B,C o D");
                RespuestaElegida[i] = Teclat.llegirString().toUpperCase();
            } while (!RespuestaElegida[i].equals("A") && !RespuestaElegida[i].equals("B") && !RespuestaElegida[i].equals("C") && !RespuestaElegida[i].equals("D"));
            if (RespuestaElegida[i].equals(RespuestaPreguntas[NumAleatorio])) {
                System.out.println(colorVerde+"RESPUESTA CORRECTA"+resetColor);
                RespuestaCorrecta++;


            }
            if (!RespuestaElegida[i].equals(RespuestaPreguntas[NumAleatorio])){
                System.out.println(colorRojo+"RESPUESTA INCORRECTA"+resetColor);
                System.out.println("La respuesta correcta es la "+RespuestaPreguntas[NumAleatorio]);
            }
        }
        return RespuestaCorrecta;
    }
    // Método para verificar si una pregunta ya ha sido seleccionada previamente
    private static boolean preguntaRepetida(int[] NumaleatorioA, int Numaleatorio){
        boolean repetida=false;
        for (int i = 0; i < NumaleatorioA.length; i++) {
            if (NumaleatorioA[i]==Numaleatorio){
                repetida=true;
            }
        }
        return repetida;
    }
    // Método para calcular y mostrar el resultado obtenido por el jugador
    public static void  calcularResultado (int preguntasCorrectas, int Numpreguntas, String colorverde, String colorRojo, String resetColor, Jugador jugador){
        System.out.println("Ahora sabrás la nota que has sacadop");
        float resultadoPorcentaje;
        System.out.println("Has respondido bien "+preguntasCorrectas+" preguntas de "+Numpreguntas+ " posibles");
        resultadoPorcentaje= ((float)preguntasCorrectas/(float)Numpreguntas*10);
        System.out.println("tu nota es "+resultadoPorcentaje);
        jugador.setPuntuacion((int) resultadoPorcentaje);
        if (resultadoPorcentaje>=5){
            System.out.println(colorverde+"FELICIDADES HAS APROBADO"+resetColor);
        }
        else {
            System.out.println(colorRojo+"HAS SUSPENDIDO, ESTUDIA Y VUELVELO A INTENTARLO"+resetColor);
        }
        guardarInformacion(jugador);

    }
    // Método para extraer la información de los ficheros
    private static String[] extraerInfoFicheros(int numInicio, int numFinal,String[] extraccion){
        String extraccionString = "src/resources/fichero.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(extraccionString))) {
            String line;
            int index = 0;

            if (numInicio!=0) {
                for (int i = 0; i < numInicio; i++) {
                    br.readLine();
                }
            }
                    while ((line = br.readLine()) != null && index < (numFinal - numInicio)) {
                        extraccion[index] = line;
                        index++;
                    }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("Archivo no encontrado: ");
        } catch (Exception e) {
            throw new RuntimeException("Error de E/S al leer el archivo: ");
        }

        return extraccion;
    }
    public static String[] extraerPosiblidades(int numInicio, String[]extraccion){
        String rutaArchivo = "src/resources/fichero.txt";

        try {

            BufferedReader br = new BufferedReader(new FileReader(rutaArchivo));
            String linea;
            int index = 0;
            if (numInicio!=0) {
                for (int i = 0; i < numInicio; i++) {
                    br.readLine();
                }
            }

            while ((linea = br.readLine()) != null) {
                String[] opciones = linea.split(",");

                String respuesta = String.join("\n", opciones);

                extraccion[index++] = respuesta;
            }

            br.close();
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return extraccion;
    }


    public static String[] extraerRespuestas(int numInicio, String[] extraccion) {
        String rutaArchivo = "src/resources/fichero.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            // Saltar líneas según el valor de numInicio
            if (numInicio != 0) {
                for (int i = 0; i < numInicio; i++) {
                    br.readLine();
                }
            }

            // Leer la línea deseada
            String linea = br.readLine();

            // Dividir la línea en un array de Strings utilizando ","
            extraccion = linea.split(",");
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return extraccion;
    }
    // Método para mostrar la clasificación de los jugadores
    private static void mostrarClaificacion(ArrayList<Jugador>jugadors){
        Collections.sort(jugadors);
        System.out.println("----- CLASIFICACIÓN -----");
        System.out.println("Nombre            Puntuación");
        System.out.println("---------------------------");
        for (int i = 0; i < jugadors.size(); i++) {
            System.out.printf("%-18s %d%n", jugadors.get(i).getNombre(), jugadors.get(i).getPuntuacion());;
        }
    }
    private static void guardarInformacion(Jugador jugador){
        String rutaArchivo = "src/resources/ficheroRespuestas.txt";

        try {
            // Crear un objeto FileWriter para escribir en el archivo
            FileWriter fw = new FileWriter(rutaArchivo, true);

            // Escribir texto en el archivo
            fw.write("\n"+"EL JUGADOR "+jugador.getNombre()+" Ha obtenido una puntuacion de: "+jugador.getPuntuacion() );


            fw.close();

            System.out.println("Texto añadido al archivo correctamente.");
        } catch (Exception e) {
            System.err.println("Error al añadir texto al archivo: " + e.getMessage());
        }
    }

}

