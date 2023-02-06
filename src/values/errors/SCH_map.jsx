const scheduleErrors = new Map();
scheduleErrors.set(
  "[SCH101]",
  "Horario no encontrado o Asignación no encontrada horario del departamento"
);
scheduleErrors.set("[SCH102]", "Preciosa asignación de horario encontrada");
scheduleErrors.set("[SCH103]", "Fechas Malas");
scheduleErrors.set("[SCH104]", "Tiempos Malos");
scheduleErrors.set(
  "[SCH105]",
  "Las fechas deben estar en el rango de fechas del período académico"
);
scheduleErrors.set(
  "[SCH106]",
  "El tiempo debe estar dentro del rango permitido"
);
scheduleErrors.set(
  "[SCH107]",
  "El Horario Académico debe tener la misma fecha inicial y final que el período académico activo actual"
);
scheduleErrors.set("[SCH108]", "Intervalo de horas no válido");
scheduleErrors.set("[SCH109]", "Horario inválido para Horario Académico");
scheduleErrors.set("[SCH110]", "El horario académico no debe tener un evento");
scheduleErrors.set("[SCH111]", "El horario académico debe tener un grupo");
scheduleErrors.set(
  "[SCH112]",
  "El grupo elegido no pertenece al período académico actual"
);
scheduleErrors.set(
  "[SCH113]",
  "El tipo de entorno no se corresponde con los requisitos del sujeto"
);
scheduleErrors.set(
  "[SCH114]",
  "El entorno no satisface los requisitos de la asignatura"
);
scheduleErrors.set(
  "[SCH115]",
  "excede el límite permitido de horas para asignar"
);
scheduleErrors.set("[SCH116]", "se cruza con otro sujeto");
scheduleErrors.set("[SCH117]", "No se puede asignar en el rango indicado");
scheduleErrors.set(
  "[SCH118]",
  "No se puede asignar el grupo en resEnv debido al grupo y resEnv pertenece a diferentes facultades. Pruébelo en el calendario de eventos"
);
scheduleErrors.set("[SCH119]", "Materia esta deshabilitada");
scheduleErrors.set(
  "[SCH120]",
  "No se puede asignar una materia de tipo externo (o no externo) en una franja horaria academica normal (de evento"
);
scheduleErrors.set("[SCH121]", "No se puede realizar esta prenda");
scheduleErrors.set(
  "[SCH122]",
  "No se pueden agregar eventos si no se ha diseñado la oferta académica de la facultad"
);
scheduleErrors.set(
  "[SCH123]",
  "No se pueden realizar asignaciones en las cuales el grupo y el recurso no compartan la misma facultad"
);
scheduleErrors.set(
  "[SCH124]",
  "No se pueden crear asignaciones ligadas a eventos que no esten asociados a su departamento"
);

export { scheduleErrors };
