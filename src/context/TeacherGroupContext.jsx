import { createContext, useState, useEffect, useContext } from "react";
import { TeacherContext } from "../context/TeacherContext";
import { GroupContext } from "../context/GroupContext";

export const TeacherGroupContext = createContext();

export function TeacherGroupContextProvider(props) {
  const url = "http://localhost:8080/teacher_group/";

  const { teachers } = useContext(TeacherContext);
  const { groups } = useContext(GroupContext);

  const [editingTeacherGroup, setEditingTeacherGroup] = useState();
  const [teacherGroups, setTeacherGroups] = useState([]);
  const [idTeacherSelected, setIdTeacherSelected] = useState(0);
  const [idGroupSelected, setIdGroupSelected] = useState(0);
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
      body: JSON.stringify(teacherGroup),
    })
      .then((response) => response.json())
      .then((data) => {
        setTeacherGroups((prevState) => prevState.concat([data]));
        setEditingTeacherGroup(null);
        setIdTeacherSelected(0);
        setIdGroupSelected(0);
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
            (teacherGroup) => teacherGroup.teacherGroupID !== teacherGroupID
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
        teacherGroups[teacherGroups.indexOf(editingTeacherGroup)] =
          prevTeacherGroup;
        setTeacherGroups(teacherGroups);
        setEditingTeacherGroup(null);
        setIdTeacherSelected(0);
        setIdGroupSelected(0);
      })
      .catch((e) => console.log(e));
  }
  //console.log(teachers);
  return (
    <TeacherGroupContext.Provider
      value={{
        teacherGroups,
        editingTeacherGroup,
        create,
        update,
        deleteById,
        setEditingTeacherGroup,
        idTeacherSelected,
        idGroupSelected,
        setIdTeacherSelected,
        setIdGroupSelected,
        teachers,
        groups,
      }}
    >
      {props.children}
    </TeacherGroupContext.Provider>
  );
}
