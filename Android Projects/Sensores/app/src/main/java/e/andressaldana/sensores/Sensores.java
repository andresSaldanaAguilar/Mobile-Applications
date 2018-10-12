package e.andressaldana.sensores;

public class Sensores {
    private String titulo;
    private String informacion;
    private int icono;

    public Sensores(String titulo, String informacion, int icono) {
        this.titulo = titulo;
        this.informacion = informacion;
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getInformacion() {
        return informacion;
    }

    public int getIcono() {
        return icono;
    }
}

