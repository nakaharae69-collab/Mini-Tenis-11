package ABP.RetroTenis.interfaz;

public class GestorIdioma {

    private int idioma;

    // textos
    private String[] opciones;
    private String textoTitulo;
    private String textoNombre;
    private String textoJugador;
    private String textoRonda;
    private String textoTiempo;
    private String textoPausa;

    public GestorIdioma(int idiomaInicial) {
        this.idioma = idiomaInicial;
        establecerIdioma(idiomaInicial);
    }

    public void establecerIdioma(int idioma) {
        this.idioma = idioma;

        if (idioma == 0) {
            opciones = new String[]{"Fácil", "Medio", "Difícil", "Salir"};
            textoTitulo = "MINI TENIS";
            textoNombre = "Introduce tu nombre:";
            textoJugador = "Jugador: ";
            textoRonda = "Ronda: ";
            textoTiempo = "Tiempo: ";
            textoPausa = "PAUSA";
        } else {
            opciones = new String[]{"Fàcil", "Mitjà", "Difícil", "Sortir"};
            textoTitulo = "MINI TENIS";
            textoNombre = "Introdueix el teu nom:";
            textoJugador = "Jugador: ";
            textoRonda = "Ronda: ";
            textoTiempo = "Temps: ";
            textoPausa = "PAUSA";
        }
    }

    // GETTERS

    public String[] getOpciones() {
        return opciones;
    }

    public String getTextoTitulo() {
        return textoTitulo;
    }

    public String getTextoNombre() {
        return textoNombre;
    }

    public String getTextoJugador() {
        return textoJugador;
    }

    public String getTextoRonda() {
        return textoRonda;
    }

    public String getTextoTiempo() {
        return textoTiempo;
    }

    public String getTextoPausa() {
        return textoPausa;
    }

    public int getIdioma() {
        return idioma;
    }
}