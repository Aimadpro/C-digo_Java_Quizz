import static org.junit.Assert.*;
import org.junit.Test;
public class QuizTest {

    @Test
    public void testObtenirPreguntesF1() {
        String[] preguntes = Main.indicarPreguntasF1();
        assertNotNull(preguntes);
        assertEquals(10, preguntes.length);
    }
    @Test
    public void testPosiblesRespuestasIA() {
        Main main = new Main();
        String[] posiblesRespuestasIA = main.posiblesRespuestasIA();
        assertNotNull(posiblesRespuestasIA);
        assertEquals(10, posiblesRespuestasIA.length);
    }
    @Test
    public void testCalcularResultado() {
        Main main = new Main();
        Jugador jugador = new Jugador("Jugador");

        // Simulamos un escenario donde el jugador responde 3 preguntas correctamente de un total de 5
        int preguntasCorrectas = 3;
        int numPreguntas = 5;
        String colorVerde = "\u001B[32m";
        String colorRojo = "\u001B[31m";
        String resetColor = "\u001B[0m";
        String expectedMessage = colorVerde + "FELICIDADES HAS APROBADO" + resetColor;

        // Llamamos a la función que queremos probar
        main.calcularResultado(preguntasCorrectas, numPreguntas, colorVerde, colorRojo, resetColor, jugador);

        // Verificamos si el estado del jugador es el esperado para el escenario dado
        assertTrue(jugador.getPuntuacion() >= 5);
    }


    @Test
    public void testCalculPuntuacio() {
        Jugador jugador = new Jugador("Test");
        jugador.respuestaCorrecta();
        jugador.respuestaCorrecta();
        assertEquals(2, jugador.getPuntuacion());
    }
}
