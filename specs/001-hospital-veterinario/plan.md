# Plan de implementación: Hospital Veterinario

**Rama de la función**: `001-hospital-veterinario` | **Fecha**: 2026-10-17
| **Especificación**: [spec.md](spec.md)

**Entrada**: especificación aprobada en `specs/001-hospital-veterinario/spec.md`

## Resumen

Desarrollar la aplicación de consola del hospital veterinario definida en la
especificación aprobada. El diseño usa una clase base `Paciente`, tres clases
derivadas, la clase `Jaula`, la coordinación de operaciones en `Hospital` y la
lectura y escritura de consola en `Main`. Los datos permanecen en los arreglos
fijos requeridos por la evaluación.

El diseño prioriza construcciones Java sencillas y explícitas. No se utilizarán
colecciones, expresiones lambda, streams, records, clases abstractas, enums,
pattern matching ni expresiones `switch`.

## Contexto técnico

**Lenguaje y versión**: Java JDK 17 o superior; se utilizará sintaxis básica
compatible con el nivel principiante acordado.

**Dependencias principales**: JDK, sin librerías externas.

**Almacenamiento**: memoria del proceso mediante `Paciente[10]` y `Jaula[6]`;
sin archivos ni bases de datos.

**Validación**: compilación desde `src/` con `javac *.java` y ejecución de
escenarios manuales documentados en `quickstart.md`.

**Plataforma objetivo**: consola en cualquier sistema con JDK 17 o superior.

**Tipo de proyecto**: aplicación Java de consola, un solo módulo.

**Objetivo de rendimiento**: administrar como máximo 10 pacientes y seis jaulas
mediante recorridos sencillos de arreglos.

**Restricciones**: atributos privados; setters con validación; herencia con
`super(...)`; `Scanner` y `System.out` solo en `Main`; mensajes exactos;
precedencia de validaciones preservada; sin cambios parciales al fallar.

**Alcance**: las tres historias y los requisitos RF-001 a RF-019 de la
especificación aprobada. No se agregan comandos ni funciones.

## Revisión de la constitución

- **Dominio encapsulado**: CUMPLE. Las clases mantienen atributos privados y
  solo aceptan modificaciones después de validar.
- **Separación entre dominio y consola**: CUMPLE. `Main` posee el `Scanner` y
  escribe los resultados; `Hospital` administra el comportamiento y devuelve
  mensajes.
- **Restricciones técnicas**: CUMPLE. JDK, arreglos fijos y archivos fuente
  Java individuales, sin dependencias ni almacenamiento persistente.
- **Reglas deterministas**: CUMPLE. Cada operación conserva el orden de error
  definido y modifica el estado después de validar todas sus condiciones.
- **Comportamiento verificable**: CUMPLE. Los comandos y resultados se
  describen en el contrato y en los escenarios de comprobación.
- **Desarrollo dirigido por el usuario**: CUMPLE. El usuario aprobó esta fase;
  la implementación requiere una autorización posterior.
- **Código para principiantes**: CUMPLE. Se planean nombres descriptivos,
  `if`/`else`, ciclos, arreglos y métodos pequeños, en lugar de sintaxis
  compacta o abstracciones avanzadas.
- **Alcance limitado**: CUMPLE. El diseño se limita a los requisitos aprobados
  y a las aclaraciones expresas del usuario registradas en la especificación.

## Estructura del proyecto

### Documentación de esta función

```text
specs/001-hospital-veterinario/
├── spec.md
├── plan.md
├── research.md
├── data-model.md
├── contracts/
│   └── console-commands.md
└── quickstart.md
```

### Código fuente previsto

```text
src/
├── Main.java
├── Hospital.java
├── Paciente.java
├── Perro.java
├── Gato.java
├── AveExotica.java
└── Jaula.java
```

**Decisión de estructura**: todos los `.java` se guardan directamente en
`src/`, cada clase en su propio archivo, para cumplir `cd src && javac *.java`.
`Paciente` será una clase base normal, no abstracta; según la decisión del
usuario, `Hospital` distinguirá perros, gatos y aves mediante condiciones
`if`/`instanceof` sencillas. Los estados se guardan como texto y el setter
acepta únicamente los tres nombres definidos.

## Complejidad

No hay excepciones a la constitución ni dependencias adicionales.
