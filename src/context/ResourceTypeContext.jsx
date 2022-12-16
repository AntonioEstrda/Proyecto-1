import { createContext, useState, useEffect } from "react";

export const ResourceTypeContext = createContext();

export function ResourceTypeContextProvider(props) {
  const [resourcesTypes, setResourcesTypes] = useState([]);

  async function createResourceType(resourceTypes) {
    await fetch("http://localhost:8080/ResourceType/", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: resourceTypes.name,
        resourceTypeId: resourceTypes.resourceTypeId,
        parent: resourceTypes.parent,
        disable: resourceTypes.disable,
      }),
    })
      .then((response) => response.json())
      .then((data) =>
        setResourcesTypes((prevState) => prevState.concat([data]))
      )
      .catch((e) => console.log(e));
  }

  function deleteResourceType(resourceTypeId) {
    setTask(
        resourcesTypes.filter((resourceType) => resourceType.resourceTypeId !== resourceTypeId)
    );
  }

  useEffect(() => {
    fetch("http://localhost:8080/ResourceType/all")
      .then((response) => response.json())
      .then((data) => {
        setResourcesTypes(data);
      });
  }, []);

  return (
    <ResourceTypeContextProvider.Provider
      value={{
        resourcesTypes,
        deleteResourceType,
        createResourceType,
      }}
    >
      {props.children}
    </ResourceTypeContextProvider.Provider>
  );
}
