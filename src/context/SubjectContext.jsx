import { createContext, useState, useEffect } from "react";

export const SubjectContext = createContext();

export function SubjectContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingSubject, setEditingSubject] = useState();
  const [subjects, setSubjects] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setSubjects(data);
      });
  }, []);

  async function create(subject) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: subject.name,
        initDate: subject.initDate,
        finalDate: subject.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setSubjects((prevState) => prevState.concat([data]));
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
          subjects.filter(
            (subject) =>
              subject.subjectID !== subjectID
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevSubject) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevSubject),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        subjects[subjects.indexOf(editingSubject)] =
          prevSubject;
        setSubjects(subjects);
      })
      .then(() => setEditingSubject(null))
      .catch((e) => console.log(e));
  }

  return (
    <SubjectContext.Provider
      value={{
        subjects,
        editingSubject,
        create,
        update,
        deleteById,
        setEditingSubject,
      }}
    >
      {props.children}
    </SubjectContext.Provider>
  );
}
