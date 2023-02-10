import { createContext, useState, useEffect, useContext } from "react";
import { AlertContext } from "./AlertContext";

export const ScheduleContext = createContext();

export function ScheduleContextProvider(props) {
  const { alert, setAlert, closeAlert } = useContext(AlertContext);

  const [grupos, setGrupos] = useState();
  const [recurso, setRecurso] = useState();
  const [idGroupSelected, setIdGroupSelected] = useState(-1);
  const [idResourceSelected, setIdResourceSelected] = useState(-1);
  const [ocupacionAmbiente, setOcupacionAmbiente] = useState();

  const url = "http://localhost:8080/Schedule/";

  useEffect(() => {
    fetch("http://localhost:8080/groupt/all")
      .then((response) => response.json())
      .then((data) => {
        setGrupos(data);
      })
      .catch((e) => console.error(e));
  }, []);

  useEffect(() => {
    if (idResourceSelected === -1) return;
    cargarAsignacionesRecurso();
  }, [idResourceSelected]);

  async function cargarAsignacionesRecurso() {
    await fetch(
      "http://localhost:8080/Schedule/AcademicScheduleByEnvId?" +
        new URLSearchParams({
          EnvironmentId: idResourceSelected,
        })
    )
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        if (data != ocupacionAmbiente) setOcupacionAmbiente(data);
        setAlert();
      })
      .catch((e) => {
        if (e.status === 404) setAlert("empty");
        if (e.status === 400) setAlert(e.headers.get("errors"));
        setOcupacionAmbiente([]);
      });
  }

  async function asignarHorario(schedule, departmentId) {
    const body = {
      ...schedule,
      res: recurso,
    };

    await fetch(
      url +
        "add?" +
        new URLSearchParams({
          departmentId,
        }),
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify(body),
      }
    )
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        cargarAsignacionesRecurso();
        setGrupos((prevState) => prevState.concat([data]));
      })
      .catch((e) => {
        if (e.status === 404) setAlert("empty");
        if (e.status === 400) setAlert(e.headers.get("errors"));
      });
  }

  async function eliminarAsignacion(scheduleId, departmentId) {
    await fetch(
      url +
        "delete?" +
        new URLSearchParams({
          id: scheduleId,
          departmentId,
        }),
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
      }
    )
      .then(() => {
        cargarAsignacionesRecurso();
        setGrupos(grupos.filter((grupo) => grupo.id !== scheduleId));
      })
      .catch((e) => console.error(e));
  }

  if (!grupos) return null;

  return (
    <ScheduleContext.Provider
      value={{
        grupos,
        idGroupSelected,
        setIdGroupSelected,
        idResourceSelected,
        setIdResourceSelected,
        asignarHorario,
        setRecurso,
        eliminarAsignacion,
        ocupacionAmbiente,
        setOcupacionAmbiente,
        alert,
        setAlert,
        closeAlert,
      }}
    >
      {props.children}
    </ScheduleContext.Provider>
  );
}
