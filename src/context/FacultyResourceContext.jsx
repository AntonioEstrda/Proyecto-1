import { createContext, useState, useEffect, useContext } from "react";
import { AlertContext } from "./AlertContext";

export const FacultyResourceContext = createContext();

export function FacultyResourceContextProvider(props) {
  const [idResourceSelected, setIdResourceSelected] = useState(-1);
  const [idFacultySelected, setIdFacultySelected] = useState(-1);
  const [facultys, setFacultys] = useState([]);
  const [resources, setResources] = useState(new Map());
  const { setAlert } = useContext(AlertContext);
  useEffect(() => {
    fetch("http://localhost:8080/faculty/all")
      .then((response) => response.json())
      .then((data) => {
        setFacultys(data);
      });
  }, []);

  useEffect(() => {
    const recursosMap = new Map();
    facultys.forEach((faculty) => {
      fetch(
        "http://localhost:8080/Resource/all?" +
          new URLSearchParams({
            facultyId: faculty.facultyId,
          })
      )
        .then((response) => {
          if (response.ok) return response.json();
          return Promise.reject(response);
        })
        .then((data) => {
          if (data.length > 0) {
            recursosMap.set(+faculty.facultyId, data);
          }
        })
        .catch((e) => {
          if (e.status === 400) setAlert(e.headers.get("errors"));
          console.error(
            "[ERROR] Ocurrió un error inesperado en cargar los recursos"
          );
        });
    });
    setResources(recursosMap);
  }, [facultys]);

  async function create(facultyResource, facultyId, resourceId) {
    console.log(facultyId, resourceId);
    await fetch(
      "http://localhost:8080/FacultyResource/assign?" +
        new URLSearchParams({
          facultyId,
        }) +
        "&" +
        new URLSearchParams({
          resourceId,
        }),
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify(facultyResource),
      }
    )
      .then((response) => {
        if (response.ok) setAlert(["Asignado", "Asignación correcta"]);
        return Promise.reject(response);
      })
      .catch((e) => {
        if (e.headers) setAlert(e.headers.get("errors"));
        else setAlert("[unespecified]");
      });
  }

  return (
    <FacultyResourceContext.Provider
      value={{
        create,
        idResourceSelected,
        setIdResourceSelected,
        idFacultySelected,
        setIdFacultySelected,
        resources,
        facultys,
      }}
    >
      {props.children}
    </FacultyResourceContext.Provider>
  );
}
