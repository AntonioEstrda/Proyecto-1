import { createContext, useState, useEffect, useContext } from "react";
import { ResourceTypeContext } from "../context/ResourceTypeContext";
import { LocationContext } from "../context/LocationContext";
import { FacultyContext } from "../context/FacultyContext";

export const ResourceContext = createContext();

export function ResourceContextProvider(props) {
  const url = "http://localhost:8080/Resource/";

  const { resourceTypes } = useContext(ResourceTypeContext);
  const { locations } = useContext(LocationContext);
  const { facultys } = useContext(FacultyContext);

  const [editingResource, setEditingResource] = useState();
  const [resources, setResources] = useState(new Map());
  const [idResourceTypeSelected, setIdResourceTypeSelected] = useState(-1);
  const [idLocationSelected, setIdLocationSelected] = useState(-1);
  const [idFacultySelected, setIdFacultySelected] = useState(-1);
  const [alert, setAlert] = useState();

  useEffect(() => {
    loadResources();
    setResources(recursosMap);
  }, [facultys]);

  const recursosMap = new Map();
  async function loadResources() {
    facultys.forEach((faculty) => {
      fetch(
        url +
          "all?" +
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
            recursosMap.set(faculty.facultyId, data);
          }
        })
        .catch((e) => {
          if (e.status === 400) setAlert(e.headers.get("errors"));
          console.error(
            "[ERROR] Ocurrió un error inesperado en cargar los recursos"
          );
        });
    });
  }

  async function create(resource, facultyId) {
    await fetch(
      url +
        "add?" +
        new URLSearchParams({
          facultyId: +facultyId,
        }),
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify(resource),
      }
    )
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        console.log(recursosMap.get(facultyId));
        recursosMap.get(facultyId).concat([data]);
        console.log(recursosMap.get(facultyId));
        setResources(new Map(recursosMap));
        setEditingResource(null);
        setIdResourceTypeSelected(0);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      })
      .catch((e) => {
        setAlert(e.headers.get("errors"));
      });
  }

  async function deleteById(facultyId, resourceId) {
    await fetch(
      url +
        "delete?" +
        new URLSearchParams({
          facultyId,
        }) +
        "&" +
        new URLSearchParams({
          resourceId,
        }),
      {
        method: "DELETE",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
      }
    )
      .then(
        () => {
          setResources(
            resources.filter((resource) => resource.resourceId !== resourceId)
          );
          console.log(facultyId, resourceId); /////////
        },
        idFacultySelected,
        resourceId
      )
      .catch((e) => console.log(e));
  }

  async function update(prevResource, facultyId) {
    console.log(prevResource, facultyId);
    await fetch(
      url +
        "update?" +
        new URLSearchParams({
          facultyId,
        }),
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify(prevResource),
      }
    )
      .then((response) => response.json())
      .then(() => {
        resources[resources.indexOf(editingResource)] = prevResource;
        setResources(resources);
        setEditingResource(null);
        setIdResourceTypeSelected(0);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      }, idFacultySelected)
      .catch((e) => console.log(e));
  }

  function closeAlert() {
    setAlert();
  }

  return (
    <ResourceContext.Provider
      value={{
        resources,
        editingResource,
        create,
        update,
        deleteById,
        setEditingResource,
        idResourceTypeSelected,
        setIdResourceTypeSelected,
        resourceTypes,
        setIdLocationSelected,
        idLocationSelected,
        locations,
        idFacultySelected,
        setIdFacultySelected,
        facultys,
        alert,
        closeAlert,
      }}
    >
      {props.children}
    </ResourceContext.Provider>
  );
}
