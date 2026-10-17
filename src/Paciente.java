public class Paciente {
    private String nombre;
    private double peso;
    private int edad;
    private String estado;
    private final double factorDosis;
    private final double pesoMaximo;
    private double dosisAcumulada;
    private Jaula jaula;

    public Paciente(
            String nombre,
            double peso,
            int edad,
            String estado,
            double factorDosis,
            double pesoMaximo) {
        if (!esNumeroValido(factorDosis)
                || factorDosis <= 0
                || !esNumeroValido(pesoMaximo)
                || pesoMaximo <= 0) {
            throw new IllegalArgumentException("Factor de dosis o peso maximo invalido");
        }

        this.factorDosis = factorDosis;
        this.pesoMaximo = pesoMaximo;
        this.dosisAcumulada = 0;
        this.jaula = null;

        if (!setNombre(nombre)
                || !setPeso(peso)
                || !setEdad(edad)
                || !setEstado(estado)) {
            throw new IllegalArgumentException("Datos de paciente invalidos");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        this.nombre = nombre;
        return true;
    }

    public double getPeso() {
        return peso;
    }

    public boolean setPeso(double peso) {
        if (!esNumeroValido(peso) || peso <= 0 || peso > pesoMaximo) {
            return false;
        }

        double nuevaDosisMaxima = peso * factorDosis;
        if (dosisAcumulada > 3 * nuevaDosisMaxima) {
            return false;
        }

        this.peso = peso;
        return true;
    }

    public int getEdad() {
        return edad;
    }

    public boolean setEdad(int edad) {
        if (edad < 0 || edad > 30) {
            return false;
        }
        this.edad = edad;
        return true;
    }

    public String getEstado() {
        return estado;
    }

    public boolean setEstado(String estado) {
        if (!estadoValido(estado)) {
            return false;
        }
        this.estado = estado;
        return true;
    }

    public double getFactorDosis() {
        return factorDosis;
    }

    public double getDosisAcumulada() {
        return dosisAcumulada;
    }

    public boolean setDosisAcumulada(double dosisAcumulada) {
        if (!esNumeroValido(dosisAcumulada)
                || dosisAcumulada < 0
                || dosisAcumulada > 3 * getDosisMaxima()) {
            return false;
        }
        this.dosisAcumulada = dosisAcumulada;
        return true;
    }

    public double getDosisMaxima() {
        return peso * factorDosis;
    }

    public Jaula getJaula() {
        return jaula;
    }

    boolean asignarJaula(Jaula jaula) {
        if (jaula == null || this.jaula != null) {
            return false;
        }
        this.jaula = jaula;
        return true;
    }

    boolean liberarJaula(Jaula jaula) {
        if (jaula == null || this.jaula != jaula) {
            return false;
        }
        this.jaula = null;
        return true;
    }

    private boolean estadoValido(String estado) {
        return "INGRESADO".equals(estado)
                || "CUARENTENA".equals(estado)
                || "EN_TRATAMIENTO".equals(estado);
    }

    private boolean esNumeroValido(double numero) {
        return !Double.isNaN(numero) && !Double.isInfinite(numero);
    }
}
