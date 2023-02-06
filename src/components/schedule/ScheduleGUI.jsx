import { useContext } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";
import { diasSchedule, horasSchedule } from "../../values/util_data";

function ScheduleGUI() {
  const {
    grupos,
    idGroupSelected,
    idResourceSelected,
    asignarHorario,
    eliminarAsignacion,
    ocupacionAmbiente,
  } = useContext(ScheduleContext);

  const gruposMap = new Map();
  const spanMap = new Map();

  const diaColumns = (dia) => {
    return (
      <th key={dia} className="border-b-2 rounded border-red">
        {dia}
      </th>
    );
  };

  const asignarGrupoRecursoAHorario = (key) => {
    const dia_hora = key.split("-");
    const hora = +dia_hora[1];
    const fHor = ":00:00";
    const startime = hora > "9" ? hora + fHor : "0" + hora + fHor;
    const endtime = hora + 2 > "9" ? hora + 2 + fHor : "0" + (hora + 2) + fHor;

    const grupoSeleccionado = grupos.find(
      (grupo) => grupo.groupId == idGroupSelected
    );

    asignarHorario(
      {
        type: "ACADEMICO",
        days: dia_hora[0],
        startime,
        endtime,
        initialdate: grupoSeleccionado.academicPeriod.initDate,
        finaldate: grupoSeleccionado.academicPeriod.finalDate,
        event: null,
        group: grupoSeleccionado,
      },
      grupoSeleccionado.subject.program.department.departmentId
    );
  };

  function Celda({ index }) {
    return diasSchedule.map((dia) => {
      let key = dia + "-" + (index + 7);
      if (gruposMap.has(key)) {
        const item = gruposMap.get(key)[0];
        return (
          <td
            key={key}
            rowSpan={gruposMap.get(key)[1]}
            className="h-10 border-2 rounded border-red bg-green-300"
          >
            {item.group.subject.name +
              " - " +
              item.res.name +
              " [" +
              item.res.number +
              "]"}
            <button
              className="hover:bg-red bg-paleta2-red-claro p-3 m-3 rounded-md "
              onClick={() => {
                eliminarAsignacion(
                  item.id,
                  item.group.subject.program.department.departmentId
                );
              }}
            >
              Eliminar
            </button>
          </td>
        );
      }
      if (!spanMap.has(key)) {
        return (
          <td key={key}>
            <button
              className="w-full h-full hover:text-black text-transparent"
              onClick={() => {
                asignarGrupoRecursoAHorario(key);
              }}
            >
              Asignar en {key}
            </button>
          </td>
        );
      }
    });
  }

  const horaRows = (hora, index) => {
    if (idGroupSelected === -1 || idResourceSelected === -1)
      return (
        <tr>
          <th key={index} className="h-10 border-r-2 rounded border-red">
            {hora}
          </th>
        </tr>
      );
    return (
      <tr>
        <th key={hora} className="h-10 border-r-2 rounded border-red">
          {hora}
        </th>
        <Celda index={index}></Celda>
      </tr>
    );
  };

  ocupacionAmbiente?.forEach((schedule) => {
    let inicio = +schedule.startime.split(":")[0];
    let duracion =
      +schedule.endtime.split(":")[0] - +schedule.startime.split(":")[0];
    gruposMap.set(schedule.days + "-" + inicio, [schedule, duracion]);
    for (let i = 1; i < duracion; i++) {
      spanMap.set(schedule.days + "-" + (inicio + i));
    }
  });

  if (gruposMap)
    return (
      <div className="w-full bg-slate-200 rounded overflow-hidden shadow-lg grid grid-cols-1 divide-y-8">
        <div className="p-4 text-center">
          <table className="w-full table-fixed">
            <thead>
              <tr>
                <th
                  key={0}
                  className="border-b-2 border-r-2 rounded border-red"
                >
                  HORAS / DIAS
                </th>
                {diasSchedule.map(diaColumns)}
              </tr>
            </thead>
            <tbody>{horasSchedule.map(horaRows)}</tbody>
          </table>
        </div>
      </div>
    );
}

export default ScheduleGUI;
