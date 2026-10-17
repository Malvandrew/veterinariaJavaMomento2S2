# Hospital Veterinario

Este proyecto es una aplicación de consola en Java para registrar y consultar pacientes de un hospital veterinario. Permite gestionar perros, gatos y aves, asignar jaulas, registrar medicaciones y dar de alta a pacientes tratados.

El proyecto se desarrolló mediante desarrollo guiado por especificación (Spec-Driven Development): primero se analizó el enunciado, se acordaron los requisitos y criterios de aceptación, y después se prepararon el diseño, las tareas y la implementación. La especificación funcional se basa en el documento de evaluación y en las aclaraciones aprobadas por el responsable.

Andrés Julián, responsable del proyecto, revisó manualmente y aprobó cada uno de los requisitos de la especificación, las historias de usuario (HU), el plan y las tareas antes de continuar con las fases siguientes. También evaluó con criterio humano y decidió las alternativas ante cada ambigüedad presentada durante el análisis y el diseño. El desarrollo prioriza código Java explícito y comprensible para estudiantes principiantes, y evita funcionalidades que no hayan sido especificadas y aprobadas.

La especificación (`specs/001-hospital-veterinario/spec.md`) se compone de 3 historias de usuario priorizadas (P1 consultar y admitir pacientes, P2 vacunar perros y asignar jaulas, P3 medicar y dar de alta), con 10 escenarios de aceptación y 11 casos límite. Define 19 requisitos funcionales (RF-001 a RF-019), 7 entidades principales (`Paciente`, `Perro`, `Gato`, `AveExotica`, `Jaula`, `Hospital` y el estado del paciente), 6 criterios de éxito medibles y una lista de supuestos. El plan técnico y el contrato de comandos están en la misma carpeta, y `tasks.md` organiza 18 tareas (T001 a T018) en 6 fases: preparación, base del dominio, una fase por historia de usuario y validación de entrega. Cada una de estas historias, requisitos, entidades, criterios, tareas y fases fue construida mediante revisión conjunta y quedó sujeta a la aprobación restrictiva de Andrés Julián: nada avanzó a la siguiente fase sin su aprobación explícita.

Las clases `Paciente`, `Perro`, `Gato` y `AveExotica` representan los animales; `Jaula` representa los espacios de alojamiento; `Hospital` administra los datos y aplica las reglas de negocio; y `Main` muestra las instrucciones, recibe comandos y presenta las respuestas.

Para compilar y ejecutar el programa, abre una terminal en la carpeta `src/` y ejecuta `javac *.java` seguido de `java Main`. Al iniciar, el programa muestra los pacientes iniciales, las jaulas disponibles, los comandos, ejemplos de uso y el prompt `Comando>`.

Comandos disponibles: `ingresar perro <nombre> <peso> <edad> <raza> <si/no>`, `ingresar gato <nombre> <peso> <edad>`, `ingresar ave <nombre> <peso> <edad> <envergadura>`, `vacunar <nombre>`, `asignar <nombre>`, `medicar <nombre> <mg>`, `alta <nombre>` y `reporte`. Escribe `salir` para terminar.

Las palabras reservadas de los comandos y los tipos de animal al ingresar aceptan mayúsculas, minúsculas o una combinación de ambas; por ejemplo, `INGRESAR GATO Felix 4 3` y `reporte`. Usa punto para los decimales. El gato requiere nombre, peso y edad; no se le indican raza ni vacunación.

El sistema carga a Rocky, Misifu y Kiwi, y las seis jaulas definidas por la evaluación al iniciar cada ejecución. Los datos se mantienen solo en memoria y vuelven a sus valores iniciales cuando se cierra y se inicia el programa.
