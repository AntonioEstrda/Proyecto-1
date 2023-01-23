import { createContext, useState, useEffect } from "react";

export const TeacherGroupContext = createContext();

export function TeacherGroupContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingTeacherGroup, setEditingTeacherGroup] = useState();
  const [teacherGroups, setTeacherGroups] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setTeacherGroups(data);
      });
  }, []);

  async function create(teacherGroup) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: teacherGroup.name,
        initDate: teacherGroup.initDate,
        finalDate: teacherGroup.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setTeacherGroups((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(teacherGroupID) {
    await fetch(url + "delete/" + teacherGroupID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setTeacherGroups(
          teacherGroups.filter(
            (teacherGroup) =>
              teacherGroup.teacherGroupID !== teacherGroupID
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevTeacherGroup) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevTeacherGroup),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        teacherGroups[teacherGroups.indexOf(editingTeacherGroup)] =
          prevTeacherGroup;
        setTeacherGroups(teacherGroups);
      })
      .then(() => setEditingTeacherGroup(null))
      .catch((e) => console.log(e));
  }

  return (
    <TeacherGroupContext.Provider
      value={{
        teacherGroups,
        editingTeacherGroup,
        create,
        update,
        deleteById,
        setEditingTeacherGroup,
      }}
    >
      {props.children}
    </TeacherGroupContext.Provider>
  );
}
