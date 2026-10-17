import java.util.Locale;

public class Hospital {
    private Paciente[] pacientes;
    private int cantidadPacientes;
    private Jaula[] jaulas;

    public Hospital() {
        pacientes = new Paciente[10];
        jaulas = new Jaula[6];
        cargarDatosIniciales();
    }

    public void cargarDatosIniciales() {
        pacientes = new Paciente[10];
        jaulas = new Jaula[6];
        cantidadPacientes = 0;

        pacientes[cantidadPacientes] =
                new Perro("Rocky", 30.0, 4, "labrador", true);
        cantidadPacientes++;

        pacientes[cantidadPacientes] = new Gato("Misifu", 4.0, 3, 0);
        cantidadPacientes++;

        pacientes[cantidadPacientes] =
                new AveExotica("Kiwi", 0.8, 2, 70.0);
        cantidadPacientes++;

        jaulas[0] = new Jaula(1, "S", "A");
        jaulas[1] = new Jaula(2, "M", "A");
        jaulas[2] = new Jaula(3, "M", "A");
        jaulas[3] = new Jaula(4, "L", "A");
        jaulas[4] = new Jaula(5, "S", "B");
        jaulas[5] = new Jaula(6, "M", "B");
    }

    public String ingresarPerro(
            String nombre,
            double peso,
            int edad,
            String raza,
            String respuestaVacunacion) {
        if (!datosPerroValidos(nombre, peso, edad, raza, respuestaVacunacion)) {
            return "ERROR: datos invalidos";
        }

        String error = validarRegistro(nombre);
        if (error != null) {
            return error;
        }

        boolean vacunado = respuestaVacunacion.equalsIgnoreCase("si");
        Perro perro = new Perro(nombre, peso, edad, raza, vacunado);
        pacientes[cantidadPacientes] = perro;
        cantidadPacientes++;

        if (vacunado) {
            return "OK: " + nombre + " ingresado";
        }
        return "OK: " + nombre + " ingresado en CUARENTENA";
    }

    public String ingresarGato(String nombre, double peso, int edad) {
        if (!datosComunesValidos(nombre, peso, edad, 15)) {
            return "ERROR: datos invalidos";
        }

        String error = validarRegistro(nombre);
        if (error != null) {
            return error;
        }

        Gato gato = new Gato(nombre, peso, edad, 0);
        pacientes[cantidadPacientes] = gato;
        cantidadPacientes++;
        return "OK: " + nombre + " ingresado";
    }

    public String ingresarAve(
            String nombre,
            double peso,
            int edad,
            double envergadura) {
        if (!datosComunesValidos(nombre, peso, edad, 15)
                || Double.isNaN(envergadura)
                || Double.isInfinite(envergadura)
                || envergadura <= 0) {
            return "ERROR: datos invalidos";
        }

        String error = validarRegistro(nombre);
        if (error != null) {
            return error;
        }

        AveExotica ave = new AveExotica(nombre, peso, edad, envergadura);
        pacientes[cantidadPacientes] = ave;
        cantidadPacientes++;
        return "OK: " + nombre + " ingresado";
    }

    private boolean datosPerroValidos(
            String nombre,
            double peso,
            int edad,
            String raza,
            String respuestaVacunacion) {
        if (!datosComunesValidos(nombre, peso, edad, 80)) {
            return false;
        }
        if (raza == null || respuestaVacunacion == null) {
            return false;
        }
        if (!respuestaVacunacion.equalsIgnoreCase("si")
                && !respuestaVacunacion.equalsIgnoreCase("no")) {
            return false;
        }
        return true;
    }

    private boolean datosComunesValidos(
            String nombre,
            double peso,
            int edad,
            double pesoMaximo) {
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }
        if (Double.isNaN(peso) || Double.isInfinite(peso)) {
            return false;
        }
        if (peso <= 0 || peso > pesoMaximo) {
            return false;
        }
        if (edad < 0 || edad > 30) {
            return false;
        }
        return true;
    }

    private String validarRegistro(String nombre) {
        if (buscarPaciente(nombre) != null) {
            return "ERROR: nombre duplicado";
        }
        if (cantidadPacientes >= pacientes.length) {
            return "ERROR: hospital lleno";
        }
        return null;
    }

    public String vacunar(String nombre) {
        Paciente paciente = buscarPaciente(nombre);
        if (paciente == null) {
            return "ERROR: paciente no encontrado";
        }

        if (!(paciente instanceof Perro)) {
            return "ERROR: solo aplica a perros";
        }

        Perro perro = (Perro) paciente;
        if (perro.getVacunado()) {
            return "ERROR: ya vacunado";
        }

        perro.setVacunado(true);
        perro.setEstado("INGRESADO");
        return "OK: " + perro.getNombre() + " vacunado";
    }

    public String asignar(String nombre) {
        Paciente paciente = buscarPaciente(nombre);
        if (paciente == null) {
            return "ERROR: paciente no encontrado";
        }

        if (paciente.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }

        if (paciente.getJaula() != null) {
            return "ERROR: paciente ya tiene jaula";
        }

        String tamanoRequerido = obtenerTamanoRequerido(paciente);
        String zonaRequerida = obtenerZonaRequerida(paciente);

        for (int i = 0; i < jaulas.length; i++) {
            Jaula jaula = jaulas[i];
            if (jaula.estaLibre()
                    && jaula.getZona().equals(zonaRequerida)
                    && tamanoCompatible(jaula.getTamano(), tamanoRequerido)) {
                boolean asignacionExitosa = jaula.asignarPaciente(paciente);
                if (asignacionExitosa) {
                    return "OK: " + paciente.getNombre()
                            + " asignado a jaula " + jaula.getNumero();
                }
            }
        }

        return "ERROR: no hay jaula compatible";
    }

    private String obtenerTamanoRequerido(Paciente paciente) {
        String tamanoRequerido;

        if (paciente.getPeso() < 5) {
            tamanoRequerido = "S";
        } else if (paciente.getPeso() <= 25) {
            tamanoRequerido = "M";
        } else {
            tamanoRequerido = "L";
        }

        if (paciente instanceof AveExotica) {
            AveExotica ave = (AveExotica) paciente;
            if (ave.getEnvergadura() > 60 && tamanoRequerido.equals("S")) {
                tamanoRequerido = "M";
            }
        }

        return tamanoRequerido;
    }

    private String obtenerZonaRequerida(Paciente paciente) {
        if (paciente instanceof AveExotica) {
            return "B";
        }
        return "A";
    }

    private boolean tamanoCompatible(String tamanoJaula, String tamanoRequerido) {
        if (tamanoRequerido.equals("S")) {
            return true;
        }
        if (tamanoRequerido.equals("M")) {
            return tamanoJaula.equals("M") || tamanoJaula.equals("L");
        }
        return tamanoJaula.equals("L");
    }

    public String medicar(String nombre, String textoMiligramos) {
        Paciente paciente = buscarPaciente(nombre);
        if (paciente == null) {
            return "ERROR: paciente no encontrado";
        }

        double miligramos;
        try {
            miligramos = Double.parseDouble(textoMiligramos);
        } catch (NumberFormatException excepcion) {
            return "ERROR: datos invalidos";
        }

        if (Double.isNaN(miligramos) || Double.isInfinite(miligramos)
                || miligramos <= 0) {
            return "ERROR: datos invalidos";
        }

        if (paciente.getEstado().equals("CUARENTENA")) {
            return "ERROR: paciente en cuarentena";
        }

        if (paciente.getJaula() == null) {
            return "ERROR: paciente sin jaula";
        }

        if (paciente instanceof Gato) {
            Gato gato = (Gato) paciente;
            if (gato.getNivelEstres() >= 8) {
                return "ERROR: gato con estres alto";
            }
        }

        double dosisMaxima = paciente.getDosisMaxima();
        if (miligramos > dosisMaxima) {
            return "ERROR: dosis excede el maximo";
        }

        double nuevaDosisAcumulada = paciente.getDosisAcumulada() + miligramos;
        if (nuevaDosisAcumulada > 3 * dosisMaxima) {
            return "ERROR: tope acumulado superado";
        }

        paciente.setDosisAcumulada(nuevaDosisAcumulada);
        paciente.setEstado("EN_TRATAMIENTO");

        if (paciente instanceof Gato) {
            Gato gato = (Gato) paciente;
            gato.aumentarEstresPorMedicacion();
        }

        return "OK: " + paciente.getNombre()
                + " medicado con " + textoMiligramos + " mg";
    }

    public String alta(String nombre) {
        Paciente paciente = buscarPaciente(nombre);
        if (paciente == null) {
            return "ERROR: paciente no encontrado";
        }

        if (!paciente.getEstado().equals("EN_TRATAMIENTO")) {
            return "ERROR: paciente no apto para alta";
        }

        Jaula jaula = paciente.getJaula();
        if (jaula != null && !jaula.liberar()) {
            throw new IllegalStateException("No se pudo liberar la jaula");
        }

        int indice = indicePaciente(nombre);
        for (int i = indice; i < cantidadPacientes - 1; i++) {
            pacientes[i] = pacientes[i + 1];
        }

        cantidadPacientes--;
        pacientes[cantidadPacientes] = null;
        return "OK: " + paciente.getNombre() + " dado de alta";
    }

    public String reporte() {
        StringBuilder textoReporte = new StringBuilder();
        textoReporte.append("--- REPORTE ---\n");
        textoReporte.append("Pacientes: ");
        textoReporte.append(cantidadPacientes);
        textoReporte.append("/10\n");

        for (int i = 0; i < cantidadPacientes; i++) {
            Paciente paciente = pacientes[i];
            String numeroJaula = "-";

            if (paciente.getJaula() != null) {
                numeroJaula = Integer.toString(paciente.getJaula().getNumero());
            }

            textoReporte.append(paciente.getNombre());
            textoReporte.append(" | ");
            textoReporte.append(obtenerTipo(paciente));
            textoReporte.append(" | ");
            textoReporte.append(String.format(Locale.US, "%.1f", paciente.getPeso()));
            textoReporte.append(" kg | ");
            textoReporte.append(paciente.getEstado());
            textoReporte.append(" | jaula: ");
            textoReporte.append(numeroJaula);
            textoReporte.append("\n");
        }

        int jaulasLibres = contarJaulasLibres();
        textoReporte.append("Jaulas libres: ");
        textoReporte.append(jaulasLibres);
        return textoReporte.toString();
    }

    private String obtenerTipo(Paciente paciente) {
        if (paciente instanceof Perro) {
            return "Perro";
        }
        if (paciente instanceof Gato) {
            return "Gato";
        }
        if (paciente instanceof AveExotica) {
            return "AveExotica";
        }

        throw new IllegalStateException("Tipo de paciente no reconocido");
    }

    private int contarJaulasLibres() {
        int jaulasLibres = 0;
        for (int i = 0; i < jaulas.length; i++) {
            if (jaulas[i].estaLibre()) {
                jaulasLibres++;
            }
        }
        return jaulasLibres;
    }

    private Paciente buscarPaciente(String nombre) {
        if (nombre == null) {
            return null;
        }

        for (int i = 0; i < cantidadPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return pacientes[i];
            }
        }
        return null;
    }

    private int indicePaciente(String nombre) {
        for (int i = 0; i < cantidadPacientes; i++) {
            if (pacientes[i].getNombre().equalsIgnoreCase(nombre)) {
                return i;
            }
        }
        return -1;
    }
}
