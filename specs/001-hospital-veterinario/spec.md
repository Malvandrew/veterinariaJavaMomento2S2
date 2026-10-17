# Especificación: Hospital Veterinario

**Rama de la función**: `001-hospital-veterinario`

**Creado**: 2026-10-17

**Estado**: Aprobada por el usuario

**Entrada**: Evaluacion_POO_Hospital_Veterinario.pdf

## Escenarios de usuario y pruebas

### Historia de usuario 1: Consultar pacientes y admitir animales (Prioridad: P1)

Como responsable de la clínica, quiero consultar el estado inicial del hospital
y registrar perros, gatos y aves, para mantener la lista de pacientes y conocer
la ocupación actual.

**Por qué tiene esta prioridad**: El registro y la consulta de pacientes son la
base para las demás operaciones del hospital.

**Prueba independiente**: Iniciar el programa, consultar el reporte, registrar
cada tipo de animal y verificar los errores de datos, duplicado y capacidad.

**Escenarios de aceptación**:

1. **Dado** que el hospital acaba de iniciar, **cuando** se solicita el reporte,
   **entonces** aparecen Rocky, Misifu y Kiwi sin jaula asignada, y seis jaulas
   libres.
2. **Dado** que los datos de un animal son válidos, **cuando** se registra un
   perro, gato o ave, **entonces** aparece en la lista con sus atributos y
   estado inicial correspondientes.
3. **Dado** que los datos de registro son inválidos, el nombre ya existe o el
   hospital alcanzó su límite, **cuando** se intenta registrar un animal,
   **entonces** se responde con el primer error aplicable en el orden definido
   y no se modifica el hospital.

### Historia de usuario 2: Vacunar perros y asignar jaulas (Prioridad: P2)

Como responsable de la clínica, quiero vacunar perros elegibles y asignar a
cada paciente una jaula compatible, para aplicar las restricciones de
cuarentena, tamaño y zona.

**Por qué tiene esta prioridad**: La asignación conecta al paciente con una
jaula y permite cumplir la condición de alojamiento necesaria para medicarlo.

**Prueba independiente**: Vacunar un perro no vacunado y probar asignaciones
por zona, tamaño, disponibilidad y orden de errores.

**Escenarios de aceptación**:

1. **Dado** un perro no vacunado en cuarentena, **cuando** se vacuna, **entonces**
   queda vacunado y pasa a `INGRESADO`.
2. **Dado** un paciente elegible y una jaula libre compatible, **cuando** se
   solicita asignarlo, **entonces** se selecciona la primera jaula que cumple
   los requisitos y paciente y jaula quedan enlazados.
3. **Dado** un paciente en cuarentena, ya asignado o sin jaula compatible,
   **cuando** se solicita una asignación, **entonces** se devuelve el primer
   error aplicable según el orden especificado y no cambia la ocupación.

### Historia de usuario 3: Medicar y dar de alta pacientes (Prioridad: P3)

Como responsable de la clínica, quiero registrar medicaciones dentro de los
límites permitidos y dar de alta pacientes en tratamiento, para completar su
ciclo de atención y liberar la jaula.

**Por qué tiene esta prioridad**: El tratamiento y el alta completan el flujo
del paciente, y dependen de su registro y alojamiento.

**Prueba independiente**: Verificar dosis permitidas y rechazadas, acumulación,
estrés felino, elegibilidad para alta, liberación de jaula y compactación de la
lista.

**Escenarios de aceptación**:

1. **Dado** un paciente alojado que cumple las condiciones de medicación,
   **cuando** recibe una dosis válida, **entonces** aumenta su dosis acumulada
   y pasa a `EN_TRATAMIENTO`; si es gato, su estrés aumenta en dos, hasta un
   máximo de 10.
2. **Dado** un paciente con una dosis inválida, superior al máximo por
   aplicación o que excede el tope acumulado, **cuando** se intenta medicar,
   **entonces** se devuelve el error correspondiente sin cambiar el estado ni
   la dosis acumulada.
3. **Dado** un paciente en `EN_TRATAMIENTO`, **cuando** se solicita su alta,
   **entonces** se libera su jaula, se elimina de la lista y los siguientes
   pacientes ocupan las posiciones anteriores.
4. **Dado** un paciente que no está en `EN_TRATAMIENTO`, **cuando** se solicita
   su alta, **entonces** permanece registrado y se informa que no es apto para
   el alta.

### Casos límite

- El nombre no puede estar vacío; los nombres duplicados se reconocen sin
  distinguir mayúsculas y minúsculas.
- El peso debe ser mayor que cero y no superar 80 kg para perros ni 15 kg para
  gatos y aves.
- La edad permitida incluye los límites 0 y 30 años.
- La envergadura de un ave debe ser mayor que cero.
- Un animal de menos de 5 kg requiere tamaño S; de 5 a 25 kg, M; y de más de
  25 kg, L.
- Una jaula compatible puede tener el tamaño requerido o ser mayor. Un ave con
  envergadura mayor a 60 cm requiere como mínimo una jaula M, aun si su peso
  indica S.
- Perros y gatos usan exclusivamente la zona A; aves usan exclusivamente la
  zona B.
- El estrés felino 8 o superior impide medicar. El estrés aumenta de dos en dos
  tras cada medicación válida y no supera 10.
- La dosis máxima por aplicación equivale al peso multiplicado por el factor
  de dosis de la especie; el acumulado no supera tres veces ese máximo.
- La medicación requiere jaula asignada y las reglas de cuarentena se aplican
  según el orden de validación indicado en los requisitos.
- Una operación que falla no cambia datos ni relaciones entre pacientes y
  jaulas.

## Requisitos

### Requisitos funcionales

- **RF-001**: Al iniciar, el sistema debe cargar, sin jaula asignada:
  Rocky (perro, 30.0 kg, 4 años, raza labrador, vacunado, estado `INGRESADO`);
  Misifu (gato, 4.0 kg, 3 años, estrés 0, estado `INGRESADO`); y Kiwi (ave,
  0.8 kg, 2 años, envergadura 70 cm, estado `INGRESADO`).
- **RF-002**: Al iniciar, el sistema debe cargar las jaulas 1 (A, S), 2 (A, M),
  3 (A, M), 4 (A, L), 5 (B, S) y 6 (B, M).
- **RF-003**: El sistema debe administrar pacientes `Perro`, `Gato` y
  `AveExotica`, que comparten el conjunto de pacientes, con un máximo de 10
  pacientes y seis jaulas.
- **RF-004**: Los atributos deben ser privados. Los setters deben rechazar
  valores inválidos sin modificar el atributo. Las subclases de paciente deben
  pasar a su clase base el factor de dosis definido para su especie: 10 para
  perros, 6 para gatos y 3 para aves.
- **RF-005**: El registro debe validar nombre no vacío, peso mayor que cero y
  máximo por especie (80 kg para perro y 15 kg para gato o ave), edad de 0 a 30
  años, envergadura de ave mayor que cero y, para perros, una respuesta de
  vacunación `si` o `no`, sin distinguir mayúsculas. Un argumento numérico que
  no se pueda interpretar como número también es un dato inválido. Si los
  datos son inválidos, debe responder `ERROR: datos invalidos`.
- **RF-006**: Para registrar un paciente, el sistema debe validar en este
  orden: datos válidos, nombre no duplicado (sin distinguir mayúsculas) y
  capacidad disponible. Los errores respectivos son `ERROR: datos invalidos`,
  `ERROR: nombre duplicado` y `ERROR: hospital lleno`.
- **RF-007**: Un perro que ingresa sin estar vacunado debe quedar en
  `CUARENTENA`; cualquier otro paciente ingresa en `INGRESADO`. Un paciente en
  cuarentena no puede recibir jaula ni medicación.
- **RF-008**: El comando `vacunar <nombre>` debe vacunar solamente perros no
  vacunados y pasarlos a `INGRESADO`. Debe responder `OK: <nombre> vacunado` al
  tener éxito. Errores, en este orden: `ERROR: paciente no encontrado`,
  `ERROR: solo aplica a perros`, `ERROR: ya vacunado`.
- **RF-009**: El tamaño requerido depende del peso: menos de 5 kg requiere S,
  de 5 a 25 kg requiere M y más de 25 kg requiere L. Un ave con envergadura
  mayor a 60 cm requiere como mínimo M. Una jaula compatible puede tener el
  tamaño requerido o ser mayor.
- **RF-010**: Perros y gatos solo pueden ocupar jaulas de zona A; las aves,
  jaulas de zona B. El comando `asignar <nombre>` debe elegir la primera jaula
  libre que cumpla tamaño y zona y enlazar al paciente con su jaula. Errores,
  en este orden: `ERROR: paciente no encontrado`, `ERROR: paciente en
  cuarentena`, `ERROR: paciente ya tiene jaula`, `ERROR: no hay jaula
  compatible`.
- **RF-011**: La dosis máxima por aplicación es el peso multiplicado por el
  factor de dosis. La dosis acumulada de un paciente no debe superar tres
  veces su dosis máxima por aplicación.
- **RF-012**: El comando `medicar <nombre> <mg>` exige jaula asignada. Errores,
  en este orden: `ERROR: paciente no encontrado`, `ERROR: datos invalidos` si
  los miligramos son menores o iguales a cero, `ERROR: paciente en cuarentena`,
  `ERROR: paciente sin jaula`, `ERROR: gato con estres alto` para gatos con
  estrés igual o mayor a 8, `ERROR: dosis excede el maximo` si excede la dosis
  por aplicación y `ERROR: tope acumulado superado` si excede el tope total.
- **RF-013**: Una medicación válida debe sumar los miligramos al acumulado y
  pasar al paciente a `EN_TRATAMIENTO`. En gatos, debe aumentar el estrés en
  dos sin superar 10. La respuesta de éxito es
  `OK: <nombre> medicado con <mg> mg`; `<mg>` conserva la representación
  numérica escrita en el comando.
- **RF-014**: El comando `alta <nombre>` solo debe aceptar pacientes en
  `EN_TRATAMIENTO`. Debe liberar cualquier jaula asignada, eliminar al paciente
  de la lista desplazando los pacientes posteriores y reducir el contador.
  Respuesta de éxito: `OK: <nombre> dado de alta`. Errores, en este orden:
  `ERROR: paciente no encontrado`, `ERROR: paciente no apto para alta`.
- **RF-015**: El comando `reporte` debe mostrar, en este formato, el total de
  pacientes de un máximo de 10; por cada paciente, nombre, tipo, peso con un
  decimal, estado y número de jaula o `-`; y el total de jaulas libres. El tipo
  debe mostrarse como `Perro`, `Gato` o `AveExotica`:

  ```text
  --- REPORTE ---
  Pacientes: <cantidad>/10
  <nombre> | <tipo> | <peso> kg | <estado> | jaula: <n o ->
  Jaulas libres: <cantidad>
  ```

- **RF-016**: El sistema debe leer una línea por comando, con palabras
  separadas por espacios y decimales escritos con punto, hasta recibir
  `salir`. Debe aceptar estas formas de comando:

  ```text
  ingresar perro <nombre> <peso> <edad> <raza> <si/no>
  ingresar gato <nombre> <peso> <edad>
  ingresar ave <nombre> <peso> <edad> <envergadura>
  vacunar <nombre>
  asignar <nombre>
  medicar <nombre> <mg>
  alta <nombre>
  reporte
  salir
  ```

  El sistema debe devolver los mensajes de éxito y error indicados en estos
  requisitos. Para un comando desconocido debe responder
  `ERROR: comando desconocido.`. Si un comando reconocido tiene una cantidad
  incorrecta de argumentos, debe responder `ERROR: comando desconocido.`. Si
  un argumento numérico no se puede interpretar como número, debe responder
  `ERROR: datos invalidos`. El argumento de vacunación de perro debe ser `si` o
  `no`, sin distinguir mayúsculas. Los verbos y palabras reservadas de comando
  (`ingresar`, `perro`, `gato`, `ave`, `vacunar`, `asignar`, `medicar`, `alta`,
  `reporte` y `salir`) deben aceptarse en mayúsculas, minúsculas o combinaciones
  de ambas. Los argumentos, como nombres, raza y valores numéricos, no se
  convierten de mayúsculas a minúsculas. El texto `<mg>` en el mensaje de
  medicación debe conservar la representación numérica escrita en el comando.
- **RF-017**: La entrada mediante `Scanner` y la salida mediante `System.out`
  deben ocurrir únicamente en `Main`; las clases de dominio no deben leer ni
  imprimir en consola.
- **RF-018**: La entrega debe usar Java JDK 17 o superior, un archivo `.java`
  por clase, arreglos de capacidad fija, sin librerías externas, archivos,
  bases de datos ni colecciones dinámicas. Debe compilar con `javac *.java` y
  ejecutarse con `java Main` desde la carpeta entregada.
- **RF-019**: Las operaciones deben respetar el orden de validación indicado:
  la primera validación fallida determina el mensaje y termina el comando sin
  cambios.
### Entidades principales

- **Paciente**: Animal registrado con nombre, peso, edad, estado, factor de
  dosis, dosis acumulada y jaula opcional.
- **Perro**: Paciente con raza y estado de vacunación; factor de dosis 10.
- **Gato**: Paciente con nivel de estrés de 0 a 10; factor de dosis 6.
- **AveExotica**: Paciente con envergadura en centímetros; factor de dosis 3.
- **Jaula**: Recurso numerado, de tamaño S/M/L y zona A/B, ocupado por cero o
  un paciente.
- **Hospital**: Administra hasta 10 pacientes y seis jaulas, incluida la
  búsqueda, las operaciones de gestión y el contador de pacientes.
- **Estado de paciente**: Uno de `INGRESADO`, `CUARENTENA` o
  `EN_TRATAMIENTO`.

## Criterios de éxito

### Resultados medibles

- **CE-001**: Para cada comando admitido, el mensaje mostrado coincide
  exactamente con el mensaje de éxito o error definido en este documento.
- **CE-002**: El reporte inicial muestra exactamente los tres pacientes
  indicados, seis jaulas libres y los pesos con un decimal.
- **CE-003**: Los registros nunca superan 10 pacientes ni las asignaciones
  superan la capacidad de seis jaulas; los errores de cada operación respetan
  su orden definido.
- **CE-004**: Una medicación válida actualiza la dosis, el estado y, si es
  gato, el estrés según los límites; una medicación inválida no cambia esos
  valores.
- **CE-005**: Tras dar de alta a un paciente apto, este ya no aparece en el
  reporte, la jaula que ocupaba queda libre y la lista restante no contiene
  posiciones vacías entre pacientes.
- **CE-006**: La entrega compila con el comando indicado y funciona mediante
  comandos de consola hasta `salir`.

## Supuestos

- El PDF es la fuente de requisitos funcionales de esta especificación; las
  aclaraciones adicionales son las decisiones expresas del usuario sobre
  compatibilidad de tamaños de jaula, representación de los miligramos,
  respuesta a comandos mal formados, nombres de tipo del reporte, aceptación
  del valor de vacunación sin distinguir mayúsculas y aceptación de las
  palabras reservadas de comandos sin distinguir mayúsculas.
- Las líneas del PDF dirigidas expresamente a asistentes de IA no se
  consideran requisitos del producto, de acuerdo con la constitución aprobada.
- La especificación conserva las reglas y mensajes que aparecen en el PDF; no
  añade comandos ni comportamientos funcionales, salvo las aclaraciones
  expresamente aprobadas por el usuario.
