public class Jaula {
    private int numero;
    private String tamano;
    private String zona;
    private Paciente ocupante;

    public Jaula(int numero, String tamano, String zona) {
        this.ocupante = null;
        if (!setNumero(numero) || !setTamano(tamano) || !setZona(zona)) {
            throw new IllegalArgumentException("Datos de jaula invalidos");
        }
    }

    public int getNumero() {
        return numero;
    }

    public boolean setNumero(int numero) {
        if (numero <= 0) {
            return false;
        }
        this.numero = numero;
        return true;
    }

    public String getTamano() {
        return tamano;
    }

    public boolean setTamano(String tamano) {
        if (!"S".equals(tamano) && !"M".equals(tamano) && !"L".equals(tamano)) {
            return false;
        }
        this.tamano = tamano;
        return true;
    }

    public String getZona() {
        return zona;
    }

    public boolean setZona(String zona) {
        if (!"A".equals(zona) && !"B".equals(zona)) {
            return false;
        }
        this.zona = zona;
        return true;
    }

    public Paciente getOcupante() {
        return ocupante;
    }

    public boolean estaLibre() {
        return ocupante == null;
    }

    public boolean asignarPaciente(Paciente paciente) {
        if (paciente == null || ocupante != null || paciente.getJaula() != null) {
            return false;
        }

        if (!paciente.asignarJaula(this)) {
            return false;
        }

        this.ocupante = paciente;
        return true;
    }

    public boolean liberar() {
        if (ocupante == null) {
            return false;
        }

        if (!ocupante.liberarJaula(this)) {
            return false;
        }

        ocupante = null;
        return true;
    }
}
