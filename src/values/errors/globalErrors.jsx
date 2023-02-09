const globalErrors = new Map();

/**
 * FACULTY
 */
globalErrors.set("FAC101", "Facultad no encontrada");
globalErrors.set("FAC102", "Facultad registrada con ese ID");
globalErrors.set("FAC103", "El nombre de la facultad no debe ser nulo");
globalErrors.set("FAC104", "La ubicación no debe ser nula");
globalErrors.set(
  "FAC105",
  "El nombre de la facultad debe contener al menos cinco caracteres"
);

/**
 * LOCATION
 */
globalErrors.set("LOC101", "Locación no encontrada");
globalErrors.set("LOC102", "El nombre de la locación no debe ser nulo");
globalErrors.set("LOC103", "La ciudad no debe ser nula o vacía");

/**
 * FACULTY_RESOURCE
 */
globalErrors.set("FACRES101", "Asignación no encontrada");
globalErrors.set("FACRES102", "Asignación existente (Facultad, Recurso)");
globalErrors.set("FACRES103", "Recurso asignado en otra facultad");

/**
 * RESOURCES
 */
globalErrors.set("RES101", "Recurso no encontrado");
globalErrors.set("RES102", "La descripción no debe estar vacía");
globalErrors.set("RES103", "el campo isDisable no debe estar vacío");
globalErrors.set("RES104", "Debe contene un tipo de recurso");
globalErrors.set("RES105", "El nombre del recurso no debe estar vacío");
globalErrors.set("RES106", "El código del recurso no debe estar vacío");
globalErrors.set("RES107", "Recurso existente");
globalErrors.set("RES108", "El número no debe ser nulo");
globalErrors.set("RES109", "debde tener un valor mayor a 1");
globalErrors.set(
  "RES110",
  "El código debe contener solo valores alfanuméricos, no especiales"
);
globalErrors.set(
  "RES111",
  "Existe un recurso con esa combinación Código número"
);
globalErrors.set("RES112", "El recurso no es de tipo ambiente");
globalErrors.set("RES113", "El recurso no debe ser de tipo ambiente");
globalErrors.set("RES114", "La ubicación no debe ser nula");
globalErrors.set("RES115", "El recurso está desactivado");

/**
 * RESOURCE_TYPE
 */
globalErrors.set("RESTYP101", "tipo de recurso no encontrado");
globalErrors.set("RESTYP102", "el campo isDisable no debe estar vacío");
globalErrors.set("RESTYP103", "el nombre del tipo no debe estar vacío");
globalErrors.set("RESTYP104", "tipo de recurso existente");
globalErrors.set("RESTYP105", "recurso padre no existente");

/**
 * ENVIRONMENT
 */
globalErrors.set("ENV101", "Ambiente no encontrado");
globalErrors.set("ENV102", "La ubicación debe contener un valor");
globalErrors.set("ENV105", "el campo isDisable de contener un valor");
globalErrors.set("ENV106", "la capacidad debe contener un valor");
globalErrors.set(
  "ENV107",
  "El valor de la capacidad debe ser mayor igual que uno"
);
globalErrors.set("ENV108", "El ambiente debe tener un tipo");
globalErrors.set("ENV109", "El valor del número no puede ser nulo");
globalErrors.set("ENV110", "El código debe contener un valor");
globalErrors.set(
  "ENV111",
  "El código debe contener solo caracteres alfabéticos, y tener mínimo 2 caracteres y máximo 5"
);
globalErrors.set("ENV112", "Existencia previa del ambiente");
globalErrors.set("ENV113", "El ambiente está desactivado");

/**
 * ASSIGNMENT_RESOURCE
 */
globalErrors.set("ASSRES101", "Asignación no encontrada");
globalErrors.set("ASSRES102", "Existe una asignación previa sobre el recurso");

/**
 * DEPARTMENT
 */
globalErrors.set("DEPT101", "Departamento no encontrado");
globalErrors.set("DEPT102", "Despartamento registrado con ese ID");
globalErrors.set("DEPT103", "El nombre del Departamento no debe ser nulo");
globalErrors.set("DEPT104", "El codigo no debe estar vacío");
globalErrors.set("DEPT105", "La ubicación no debe estar vacía");
globalErrors.set(
  "DEPT106",
  "La ubicación debe contener al menos cinco caractetes"
);
globalErrors.set("DEPT107", "El código debe contener un valor");
globalErrors.set(
  "DEPT108",
  "El código debe contener solo caracteres alfabéticos, y tener mínimo 2 caracteres y máximo 5"
);
globalErrors.set("DEPT109", "El campo ID Facultad no puede ser nulo");
globalErrors.set("DEPT110", "Debe existir una facultad registrada");
globalErrors.set("DEPT111", "El codigo debe tener de 2 a 8 caracteres");
globalErrors.set("DEPT112", "Departamento registrado con este código");

/**
 * GROUP
 */
globalErrors.set("GROUP101", "Group no encontrado");
globalErrors.set("GROUP102", "Group registrado con ese ID previamente");
globalErrors.set("GROUP103", "El nombre del Group no debe ser nulo");
globalErrors.set("GROUP104", "La capacidad no puede ser nulo");
globalErrors.set("GROUP105", "El ID de Materia no puede ser nulo");
globalErrors.set("GROUP106", "El ID de Periodo Academico no puede ser nulo");

/**
 * PROGRAM
 */
globalErrors.set("PRG101", "Programa no encontrado");
globalErrors.set("PRG102", "Programa registrado con ese ID previamente");
globalErrors.set("PRG103", "El nombre del Programa no debe ser nulo");
globalErrors.set("PRG104", "El codigo del Programa no debe ser nulo");
globalErrors.set("PRG105", "La Ubicacion del Programa no debe ser nulo");
globalErrors.set("PRG106", "El ID del departamento no puede ser nulo");
globalErrors.set("PRG107", "El codigo debe tener de 2 a 8 caracteres");
globalErrors.set("PRG108", "Programa registrado con este código");
globalErrors.set("PRG109", "El campo color no puede estar vacío");

/**
 * HOURLY_ASSIGNMENT
 */
globalErrors.set("HA101", "Asignación no encontrada");
globalErrors.set("HA102", "Existe una asignación registrada con este ID");
globalErrors.set("HA103", "El ID de Departamento no puede ser nulo");
globalErrors.set("HA104", "El ID de Profesor no puede ser nulo");
globalErrors.set("HA105", "El campo Horas no puede ser nulo");
globalErrors.set("HA106", "El campo Tipo de Vinculacion no puede ser nulo");
globalErrors.set("HA107", "El campo de fecha inicial no puede ser nulo");
globalErrors.set("HA108", "El campo fecha final no puede ser nulo");
globalErrors.set(
  "HA109",
  "IsDisable debe contener un valor (por defecto False"
);

/**
 * ACADEMIC_PERIOD
 */
globalErrors.set("AP101", "Periodo Academico no encontrado");
globalErrors.set(
  "AP102",
  "Periodo Academico registrado con este ID previamente"
);
globalErrors.set("AP103", "El nombre del Periodo Academico no debe ser nulo");
globalErrors.set("AP104", "La fecha de inicio no puede ser nula");
globalErrors.set("AP105", "La fecha de finalización no puede ser nula");

/**
 * TEACHER
 */
globalErrors.set("TCH101", "Profesor no encontrado");
globalErrors.set("TCH102", "Profesor registrado con este ID previamente");
globalErrors.set("TCH103", "El nombre del Profesor no debe ser nulo");
globalErrors.set("TCH104", "El apellido del Profesor no debe ser nulo");
globalErrors.set(
  "TCH105",
  "El numero de identificación del Profesor no debe ser nulo"
);
globalErrors.set(
  "TCH106",
  "El numero de identificación del Profesor debe ser numerico"
);
globalErrors.set("TCH107", "El campo isDisable debe contener un valor");
globalErrors.set("TCH108", "Profesor registrado con esta identificación");

/**
 * SUBJECT
 */
globalErrors.set("SUBJ101", "Materia no encontrada");
globalErrors.set("SUBJ102", "Materia registrada con este ID previamente");
globalErrors.set("SUBJ103", "El nombre de la Materia no debe ser nulo");
globalErrors.set("SUBJ104", "El campo requisitos de Materia no debe ser nulo");
globalErrors.set(
  "SUBJ105",
  "El campo del semestre no debe ser igual o menor a 0"
);
globalErrors.set(
  "SUBJ106",
  "La campo de intensidad no debe ser igual o menor a 0"
);
globalErrors.set("SUBJ107", "El campo de modalidad no debe ser nulo");
globalErrors.set("SUBJ108", "El campo isDisable debe contener un valor");
globalErrors.set("SUBJ109", "El campo tipo no deber ser nulo");
globalErrors.set("SUBJ110", "El campo del programa debe contener un valor");
globalErrors.set(
  "SUBJ111",
  'El requerimiento debe ser de la siguiente manera  {"resEnvTypeId":[restypeid, restypeId], "resEnvTypeId":[restypeid, restypeId]}, se aceptan valores nulos key:null value, o listas vacías  "resEnvtypeId":[], los valores long hacen alusión a tipos de recurso "resEnvTypeId" es un identificador de un recurso de tipo ambiente'
);
globalErrors.set("SUBJ112", "El código debe contener un valor");
globalErrors.set(
  "SUBJ113",
  "El código de la mteria debe seguir el siguiente patrón ^(?i)(\\w{2,6})\\d{1,3}$"
);

/**
 * TEACHER_GROUP
 */
globalErrors.set("TG101", "Profesor no encontrado");
globalErrors.set("TG102", "Grupo no encontrado");
globalErrors.set("TG103", "El Id del Profesor no debe ser nulo");
globalErrors.set("TG104", "El Id del Grupo no debe ser nulo");
globalErrors.set(
  "TG105",
  "Profesor con Grupo registrado con este ID previamente"
);
globalErrors.set("TG106", "Profesor con Grupo no encontrado");
globalErrors.set(
  "TG107",
  "La intensidad en horas excede las horas disponibles del profesor"
);

/**
 * STANDAR_ERRORS
 */
globalErrors.set(
  "STD101",
  "No se puede eliminar el registro asociado, debido a las subdependencias"
);

/**
 * EVENT
 */
globalErrors.set("EVT101", "Evento no encontrado");
globalErrors.set(
  "EVT102",
  "El evento ya existe / Código asignado a otro evento"
);
globalErrors.set("EVT103", "El nombre debe tener un valor");
globalErrors.set("EVT104", "La descripción debe tener un valor");
globalErrors.set(
  "EVT105",
  "Código de evento, debe tener un valor o contener solo caracteres alfanuméricos"
);
globalErrors.set("EVT106", "El evento debe tener una profesora asociada");
globalErrors.set(
  "EVT107",
  "El evento debe estar asociado a un período académico o debe ser el período académico actual"
);
globalErrors.set("EVT108", "Debe tener un programa");
globalErrors.set("EVT109", "Debe tener un tipo");

/**
 * SCHEDULE
 */
globalErrors.set(
  "SCH101",
  "Horario no encontrado o Asignación no encontrada horario del departamento"
);
globalErrors.set("SCH102", "Preciosa asignación de horario encontrada");
globalErrors.set("SCH103", "Fechas Malas");
globalErrors.set("SCH104", "Tiempos Malos");
globalErrors.set(
  "SCH105",
  "Las fechas deben estar en el rango de fechas del período académico"
);
globalErrors.set("SCH106", "El tiempo debe estar dentro del rango permitido");
globalErrors.set(
  "SCH107",
  "El Horario Académico debe tener la misma fecha inicial y final que el período académico activo actual"
);
globalErrors.set("SCH108", "Intervalo de horas no válido");
globalErrors.set("SCH109", "Horario inválido para Horario Académico");
globalErrors.set("SCH110", "El horario académico no debe tener un evento");
globalErrors.set("SCH111", "El horario académico debe tener un grupo");
globalErrors.set(
  "SCH112",
  "El grupo elegido no pertenece al período académico actual"
);
globalErrors.set(
  "SCH113",
  "El tipo de entorno no se corresponde con los requisitos del sujeto"
);
globalErrors.set(
  "SCH114",
  "El entorno no satisface los requisitos de la asignatura"
);
globalErrors.set("SCH115", "excede el límite permitido de horas para asignar");
globalErrors.set("SCH116", "se cruza con otro sujeto");
globalErrors.set("SCH117", "No se puede asignar en el rango indicado");
globalErrors.set(
  "SCH118",
  "No se puede asignar el grupo en resEnv debido al grupo y resEnv pertenece a diferentes facultades. Pruébelo en el calendario de eventos"
);
globalErrors.set("SCH119", "Materia esta deshabilitada");
globalErrors.set(
  "SCH120",
  "No se puede asignar una materia de tipo externo (o no externo) en una franja horaria academica normal (de evento"
);
globalErrors.set("SCH121", "No se puede realizar esta prenda");
globalErrors.set(
  "SCH122",
  "No se pueden agregar eventos si no se ha diseñado la oferta académica de la facultad"
);
globalErrors.set(
  "SCH123",
  "No se pueden realizar asignaciones en las cuales el grupo y el recurso no compartan la misma facultad"
);
globalErrors.set(
  "SCH124",
  "No se pueden crear asignaciones ligadas a eventos que no esten asociados a su departamento"
);

export { globalErrors };
