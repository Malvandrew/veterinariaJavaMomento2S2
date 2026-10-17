public class Gato extends Paciente {
    private int nivelEstres;

    public Gato(String nombre, double peso, int edad, int nivelEstres) {
        super(nombre, peso, edad, "INGRESADO", 6, 15);

        if (!setNivelEstres(nivelEstres)) {
            throw new IllegalArgumentException("Nivel de estres invalido");
        }
    }

    public int getNivelEstres() {
        return nivelEstres;
    }

    public boolean setNivelEstres(int nivelEstres) {
        if (nivelEstres < 0 || nivelEstres > 10) {
            return false;
        }
        this.nivelEstres = nivelEstres;
        return true;
    }

    void aumentarEstresPorMedicacion() {
        nivelEstres = nivelEstres + 2;
        if (nivelEstres > 10) {
            nivelEstres = 10;
        }
    }
}
