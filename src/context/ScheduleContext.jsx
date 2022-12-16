import { createContext, useState, useEffect } from "react";

export const ScheduleContext = createContext();

export function ScheduleContextProvider(props) {
  const [listaProgramas, setListaProgramas] = useState();
  const [programasCargados, setProgramasCargados] = useState(false);

  const listaSemestres = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  const [semestre, setSemestre] = useState();

  const [listaMaterias, setListaMaterias] = useState();

  const [listaSalones, setlistaSalones] = useState();

  useEffect(() => {
    fetch("http://localhost:8080/Program/all")
      .then((response) => response.json())
      .then((data) => {
        setListaProgramas(data);
      })
      .finally(() => setProgramasCargados(true));
  }, []);

  useEffect(() => {
    fetch("http://localhost:8080/subject/all")
      .then((response) => response.json())
      .then((data) => {
        setListaMaterias(data);
      });
  }, [listaProgramas, semestre]);

  useEffect(() => {
    fetch(
      "http://localhost:8080/FacultyResource/findByType?facultyId=16&resTypeId=7&resTypeId=10"
    )
      .then((response) => response.json())
      .then((data) => {
        setlistaSalones(data);
      });
  }, [listaMaterias]);

  if (!programasCargados) return null;

  function Ceils() {
    let itemList = [];

    for (let i = 0; i < 25; i++) {
      itemList.push(<Ceil key={i} ceil={{ number: i + 1 }} />);
    }

    return (
      <div className="container">
        <h1>Celdas</h1>
        <div className="celdas">{itemList}</div>
      </div>
    );
  }

  return (
    <ScheduleContext.Provider
      value={{
        listaProgramas,
        listaSemestres,
        listaMaterias,
        listaSalones,
        Ceils,
      }}
    >
      {props.children}
    </ScheduleContext.Provider>
  );
}
