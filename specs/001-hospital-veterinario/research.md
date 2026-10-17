# Decisiones de diseño: Hospital Veterinario

## Decisiones acordadas

### Java y nivel de complejidad

- **Decisión**: usar solo el JDK y sintaxis Java básica.
- **Razón**: la evaluación pide Java 17 o superior y el usuario prioriza que un
  estudiante principiante pueda leer el código.
- **Aplicación**: usar variables y nombres descriptivos, `if`/`else`, ciclos,
  métodos pequeños, arreglos y clases sencillas. No usar colecciones,
  streams, lambdas, records, enums, clases abstractas, pattern matching ni
  expresiones `switch`.

### Clases y jerarquía

- **Decisión**: crear `Paciente` como clase base normal y `Perro`, `Gato` y
  `AveExotica` como subclases que llaman a `super(...)`.
- **Razón**: la herencia es un criterio del PDF; el usuario eligió evitar el
  concepto adicional de clase abstracta. `Hospital` reconocerá cada especie
  mediante condiciones explícitas `if`/`instanceof`.

### Estados del paciente

- **Decisión**: guardar el estado como `String` y validar que sea uno de
  `INGRESADO`, `CUARENTENA` o `EN_TRATAMIENTO`.
- **Razón**: el usuario prefirió texto validado frente a un `enum`.

### Almacenamiento

- **Decisión**: `Hospital` administra `Paciente[10]`, un contador y `Jaula[6]`.
  El alta desplaza los pacientes posteriores y limpia la última posición usada.
- **Razón**: el PDF exige capacidad fija, contador y compactación; la
  constitución prohíbe colecciones dinámicas.

### Relación entre paciente y jaula

- **Decisión**: la asignación y liberación mantienen juntas la referencia del
  paciente y la del ocupante de la jaula.
- **Razón**: una jaula libre se define por no tener ocupante y un paciente
  conserva una referencia a su jaula o `null`. La operación valida ambos lados
  antes de completar el vínculo.

### Tamaño compatible

- **Decisión**: una jaula del tamaño requerido o mayor es compatible.
- **Razón**: el usuario eligió esta regla; además, interpreta «como mínimo M»
  para aves grandes de modo que una jaula L también sea válida.
- **Aplicación**: representar S, M y L como texto y compararlos mediante
  condiciones `if` explícitas; no usar enums ni tablas de búsqueda.

### Procesamiento de comandos

- **Decisión**: `Main` lee las líneas y muestra el texto que devuelve
  `Hospital`. `Main` separa los argumentos; las operaciones de negocio y su
  orden de validación quedan en `Hospital`.
- **Decisión específica para medicación**: `Hospital` recibe el texto de mg en
  un solo parámetro, lo interpreta para calcular y conserva su representación
  original para el mensaje de éxito.
- **Razón**: cumple el orden de error (primero paciente existente, después
  validez de mg) y evita pasar a la vez el número y una copia textual.

### Entrada mal formada

- **Decisión**: un comando reconocido con cantidad incorrecta de argumentos
  responde `ERROR: comando desconocido.`; un argumento numérico no convertible
  responde `ERROR: datos invalidos`; el indicador de vacunación acepta `si` o
  `no` sin distinguir mayúsculas.
- **Razón**: son aclaraciones funcionales explícitamente aprobadas por el
  usuario y registradas en la especificación.

### Formato del reporte

- **Decisión**: mostrar los tipos como `Perro`, `Gato` y `AveExotica`; mostrar
  el peso con un decimal; conservar el texto original de `<mg>` en el mensaje de
  medicación.
- **Razón**: los nombres de tipo y la representación de mg fueron elegidos por
  el usuario; el formato de peso con un decimal está indicado por el PDF.

### Verificación

- **Decisión**: comprobar compilación y comandos con escenarios reproducibles
  descritos en `quickstart.md`; no introducir un framework ni dependencias de
  pruebas.
- **Razón**: el enunciado exige una aplicación de consola sin dependencias
  externas y no requiere un sistema de pruebas adicional.

## Cuestiones pendientes

No quedan decisiones de diseño pendientes identificadas en esta fase. La
implementación de código es una fase separada y debe ser aprobada por el
usuario antes de iniciarse.
