import { createContext, useState, useEffect } from "react";

export const TeacherContext = createContext();

export function TeacherContextProvider(props) {
  const url = "http://localhost:8080/teacher/";

  const [editingTeacher, setEditingTeacher] = useState();
  const [teachers, setTeachers] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setTeachers(data);
      });
  }, []);

  async function create(teacher) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        firstName: teacher.firstName,
        lastName: teacher.lastName,
        numIden: teacher.numIden,
        isDisable: teacher.isDisable,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setTeachers((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(teacherID) {
    await fetch(url + "delete/" + teacherID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setTeachers(
          teachers.filter((teacher) => teacher.teacherID !== teacherID)
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevTeacher) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevTeacher),
    })
      .then((response) => response.json())
      .then(() => {
        teachers[teachers.indexOf(editingTeacher)] = prevTeacher;
        setTeachers(teachers);
      })
      .then(() => setEditingTeacher(null))
      .catch((e) => console.log(e));
  }

  return (
    <TeacherContext.Provider
      value={{
        teachers,
        editingTeacher,
        create,
        update,
        deleteById,
        setEditingTeacher,
      }}
    >
      {props.children}
    </TeacherContext.Provider>
  );
}
