import { createContext, useState, useEffect } from "react";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const url = "http://localhost:8080/faculty/";

  const [editingFaculty, setEditingFaculty] = useState();
  const [facultys, setFacultys] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setFacultys(data);
      });
  }, []);

  async function create(faculty) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: faculty.facultyName,
        location: undefined,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setFacultys((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(facultyID) {
    await fetch(url + "delete/" + facultyID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setFacultys(
          facultys.filter((faculty) => faculty.facultyID !== facultyID)
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevFaculty) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevFaculty),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        facultys[facultys.indexOf(editingFaculty)] = prevFaculty;
        setFacultys(facultys);
      })
      .then(() => setEditingFaculty(null))
      .catch((e) => console.log(e));
  }

  return (
    <FacultyContext.Provider
      value={{
        facultys,
        editingFaculty,
        create,
        update,
        deleteById,
        setEditingFaculty,
      }}
    >
      {props.children}
    </FacultyContext.Provider>
  );
}
