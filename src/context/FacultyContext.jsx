import { createContext, useState, useEffect, useContext } from "react";
import { LocationContext } from "../context/LocationContext";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const url = "http://localhost:8080/faculty/";

  const { locations } = useContext(LocationContext);

  const [editingFaculty, setEditingFaculty] = useState();
  const [facultys, setFacultys] = useState([]);
  const [idLocationSelected, setIdLocationSelected] = useState(0);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setFacultys(data);
      });
  }, []);

  async function create(faculty) {
    console.log(faculty);
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(faculty),
    })
      .then((response) => response.json())
      .then((data) => {
        setFacultys((prevState) => prevState.concat([data]));
        setEditingFaculty(null);
        setIdLocationSelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(facultyId) {
    await fetch(url + "delete/" + facultyId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setFacultys(
          facultys.filter((faculty) => faculty.facultyId !== facultyId)
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevFaculty) {
    console.log(prevFaculty);
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevFaculty),
    })
      .then((response) => response.json())
      .then(() => {
        facultys[facultys.indexOf(editingFaculty)] = prevFaculty;
        setFacultys(facultys);
        setEditingFaculty(null);
        setIdLocationSelected(0);
      })
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
        idLocationSelected,
        setIdLocationSelected,
        locations,
      }}
    >
      {props.children}
    </FacultyContext.Provider>
  );
}
