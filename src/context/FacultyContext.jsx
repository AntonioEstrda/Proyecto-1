import { createContext, useState, useEffect } from "react";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const [facultys, setFacultys] = useState([]);

  function deleteFaculty(facultyId) {
    setFacultys(facultys.filter((faculty) => faculty.id !== facultyId));
  }

  function createFaculty(faculty) {
    fetch("http://localhost:8080/faculty/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        facultyName: faculty.name,
        location: faculty.location,
      }),
    })
      .then(() =>
        setFacultys([
          ...facultys,
          {
            facultyName: faculty.name,
            location: faculty.location,
          },
        ])
      )
      .catch((e) => console.log(e));
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
