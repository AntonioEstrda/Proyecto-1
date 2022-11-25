import { createContext, useState, useEffect } from "react";
import { resources as data } from "../data/resources";

export const ResourceContext = createContext();

export function ResourceContextProvider(props) {
  const [resources, setResources] = useState([]);

  function deleteResource(resourceId) {
    setResources(
      resources.filter((resource) => resource.resourceId !== resourceId)
    );
  }

  function createResource(resource) {
    console.log(resource);
    setResources([
      ...resources,
      {
        name: resource.name,
        resourceId: resources.length,
        description: resource.description,
        resourceType: {
          name: resource.resourceType,
          parent: null,
          resourceTypeId: null,
          disable: false,
        },
        disable: resource.disable,
      },
    ]);
  }

  useEffect(() => {
    setResources(data);
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
