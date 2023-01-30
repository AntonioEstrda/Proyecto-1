import { useContext } from "react";
import { createContext, useState, useEffect } from "react";
import { SubjectContext } from "../context/SubjectContext";
import { AcademicPeriodContext } from "../context/AcademicPeriodContext";

export const GroupContext = createContext();

export function GroupContextProvider(props) {
  const url = "http://localhost:8080/groupt/";

  const { subjects } = useContext(SubjectContext);
  const { academicPeriods } = useContext(AcademicPeriodContext);

  const [editingGroup, setEditingGroup] = useState();
  const [groups, setGroups] = useState([]);
  const [idSubjectSelected, setIdSubjectSelected] = useState(0);
  const [idAcademicPeriodSelected, setAcademicPeriodSelected] = useState(0);
  useEffect(() => {
    subjects.forEach((subject) => {
      fetch(
        url +
        "all"
      )
        .then((response) => response.json())
        .then((data) => {
          setGroups(...groups, data);
        })
        .catch((e) => console.log(e));
    });
  }, []);

  async function create(group) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(group),
    })
      .then((response) => response.json())
      .then((data) => {
        setGroups((prevState) => prevState.concat([data]));
        setEditingGroup(null);
        setIdSubjectSelected(0);
        setAcademicPeriodSelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(groupId) {
    await fetch(url + "delete/" + groupId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setGroups(
          groups.filter((group) => group.groupId !== groupId)
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevGroup) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevGroup),
    })
      .then((response) => response.json())
      .then(() => {
        groups[groups.indexOf(editingGroup)] = prevGroup;
        setGroups(groups);
        setEditingGroup(null);
        setIdSubjectSelected(0);
        setAcademicPeriodSelected(0);
      })
      .catch((e) => console.log(e));
  }

  return (
    <GroupContext.Provider
      value={{
        groups,
        editingGroup,
        create,
        update,
        deleteById,
        setEditingGroup,
        idSubjectSelected,
        setIdSubjectSelected,
        subjects,
        setAcademicPeriodSelected,
        idAcademicPeriodSelected,
        academicPeriods,
      }}
    >
      {props.children}
    </GroupContext.Provider>
  );
}
