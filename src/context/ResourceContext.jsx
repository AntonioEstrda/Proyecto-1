import { createContext, useState, useEffect, useContext } from "react";
import { FacultyContext } from "./FacultyContext";

export const ResourceContext = createContext();

export function ResourceContextProvider(props) {
  const [resources, setResources] = useState([]);
  const [resourcesTypes, setResourcesTypes] = useState([]);
  const { facultys, addResource } = useContext(FacultyContext);

  function deleteResource(resourceId) {
    setResources(
      resources.filter((resource) => resource.resourceId !== resourceId)
    );
  }

  function createResource(resource, resourceType) {
    setResources([
      ...resources,
      {
        name: resource.name,
        resourceId: resources.length,
        description: resource.description,
        resourceType: {
          name: resourceType.name,
          parent: resourceType.parent,
          resourceTypeId: resourceType.resourceTypeId,
          disable: resourceType.disable,
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
    fetch("http://localhost:8080/ResourceType/all")
      .then((response) => response.json())
      .then((data) => {
        setResourcesTypes(data);
      });
  }, []);

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
        resourcesTypes,
        deleteResource,
        createResource,
      }}
    >
      {props.children}
    </ResourceContext.Provider>
  );
}
