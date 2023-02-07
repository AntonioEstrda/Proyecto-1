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
  const [resources, setResources] = useState([]);
  const [idResourceTypeSelected, setIdResourceTypeSelected] = useState(0);
  const [idLocationSelected, setIdLocationSelected] = useState(0);
  const [idFacultySelected, setIdFacultySelected] = useState(0);
  useEffect(() => {
    facultys.forEach((faculty) => {
      fetch(
        url +
          "all?" +
          new URLSearchParams({
            facultyId: faculty.facultyId,
          })
      )
        .then((response) => response.json())
        .then((data) => {
          setResources(...resources, data);
        })
        .catch((e) => console.log(e));
    });
  }, [facultys]);

  async function create(resource, facultyId) {
    await fetch(
      url +
        "add?" +
        new URLSearchParams({
          facultyId,
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
      .then((response) => response.json())
      .then((data) => {
        setResources((prevState) => prevState.concat([data]));
        setEditingResource(null);
        setIdResourceTypeSelected(0);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(facultyId, resourceId) {
    //console.log(facultyId, resourceId);
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
            //resources.filter((faculty) => faculty.facultyId !== facultyId),
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

  //console.log(facultys);
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
      }}
    >
      {props.children}
    </ResourceContext.Provider>
  );
}
