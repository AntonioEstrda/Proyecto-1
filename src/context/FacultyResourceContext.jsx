import { createContext, useState, useEffect, useContext } from "react";
import { FacultyContext } from "../context/FacultyContext";
import { ResourceContext } from "../context/ResourceContext";
import { ResourceTypeContext } from "../context/ResourceTypeContext";

export const FacultyResourceContext = createContext();

export function FacultyResourceContextProvider(props) {
  const url = "http://localhost:8080/FacultyResource/";

  const { facultys } = useContext(FacultyContext);
  const { resources } = useContext(ResourceContext);
  const { resourceTypes } = useContext(ResourceTypeContext);

  const [editingFacultyResource, setEditingFacultyResource] = useState();
  const [facultyResources, setFacultyResources] = useState([]);
  const [idResourceSelected, setIdResourceSelected] = useState(0);
  const [idFacultySelected, setIdFacultySelected] = useState(0);
  useEffect(() => {
    facultys.forEach((faculty) => {
      resourceTypes.forEach((resourceType) => {
        fetch(
          url +
            "findByType?" +
            new URLSearchParams({
              facultyId: faculty.facultyId,
            }) +
            "&" +
            new URLSearchParams({
              resTypeId: resourceType.resourceTypeId,
            })
        )
          .then((response) => response.json())
          .then((data) => {
            setFacultyResources(...facultyResources, data);
          })
          .catch((e) => console.error(e));
      });
    });
  }, []);

  async function create(facultyResource, facultyId, resourceId) {
    await fetch(
      url +
        "assing?" +
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
        response.json(), response.headers.get("errors");
      })
      .then((data) => {
        setFacultyResources((prevState) => prevState.concat([data]));
        setIdFacultySelected(0);
        setIdResourceSelected(0);
      })
      .catch((e) => console.error(e));
  }

  async function deleteById(facultyId, resourceId) {
    await fetch(
      url +
        "unassign?" +
        new URLSearchParams({
          facultyId,
        }) +
        "&" +
        new URLSearchParams({
          resourceId,
        }),
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        mode: "cors",
      }
    )
      .then(
        () => {
          setFacultyResources(
            facultyResources.filter(
              (facultyResource) => facultyResource.facResId !== facResId
            )
          );
        },
        idFacultySelected,
        idResourceSelected
      )
      .catch((e) => console.error(e));
  }

  async function update(prevFacultyResource) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevFacultyResource),
    })
      .then((response) => response.json())
      .then((data) => {
        data.registrerDate = data.registrerDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        facultyResources[facultyResources.indexOf(editingFacultyResource)] =
          prevFacultyResource;
        setFacultyResources(facultyResources);
      })
      .then(() => setEditingFacultyResource(null))
      .catch((e) => console.error(e));
  }

  return (
    <FacultyResourceContext.Provider
      value={{
        facultyResources,
        editingFacultyResource,
        create,
        update,
        deleteById,
        setEditingFacultyResource,
        idResourceSelected,
        setIdResourceSelected,
        resourceTypes,
        resources,
        idFacultySelected,
        setIdFacultySelected,
        facultys,
      }}
    >
      {props.children}
    </FacultyResourceContext.Provider>
  );
}
