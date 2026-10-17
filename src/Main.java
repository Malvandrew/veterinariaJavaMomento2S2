import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hospital hospital = new Hospital();
        Scanner scanner = new Scanner(System.in);

        mostrarInstrucciones(hospital);

        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine().trim();
            if (linea.isEmpty()) {
                System.out.println("ERROR: comando desconocido.");
                mostrarPrompt();
                continue;
            }

            String[] partes = linea.split("\\s+");
            if (partes.length == 1 && partes[0].equalsIgnoreCase("salir")) {
                break;
            }

            String respuesta = ejecutarComando(partes, hospital);
            System.out.println(respuesta);
            mostrarPrompt();
        }

        scanner.close();
    }

    private static void mostrarInstrucciones(Hospital hospital) {
        System.out.println("=== HOSPITAL VETERINARIO ===");
        System.out.println("Pacientes y jaulas cargados al iniciar:");
        System.out.println(hospital.reporte());
        System.out.println();
        System.out.println("Comandos disponibles:");
        System.out.println("  ingresar perro <nombre> <peso> <edad> <raza> <si/no>");
        System.out.println("  ingresar gato <nombre> <peso> <edad>");
        System.out.println("  ingresar ave <nombre> <peso> <edad> <envergadura>");
        System.out.println("  vacunar <nombre>");
        System.out.println("  asignar <nombre>");
        System.out.println("  medicar <nombre> <mg>");
        System.out.println("  alta <nombre>");
        System.out.println("  reporte");
        System.out.println("  salir");
        System.out.println();
        System.out.println("Ejemplos de uso:");
        System.out.println("  Perro (nombre, peso, edad, raza, vacunacion):");
        System.out.println("  ingresar perro Luna 12.5 4 mestizo no");
        System.out.println("  vacunar Luna");
        System.out.println("  asignar Luna");
        System.out.println("  medicar Luna 10");
        System.out.println();
        System.out.println("  Gato (nombre, peso, edad; no lleva raza ni vacunacion):");
        System.out.println("  ingresar gato Felix 8 3");
        System.out.println("  asignar Felix");
        System.out.println("  medicar Felix 10");
        System.out.println();
        System.out.println("  Ave (nombre, peso, edad, envergadura):");
        System.out.println("  ingresar ave KiwiNuevo 0.8 2 70");
        System.out.println("  asignar KiwiNuevo");
        System.out.println("  medicar KiwiNuevo 2");
        System.out.println();
        System.out.println("  Consultar el estado del hospital:");
        System.out.println("  reporte");
        System.out.println("  alta Felix");
        System.out.println("  salir");
        mostrarPrompt();
    }

    private static void mostrarPrompt() {
        System.out.print("Comando> ");
        System.out.flush();
    }

    private static String ejecutarComando(String[] partes, Hospital hospital) {
        try {
            if (partes[0].equalsIgnoreCase("ingresar")) {
                return ejecutarIngreso(partes, hospital);
            }

            if (partes[0].equalsIgnoreCase("vacunar")) {
                if (partes.length != 2) {
                    return "ERROR: comando desconocido.";
                }
                return hospital.vacunar(partes[1]);
            }

            if (partes[0].equalsIgnoreCase("asignar")) {
                if (partes.length != 2) {
                    return "ERROR: comando desconocido.";
                }
                return hospital.asignar(partes[1]);
            }

            if (partes[0].equalsIgnoreCase("medicar")) {
                if (partes.length != 3) {
                    return "ERROR: comando desconocido.";
                }
                return hospital.medicar(partes[1], partes[2]);
            }

            if (partes[0].equalsIgnoreCase("alta")) {
                if (partes.length != 2) {
                    return "ERROR: comando desconocido.";
                }
                return hospital.alta(partes[1]);
            }

            if (partes[0].equalsIgnoreCase("reporte")) {
                if (partes.length != 1) {
                    return "ERROR: comando desconocido.";
                }
                return hospital.reporte();
            }

            return "ERROR: comando desconocido.";
        } catch (NumberFormatException excepcion) {
            return "ERROR: datos invalidos";
        }
    }

    private static String ejecutarIngreso(String[] partes, Hospital hospital) {
        if (partes.length < 2) {
            return "ERROR: comando desconocido.";
        }

        if (partes[1].equalsIgnoreCase("perro")) {
            if (partes.length != 7) {
                return "ERROR: comando desconocido.";
            }

            double peso = Double.parseDouble(partes[3]);
            int edad = Integer.parseInt(partes[4]);
            return hospital.ingresarPerro(
                    partes[2],
                    peso,
                    edad,
                    partes[5],
                    partes[6]);
        }

        if (partes[1].equalsIgnoreCase("gato")) {
            if (partes.length != 5) {
                return "ERROR: comando desconocido.";
            }

            double peso = Double.parseDouble(partes[3]);
            int edad = Integer.parseInt(partes[4]);
            return hospital.ingresarGato(partes[2], peso, edad);
        }

        if (partes[1].equalsIgnoreCase("ave")) {
            if (partes.length != 6) {
                return "ERROR: comando desconocido.";
            }

            double peso = Double.parseDouble(partes[3]);
            int edad = Integer.parseInt(partes[4]);
            double envergadura = Double.parseDouble(partes[5]);
            return hospital.ingresarAve(partes[2], peso, edad, envergadura);
        }

        return "ERROR: comando desconocido.";
    }
}
