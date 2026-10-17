public class AveExotica extends Paciente {
    private double envergadura;

    public AveExotica(String nombre, double peso, int edad, double envergadura) {
        super(nombre, peso, edad, "INGRESADO", 3, 15);

        if (!setEnvergadura(envergadura)) {
            throw new IllegalArgumentException("Envergadura invalida");
        }
    }

    public double getEnvergadura() {
        return envergadura;
    }

    public boolean setEnvergadura(double envergadura) {
        if (Double.isNaN(envergadura)
                || Double.isInfinite(envergadura)
                || envergadura <= 0) {
            return false;
        }
        this.envergadura = envergadura;
        return true;
    }
}
