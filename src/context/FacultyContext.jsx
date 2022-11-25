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
        name: faculty.name,
        id: facultys.length,
        location: faculty.location,
      },
    ]);
  }

  useEffect(() => {
    setFacultys(data);
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
