package ABP.RetroTenis.interfaz;

public class GestorIdioma {

    private int idioma;

    private String[] opciones;
    private String[] textos;

    public GestorIdioma(int idioma) {
        setIdioma(idioma);
    }

    public void setIdioma(int idioma) {
        this.idioma = idioma;

        // Idioma castella
        if (idioma == 0) {
            opciones = new String[]{"Fácil", "Medio", "Difícil", "Salir"};

            textos = new String[]{
                "MINI TENIS",          // titulo
                "Introduce tu nombre:", // nom
                "Jugador: ",           // jugador
                "Ronda: ",             // ronda
                "Tiempo: ",            // temps
                "PAUSA"                // pausa
            };

        } 
        //Idioma catala
        else {
            opciones = new String[]{"Fàcil", "Mitjà", "Difícil", "Sortir"};

            textos = new String[]{
                "MINI TENIS",
                "Introdueix el teu nom:",
                "Jugador: ",
                "Ronda: ",
                "Temps: ",
                "PAUSA"
            };
        }
    }

    // getters

    public String[] getOpciones() {
        return opciones;
    }

    public String getTitulo() {
        return textos[0];
    }

    public String getNombre() {
        return textos[1];
    }

    public String getJugador() {
        return textos[2];
    }

    public String getRonda() {
        return textos[3];
    }

    public String getTiempo() {
        return textos[4];
    }

    public String getPausa() {
        return textos[5];
    }

    public int getIdioma() {
        return idioma;
    }
}