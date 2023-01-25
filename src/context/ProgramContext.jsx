import { createContext, useState, useEffect } from "react";

export const ProgramContext = createContext();

export function ProgramContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingProgram, setEditingProgram] = useState();
  const [programs, setPrograms] = useState([]);
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
      body: JSON.stringify({
        name: program.name,
        initDate: program.initDate,
        finalDate: program.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setPrograms((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(programID) {
    await fetch(url + "delete/" + programID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setPrograms(
          programs.filter(
            (program) =>
              program.programID !== programID
          )
        )
      )
      .catch((e) => console.log(e));
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
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        programs[programs.indexOf(editingProgram)] =
          prevProgram;
        setPrograms(programs);
      })
      .then(() => setEditingProgram(null))
      .catch((e) => console.log(e));
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
      }}
    >
      {props.children}
    </ProgramContext.Provider>
  );
}
