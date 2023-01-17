import { createContext, useState, useEffect } from "react";

export const ResourceTypeContext = createContext();

export function ResourceTypeContextProvider(props) {
  const url = "http://localhost:8080/ResourceType/";

  const [editingresourceType, setEditingResourceType] = useState();
  const [resourcesTypes, setResourcesTypes] = useState([]);

  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setResourcesTypes(data);
      });
  }, []);

  async function createResourceType(resourceType) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: resourceType.name,
        resourceTypeId: resourceType.resourceTypeId,
        parent: resourceType.parent,
        disable: resourceType.disable,
      }),
    })
      .then((response) => response.json())
      .then((data) =>
        setResourcesTypes((prevState) => prevState.concat([data]))
      )
      .catch((e) => console.log(e));
  }

  async function deleteResourceType(resourceTypeId) {

    await fetch(url + "delete/" + resourceTypeId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setResourcesTypes(
          resourcesTypes.filter( (resourceType) => resourceType.resourceTypeId !== resourceTypeId) )
      )
      .catch((e) => console.log(e));
}  


  return (
    <ResourceTypeContext.Provider
      value={{
        resourcesTypes,
        editingresourceType,
        deleteResourceType,
        createResourceType,
        setEditingResourceType,
      }}
    >
      {props.children}
    </ResourceTypeContext.Provider>
  );


}
