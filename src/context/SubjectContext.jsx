import { createContext, useState, useContext, useEffect } from "react";
import { ProgramContext } from "../context/ProgramContext";

export const SubjectContext = createContext();

export function SubjectContextProvider(props) {
  const url = "http://localhost:8080/subject/";

  const { programs } = useContext(ProgramContext);

  const [editingSubject, setEditingSubject] = useState();
  const [subjects, setSubjects] = useState([]);
  const [idProgramSelected, setIdProgramSelected] = useState(0);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setSubjects(data);
      });
  }, []);

  async function create(subject) {
    console.log(subject);
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(subject),
    })
      .then((response) => response.json())
      .then((data) => {
        setSubjects((prevState) => prevState.concat([data]));
        setEditingSubject(null);
        setIdProgramSelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(subjectID) {
    await fetch(url + "delete/" + subjectID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setSubjects(
          subjects.filter((subject) => subject.subjectID !== subjectID)
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevSubject) {
    console.log(prevSubject);
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevSubject),
    })
      .then((response) => response.json())
      .then(() => {
        subjects[subjects.indexOf(editingSubject)] = prevSubject;
        setSubjects(subjects);
        setEditingSubject(null);
        setIdProgramSelected(0);
      })
      .catch((e) => console.log(e));
  }
  console.log(programs);
  return (
    <SubjectContext.Provider
      value={{
        subjects,
        editingSubject,
        create,
        update,
        deleteById,
        setEditingSubject,
        idProgramSelected,
        setIdProgramSelected,
        programs,
      }}
    >
      {props.children}
    </SubjectContext.Provider>
  );
}
