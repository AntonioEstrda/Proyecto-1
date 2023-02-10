import { createContext, useState, useEffect, useContext } from "react";
import { ResourceTypeContext } from "../context/ResourceTypeContext";
import { LocationContext } from "../context/LocationContext";
import { FacultyContext } from "../context/FacultyContext";
import { AlertContext } from "../context/AlertContext";

export const ResourceContext = createContext();

export function ResourceContextProvider(props) {
  const url = "http://localhost:8080/Resource/";

  const { resourceTypes } = useContext(ResourceTypeContext);
  const { locations } = useContext(LocationContext);
  const { facultys } = useContext(FacultyContext);
  const { alert, setAlert, closeAlert } = useContext(AlertContext);

  const [editingResource, setEditingResource] = useState();
  const [resources, setResources] = useState(new Map());
  const [idResourceTypeSelected, setIdResourceTypeSelected] = useState(-1);
  const [idLocationSelected, setIdLocationSelected] = useState(-1);
  const [idFacultySelected, setIdFacultySelected] = useState(-1);

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
        const recursosMap = resources;
        setResources(
          recursosMap.set(
            +facultyId,
            recursosMap.get(+facultyId).concat([data])
          )
        );
        setAlert(["Crear", "Recurso creado con éxito"]);
        setEditingResource(null);
        setIdResourceTypeSelected(0);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      })
      .catch((e) => {
        if (e.headers?.has("errors")) setAlert(e.headers.get("errors"));
        else setAlert("[unespecified]");
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
      .then(() => {
        const recursosMap = resources;
        setResources(
          recursosMap.set(
            +facultyId,
            recursosMap
              .get(+facultyId)
              .filter((resource) => resource.resourceId !== resourceId)
          )
        );
        setAlert(["Eliminar", "Recurso eliminado con éxito"]);
      })
      .catch((e) => console.log(e));
  }

  async function update(prevResource, facultyId) {
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
        const recursosMap = resources;
        let recursosFacultad = resources.get(+facultyId);
        recursosFacultad[recursosFacultad.indexOf(editingResource)] =
          prevResource;
        setResources(recursosMap.set(+facultyId, recursosFacultad));
        setAlert(["Actualizar", "Recurso actualizado con éxito"]);

        setEditingResource(null);
        setIdResourceTypeSelected(0);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      }, idFacultySelected)
      .catch((e) => console.log(e));
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
