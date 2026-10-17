public class Perro extends Paciente {
    private String raza;
    private boolean vacunado;

    public Perro(String nombre, double peso, int edad, String raza, boolean vacunado) {
        super(nombre, peso, edad, "INGRESADO", 10, 80);

        if (!setRaza(raza)) {
            throw new IllegalArgumentException("Raza invalida");
        }

        this.vacunado = vacunado;
        if (!vacunado) {
            setEstado("CUARENTENA");
        }
    }

    public String getRaza() {
        return raza;
    }

    public boolean setRaza(String raza) {
        if (raza == null) {
            return false;
        }
        this.raza = raza;
        return true;
    }

    public boolean getVacunado() {
        return vacunado;
    }

    public boolean setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
        return true;
    }
}
