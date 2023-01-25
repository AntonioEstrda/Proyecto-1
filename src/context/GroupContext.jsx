import { createContext, useState, useEffect } from "react";

export const GroupContext = createContext();

export function GroupContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingGroup, setEditingGroup] = useState();
  const [groups, setGroups] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setGroups(data);
      });
  }, []);

  async function create(group) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: group.name,
        initDate: group.initDate,
        finalDate: group.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setGroups((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(groupID) {
    await fetch(url + "delete/" + groupID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setGroups(
          groups.filter(
            (group) =>
              group.groupID !== groupID
          )
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
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        groups[groups.indexOf(editingGroup)] =
          prevGroup;
        setGroups(groups);
      })
      .then(() => setEditingGroup(null))
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
      }}
    >
      {props.children}
    </GroupContext.Provider>
  );
}
