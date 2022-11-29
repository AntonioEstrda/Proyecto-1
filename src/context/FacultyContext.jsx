import { createContext, useState, useEffect } from "react";
import { facultys as data } from "../data/facultys";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const [facultys, setFacultys] = useState([]);

  function deleteFaculty(facultyId) {
    setFacultys(facultys.filter((faculty) => faculty.id !== facultyId));
  }

  function createFaculty(faculty) {
    setFacultys([
      ...facultys,
      {
        facultyName: faculty.name,
        id: facultys.length,
        location: faculty.location,
      },
      // fetch("http://localhost:8080/faculty/", {
      //   method: "POST",
      //   body: {
      //     name: faculty.name,
      //     id: facultys.length,
      //     location: faculty.location,
      //   }, // body data type must match "Content-Type" header
      // }),
    ]);
  }

  useEffect(() => {
    fetch("http://localhost:8080/faculty/all")
      .then((response) => response.json())
      .then((dataFaculties) => setFacultys(dataFaculties));
  }, []);

  return (
    <FacultyContext.Provider
      value={{
        facultys,
        deleteFaculty,
        createFaculty,
      }}
    >
      {props.children}
    </FacultyContext.Provider>
  );
}
