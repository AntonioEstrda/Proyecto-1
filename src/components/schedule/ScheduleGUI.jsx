import { useContext, useState, useEffect } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleGUI() {
  const { grupos, idGroupSelected, idResourceSelected } =
    useContext(ScheduleContext);

  const gruposMap = new Map();
  const [ocupacionAmbiente, setOcupacionAmbiente] = useState();
  useEffect(() => {
    fetch(
      "http://localhost:8080/Schedule/AcademicScheduleByEnvId?" +
        new URLSearchParams({
          EnvironmentId: idResourceSelected,
        })
    )
      .then((response) => response.json())
      .then((data) => {
        setOcupacionAmbiente(data);
      })
      .catch((e) => console.log(e));
  }, [idResourceSelected]);

  const dias = ["LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES", "SABADO"];
  const diaColumns = (dia, index) => {
    return <th className="border-b-2 rounded border-red">{dia}</th>;
  };

  const horas = [
    "7 AM",
    "8 AM",
    "9 AM",
    "10 AM",
    "11 AM",
    "12 M",
    "1 PM",
    "2 PM",
    "3 PM",
    "4 PM",
    "5 PM",
    "6 PM",
  ];
  const horaRows = (hora, index) => {
    return (
      <tr>
        <th className="h-10 border-r-2 rounded border-red">{hora}</th>
        {dias.map((dia) => {
          let key = dia + "-" + (index + 7);
          if (gruposMap.has(key))
            return (
              <td className="h-10 border-r-2 rounded border-red">
                {gruposMap.get(key).group.subject.name}
              </td>
            );
        })}
      </tr>
    );
  };

  console.log(ocupacionAmbiente);
  console.log(gruposMap);

  ocupacionAmbiente?.forEach((schedule) => {
    for (
      let count = +schedule.startime.split(":")[0];
      count < +schedule.endtime.split(":")[0];
      count++
    ) {
      gruposMap.set(schedule.days + "-" + count, schedule);
    }
  });

  if (gruposMap)
    return (
      <div className="mt-4 w-full bg-slate-200 rounded overflow-hidden shadow-lg grid grid-cols-1 divide-y-8">
        <div className="p-4 text-center">
          <table className="w-full table-fixed">
            <thead>
              <tr>
                <th className="border-b-2 border-r-2 rounded border-red">
                  HORAS / DIAS
                </th>
                {dias.map(diaColumns)}
              </tr>
            </thead>
            <tbody>{horas.map(horaRows)}</tbody>
          </table>
        </div>
      </div>
    );
}

export default ScheduleGUI;
