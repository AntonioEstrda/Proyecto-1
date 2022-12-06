import { createContext, useState, useEffect } from "react";

export const FacultyContext = createContext();

export function FacultyContextProvider(props) {
  const [facultys, setFacultys] = useState([]);
  const [loading, setLoading] = useState(true);

  function deleteFaculty(facultyId) {
    setFacultys(facultys.filter((faculty) => faculty.id !== facultyId));
  }

  const addResource = ({ resource, id }) => {
    const aux = facultys.find((fc) => fc.facultyId === id);
    aux.resources.push(...resource);
    const dsd = facultys.filter((res) => res.facultyId !== id);
    setFacultys([...dsd, aux]);
  };

  //facultys[index].resources += resource

  async function createFaculty(faculty) {
    await fetch("http://localhost:8080/faculty/", {
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

  useEffect(() => {
    fetch("http://localhost:8080/faculty/all")
      .then((response) => response.json())
      .then((data) => {
        const ctx = data.map((d) => ({
          ...d,
          resources: [],
        }));
        setFacultys(ctx);
      })
      .finally(() => setLoading(false));
  }, []);

  if (loading) return null;

  return (
    <FacultyContext.Provider
      value={{
        facultys,
        deleteFaculty,
        createFaculty,
        addResource,
      }}
    >
      {props.children}
    </FacultyContext.Provider>
  );
}
