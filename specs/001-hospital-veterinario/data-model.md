# Modelo de datos: Hospital Veterinario

Los datos existen únicamente mientras se ejecuta el programa y se mantienen en
arreglos de capacidad fija según el enunciado.

## Estado del paciente

El atributo de estado es texto y solo admite:

- `INGRESADO`
- `CUARENTENA`
- `EN_TRATAMIENTO`

## Paciente

Clase base para los tres tipos de paciente.

| Atributo | Tipo conceptual | Regla |
|---|---|---|
| nombre | Texto | No vacío; único en el hospital ignorando mayúsculas/minúsculas |
| peso | Decimal | Mayor que cero; máximo específico de especie |
| edad | Entero | De 0 a 30, ambos incluidos |
| estado | Texto | Uno de los tres estados permitidos |
| factorDosis | Decimal | Fijo por subtipo: perro 10, gato 6, ave 3 |
| dosisAcumulada | Decimal | Inicia en 0; no supera tres veces peso × factorDosis |
| jaula | Referencia a Jaula o nula | Como máximo una jaula asignada |

Los atributos son privados. Los setters deben validar antes de cambiar el
atributo.

## Perro

Subtipo de `Paciente`.

| Atributo | Tipo conceptual | Regla |
|---|---|---|
| raza | Texto | Se recibe como argumento del comando |
| vacunado | Booleano | `si`/`no` del comando; estado inicial depende de este valor |

Peso máximo: 80 kg. Factor de dosis: 10. Un perro no vacunado comienza en
`CUARENTENA`; uno vacunado comienza en `INGRESADO`.

## Gato

Subtipo de `Paciente`.

| Atributo | Tipo conceptual | Regla |
|---|---|---|
| nivelEstres | Entero | De 0 a 10, ambos incluidos |

Peso máximo: 15 kg. Factor de dosis: 6. Si el estrés es 8 o superior, no se
puede medicar. Cada medicación válida aumenta el estrés en dos, hasta un máximo
de 10.

## AveExotica

Subtipo de `Paciente`.

| Atributo | Tipo conceptual | Regla |
|---|---|---|
| envergadura | Decimal | Mayor que cero, medida en centímetros |

Peso máximo: 15 kg. Factor de dosis: 3. Una envergadura superior a 60 cm
requiere una jaula de tamaño M como mínimo.

## Jaula

| Atributo | Tipo conceptual | Regla |
|---|---|---|
| numero | Entero | Identifica la jaula |
| tamano | Texto | S, M o L |
| zona | Texto | A o B |
| ocupante | Referencia a Paciente o nula | Nula significa que la jaula está libre |

Perros y gatos requieren zona A; las aves requieren zona B. Una jaula compatible
puede ser del tamaño requerido o mayor. La relación de ocupación debe reflejar
la misma asignación desde la jaula y desde el paciente.

## Hospital

- Contiene un arreglo de 10 referencias a `Paciente` y un contador de pacientes.
- Contiene un arreglo de seis jaulas.
- Carga los tres pacientes y seis jaulas iniciales establecidos en RF-001 y
  RF-002.
- Coordina registro, búsqueda, vacunación, asignación, medicación, alta y
  generación del reporte.
- El alta desplaza los pacientes posteriores del arreglo y reduce el contador.

## Reglas calculadas

- Tamaño requerido por peso: menos de 5 kg = S; de 5 a 25 kg = M; más de
  25 kg = L.
- Tamaño requerido por ave grande: al menos M cuando la envergadura supera
  60 cm.
- Máximo por aplicación: peso × factorDosis.
- Máximo acumulado: 3 × (peso × factorDosis).
