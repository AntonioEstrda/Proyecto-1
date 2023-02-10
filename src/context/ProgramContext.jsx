import { createContext, useState, useEffect, useContext } from "react";
import { LocationContext } from "../context/LocationContext";
import { DepartmentContext } from "../context/DepartmentContext";

export const ProgramContext = createContext();

export function ProgramContextProvider(props) {
  const url = "http://localhost:8080/Program/";

  const { departments } = useContext(DepartmentContext);
  const { locations } = useContext(LocationContext);

  const [editingProgram, setEditingProgram] = useState();
  const [programs, setPrograms] = useState([]);
  const [idLocationSelected, setIdLocationSelected] = useState(0);
  const [idDepartmentSelected, setIdDepartmentSelected] = useState(0);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setPrograms(data);
      });
  }, []);

  async function create(program) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(program),
    })
      .then((response) => response.json())
      .then((data) => {
        setPrograms((prevState) => prevState.concat([data]));
        setEditingProgram(null);
        setIdLocationSelected(0);
        setIdDepartmentSelected(0);
      })
      .catch((e) => console.error(e));
  }

  async function deleteById(ProgramId) {
    await fetch(url + "delete/" + ProgramId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setPrograms(
          programs.filter((program) => program.ProgramId !== ProgramId)
        )
      )
      .catch((e) => console.error(e));
  }

  async function update(prevProgram) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevProgram),
    })
      .then((response) => response.json())
      .then((data) => {
        programs[programs.indexOf(editingProgram)] = prevProgram;
        setPrograms(programs);
        setEditingProgram(null);
        setIdLocationSelected(0);
        setIdDepartmentSelected(0);
      })
      .catch((e) => console.error(e));
  }

  return (
    <ProgramContext.Provider
      value={{
        programs,
        editingProgram,
        create,
        update,
        deleteById,
        setEditingProgram,
        idDepartmentSelected,
        idLocationSelected,
        setIdLocationSelected,
        setIdDepartmentSelected,
        locations,
        departments,
      }}
    >
      {props.children}
    </ProgramContext.Provider>
  );
}
