# Guía de comprobación: Hospital Veterinario

## Requisitos previos

- JDK 17 o superior.
- Terminal en la carpeta del proyecto.

## Compilar y ejecutar

```sh
cd src
javac *.java
java Main
```

## Datos iniciales y reporte

Escribir:

```text
reporte
salir
```

Comprobar que el reporte contiene Rocky, Misifu y Kiwi, los tipos
`Perro`/`Gato`/`AveExotica`, los pesos `30.0`, `4.0` y `0.8`, estado
`INGRESADO`, sin jaula asignada, y `Jaulas libres: 6`.

## Registro, cuarentena y vacunación

```text
ingresar perro Rex 12.5 5 beagle no
asignar Rex
vacunar Rex
asignar Rex
reporte
salir
```

Comprobar: Rex ingresa en `CUARENTENA`; la primera asignación se rechaza por
cuarentena; la vacunación pasa a `INGRESADO`; la segunda asignación usa la
primera jaula A compatible, tamaño M.

## Jaulas y primera jaula compatible

```text
asignar Misifu
asignar Rocky
asignar Kiwi
reporte
salir
```

Comprobar que Misifu usa la primera jaula A compatible, Rocky una jaula A L y
Kiwi una jaula B M (su envergadura es 70 cm). Las referencias de paciente y
ocupante deben concordar.

## Medicación, acumulado y alta

```text
asignar Rocky
medicar Rocky 300.00
medicar Rocky 300
medicar Rocky 300
medicar Rocky 1
alta Rocky
reporte
salir
```

Comprobar que las tres dosis de 300 alcanzan el acumulado máximo de 900 mg; el
mensaje de la primera conserva `300.00`; la dosis posterior de 1 mg se rechaza
por tope acumulado; el alta elimina a Rocky, compacta la lista y libera su
jaula.

## Aumento de estrés felino

```text
asignar Misifu
medicar Misifu 1
medicar Misifu 1
medicar Misifu 1
medicar Misifu 1
medicar Misifu 1
alta Misifu
salir
```

Comprobar que las primeras cuatro dosis suben el estrés a 8, la quinta se
rechaza por estrés alto y el alta procede porque el gato recibió medicación
válida.

## Precedencia y errores de datos

```text
ingresar gato Rocky 0 31
ingresar gato rocky 2 2
ingresar perro Nuevo 10 2 mestizo tal
ingresar gato Otro peso 2
asignar Desconocido
medicar Desconocido 0
alta Rocky
salir
```

Comprobar que se informan, en ese orden, datos inválidos antes que duplicado,
nombre duplicado ignorando mayúsculas, vacunación inválida, número no
interpretable, paciente no encontrado antes que dosis inválida y paciente no
apto para alta. Una cantidad incorrecta de argumentos debe dar
`ERROR: comando desconocido.`.
