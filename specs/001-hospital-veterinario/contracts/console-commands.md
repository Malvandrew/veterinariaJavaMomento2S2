# Contrato de comandos de consola

La consola recibe un comando por línea. Los argumentos se separan por espacios;
los decimales se escriben con punto. El proceso continúa hasta el comando
`salir`.

## Formas de comando

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

Los verbos y palabras reservadas de comando (`ingresar`, `perro`, `gato`, `ave`,
`vacunar`, `asignar`, `medicar`, `alta`, `reporte` y `salir`) aceptan
mayúsculas, minúsculas o combinaciones de ambas. Esta regla no cambia los
argumentos ni los mensajes de respuesta.

El argumento de vacunación acepta `si` o `no` sin distinguir mayúsculas y
minúsculas. Los nombres duplicados también se comparan sin distinguir
mayúsculas y minúsculas.

## Respuestas

| Comando | Éxito | Errores, en orden |
|---|---|---|
| `ingresar perro` | `OK: <nombre> ingresado` o `OK: <nombre> ingresado en CUARENTENA` | `ERROR: datos invalidos` → `ERROR: nombre duplicado` → `ERROR: hospital lleno` |
| `ingresar gato` | `OK: <nombre> ingresado` | `ERROR: datos invalidos` → `ERROR: nombre duplicado` → `ERROR: hospital lleno` |
| `ingresar ave` | `OK: <nombre> ingresado` | `ERROR: datos invalidos` → `ERROR: nombre duplicado` → `ERROR: hospital lleno` |
| `vacunar <nombre>` | `OK: <nombre> vacunado` | `ERROR: paciente no encontrado` → `ERROR: solo aplica a perros` → `ERROR: ya vacunado` |
| `asignar <nombre>` | `OK: <nombre> asignado a jaula <n>` | `ERROR: paciente no encontrado` → `ERROR: paciente en cuarentena` → `ERROR: paciente ya tiene jaula` → `ERROR: no hay jaula compatible` |
| `medicar <nombre> <mg>` | `OK: <nombre> medicado con <mg> mg` | `ERROR: paciente no encontrado` → `ERROR: datos invalidos` → `ERROR: paciente en cuarentena` → `ERROR: paciente sin jaula` → `ERROR: gato con estres alto` → `ERROR: dosis excede el maximo` → `ERROR: tope acumulado superado` |
| `alta <nombre>` | `OK: <nombre> dado de alta` | `ERROR: paciente no encontrado` → `ERROR: paciente no apto para alta` |
| `reporte` | Reporte definido abajo | No indica errores funcionales |
| Comando desconocido | — | `ERROR: comando desconocido.` |

Un comando reconocido con número incorrecto de argumentos responde
`ERROR: comando desconocido.`. Un argumento numérico que no se pueda
interpretar como número responde `ERROR: datos invalidos`. Un valor de
vacunación distinto de `si` o `no` responde `ERROR: datos invalidos`. El texto
numérico usado para `<mg>` se conserva sin normalizar en el mensaje de éxito.

## Reporte

El peso se muestra con un decimal. Los tipos mostrados son `Perro`, `Gato` y
`AveExotica`.

```text
--- REPORTE ---
Pacientes: <cantidad>/10
<nombre> | <tipo> | <peso> kg | <estado> | jaula: <n o ->
Jaulas libres: <cantidad>
```

## Orden y efecto de comandos

- Registro: validar todos los datos, luego nombre duplicado y luego capacidad.
- Asignación: buscar paciente, revisar cuarentena, revisar jaula existente y
  buscar jaula compatible, en ese orden.
- Medicación: buscar paciente, validar mg, revisar cuarentena, jaula, estrés
  felino, máximo por aplicación y tope acumulado, en ese orden.
- Alta: buscar paciente y validar estado `EN_TRATAMIENTO`; después liberar la
  jaula, si la tiene, y eliminar al paciente compactando el arreglo.
- Si una operación falla, el estado del hospital no cambia.
