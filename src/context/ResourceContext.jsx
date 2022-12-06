import { createContext, useState, useEffect, useContext } from "react";
import { FacultyContext } from "./FacultyContext";

export const ResourceContext = createContext();

export function ResourceContextProvider(props) {
  const [resources, setResources] = useState([]);
  const { facultys, addResource } = useContext(FacultyContext);

  function deleteResource(resourceId) {
    setResources(
      resources.filter((resource) => resource.resourceId !== resourceId)
    );
  }

  function createResource(resource) {
    setResources([
      ...resources,
      {
        name: resource.name,
        resourceId: resources.length,
        description: resource.description,
        resourceType: {
          name: resource.resourceType,
          parent: resource.parent,
          resourceTypeId: resource.resourceTypeId,
          disable: resource.disable,
        },
        code: resource.code,
        number: resource.number,
        location: resource.location,
        capacity: resource.capacity,
        disable: resource.disable,
      },
    ]);
  }

  useEffect(() => {
    facultys.forEach((faculty) => {
      fetch(
        "http://localhost:8080/Resource/all?" +
          new URLSearchParams({
            facultyId: faculty.facultyId,
          })
      )
        .then((response) => response.json())
        .then((data) =>
          addResource({
            id: faculty.facultyId,
            resource: data,
          })
        );
    });
  }, []);

  return (
    <ResourceContext.Provider
      value={{
        resources,
        deleteResource,
        createResource,
      }}
    >
      {props.children}
    </ResourceContext.Provider>
  );
}
