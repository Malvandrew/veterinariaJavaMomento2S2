# Tareas: Hospital Veterinario

**Entrada**: documentos de diseño en `specs/001-hospital-veterinario/`

**Prerrequisitos**: `spec.md`, `plan.md`, `research.md`, `data-model.md`,
`contracts/console-commands.md` y `quickstart.md`

**Pruebas**: no se solicitó agregar un framework ni pruebas automatizadas. La
validación final se hará compilando y ejecutando los escenarios de
`quickstart.md`.

## Fase 1: Preparación

**Propósito**: respetar la estructura plana de fuentes Java exigida para la
entrega.

- [x] T001 Mantener todas las clases Java directamente en `src/`, sin paquetes, para compilar con `cd src && javac *.java`

## Fase 2: Base del dominio

**Propósito**: crear las clases compartidas que requieren todas las historias.

- [x] T002 Crear `Paciente` como clase base normal, con atributos privados, setters que rechazan valores inválidos sin modificarlos, estado `String` restringido a `INGRESADO`, `CUARENTENA` o `EN_TRATAMIENTO`, dosis y referencia opcional a jaula en `src/Paciente.java`
- [x] T003 Crear `Jaula` con número, tamaño S/M/L, zona A/B y ocupante opcional; validar los setters y preparar operaciones que mantengan enlazados jaula y paciente en `src/Jaula.java`

## Fase 3: Historia 1 - Consultar y admitir pacientes (Prioridad: P1)

**Objetivo**: iniciar con los pacientes y jaulas indicados, permitir ingresos
válidos y mostrar el reporte.

**Prueba independiente**: ejecutar el programa, revisar el reporte inicial,
registrar cada especie y comprobar datos inválidos, nombre duplicado y límite
del hospital.

### Implementación

- [x] T004 [P] [US1] Crear `Perro` heredando de `Paciente`, con raza, vacunación, peso máximo 80 kg, factor de dosis 10 y estado inicial según vacunación en `src/Perro.java`
- [x] T005 [P] [US1] Crear `Gato` heredando de `Paciente`, con estrés de 0 a 10, peso máximo 15 kg y factor de dosis 6 en `src/Gato.java`
- [x] T006 [P] [US1] Crear `AveExotica` heredando de `Paciente`, con envergadura mayor que cero, peso máximo 15 kg y factor de dosis 3 en `src/AveExotica.java`
- [x] T007 [US1] Crear `Hospital` con `Paciente[10]`, contador, `Jaula[6]`, carga de los datos iniciales, búsqueda, validación de ingreso en el orden aprobado y generación del reporte en `src/Hospital.java`
- [x] T008 [US1] Crear el ciclo de consola y procesar los comandos de ingreso y `reporte`, mostrando los resultados de `Hospital` y terminando al leer `salir` en `src/Main.java`

## Fase 4: Historia 2 - Vacunar y asignar jaulas (Prioridad: P2)

**Objetivo**: vacunar perros elegibles y alojar pacientes en la primera jaula
libre compatible.

**Prueba independiente**: vacunar un perro en cuarentena y probar asignaciones
por tamaño, zona y disponibilidad, incluidos los errores en su orden aprobado.

### Implementación

- [x] T009 [US2] Agregar a `Hospital` la búsqueda y validación de vacunación, con transición del perro a `INGRESADO` y los mensajes definidos en `src/Hospital.java`
- [x] T010 [US2] Agregar a `Hospital` el tamaño requerido, compatibilidad con jaulas del mismo tamaño o mayores, zona por especie, selección de la primera jaula libre y enlace recíproco en `src/Hospital.java`
- [x] T011 [US2] Procesar `vacunar <nombre>` y `asignar <nombre>` desde consola, respetando número de argumentos y mensajes definidos en `src/Main.java`

## Fase 5: Historia 3 - Medicar y dar de alta (Prioridad: P3)

**Objetivo**: aplicar medicación dentro de los límites y dar de alta pacientes
en tratamiento.

**Prueba independiente**: revisar dosis máximas y acumuladas, aumento de estrés
felino, mensajes de rechazo, alta, liberación de jaula y compactación del
arreglo.

### Implementación

- [x] T012 [US3] Agregar en `Gato` el aumento de estrés de dos unidades sin superar 10, con nombre de método explícito, en `src/Gato.java`
- [x] T013 [US3] Agregar a `Hospital` la validación ordenada de medicación, conversión del texto de mg recibido, preservación de su representación original, acumulación y transición a `EN_TRATAMIENTO` en `src/Hospital.java`
- [x] T014 [US3] Agregar a `Hospital` el alta para pacientes en tratamiento, liberación de jaula y compactación de `Paciente[10]` en `src/Hospital.java`
- [x] T015 [US3] Procesar `medicar <nombre> <mg>` y `alta <nombre>`, y mostrar los mensajes devueltos por `Hospital` en `src/Main.java`

## Fase 6: Validación de entrega

**Propósito**: verificar los requisitos aprobados y la ejecución descrita.

- [x] T016 Compilar con `javac *.java` desde `src/` y recorrer los escenarios de `specs/001-hospital-veterinario/quickstart.md`, comprobando límites, errores, mensajes y reportes
- [x] T017 Aceptar palabras reservadas de todos los comandos y el tipo de paciente de ingreso sin distinguir mayúsculas/minúsculas en `src/Main.java`
- [x] T018 Documentar y validar ejemplos de comandos en mayúsculas, minúsculas y combinaciones en `specs/001-hospital-veterinario/spec.md`, `specs/001-hospital-veterinario/contracts/console-commands.md` y `specs/001-hospital-veterinario/quickstart.md`

## Dependencias y orden de ejecución

### Dependencias entre fases

- **Preparación**: primero se confirma el uso de la carpeta plana `src/`.
- **Base del dominio**: T002 y T003 deben estar listas antes de las historias.
- **Historia 1**: T004, T005 y T006 pueden implementarse en paralelo después
  de la base; T007 depende de esas clases y T008 depende de `Hospital`.
- **Historia 2**: depende del registro y la carga inicial de la Historia 1.
  T009 y T010 modifican el mismo archivo y se ejecutan en orden; T011 los
  integra con la consola.
- **Historia 3**: depende de pacientes, jaulas y asignación. T012 precede a la
  medicación en T013; T014 agrega el alta; T015 integra ambos comandos.
- **Validación final**: T016 depende de todas las tareas de implementación.

### Oportunidades de trabajo en paralelo

- Después de T002 y T003, se pueden desarrollar T004, T005 y T006 en paralelo:
  cada subtipo tiene un archivo distinto.
- Las tareas que modifican `Hospital.java`, `Main.java` o `Gato.java` se
  mantienen en secuencia para evitar conflictos entre cambios.

## Ejemplo de tareas paralelas

```text
Después de completar T002 y T003:
T004: Perro en src/Perro.java
T005: Gato en src/Gato.java
T006: AveExotica en src/AveExotica.java
```

## Estrategia de entrega incremental

1. Completar preparación y base común.
2. Implementar Historia 1 como primer incremento demostrable.
3. Añadir vacunación y asignación de jaulas (Historia 2).
4. Añadir medicación y alta (Historia 3).
5. Compilar y comprobar el recorrido completo del `quickstart.md`.

Las tareas describen trabajo pendiente; no se marca ninguna como completada
hasta realizarla durante la implementación.
