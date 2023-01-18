import { createContext, useState, useEffect } from "react";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const url = "http://localhost:8080/faculty/";

  const [locations, setLocations] = useState([]);
  const [facultys, setFacultys] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        const ctx = data.map((d) => ({
          ...d,
          resources: [],
        }));
        setFacultys(ctx);
      });
  }, []);

  function deleteById(facultyId) {
    setFacultys(facultys.filter((faculty) => faculty.id !== facultyId));
  }

  async function create(faculty) {
    await fetch(url, {
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
      .then((response) => response.json())
      .then((data) => setFacultys((prevState) => prevState.concat([data])))
      .catch((e) => console.log(e));
  }

  return (
    <FacultyContext.Provider
      value={{
        facultys,
        create,
        deleteById,
      }}
    >
      {props.children}
    </FacultyContext.Provider>
  );
}
