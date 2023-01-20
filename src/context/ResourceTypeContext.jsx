import { createContext, useState, useEffect } from "react";

export const ResourceTypeContext = createContext();

export function ResourceTypeContextProvider(props) {
  const url = "http://localhost:8080/ResourceType/";

  const [editingResourceType, setEditingResourceType] = useState();
  const [resourcesTypes, setResourcesTypes] = useState([]);

  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setResourcesTypes(data);
      });
  }, []);

  async function create(resourceType) {
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

  async function deleteById(resourceTypeId) {
    await fetch(url + "delete/" + resourceTypeId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setResourcesTypes(
          resourcesTypes.filter(
            (resourceType) => resourceType.resourceTypeId !== resourceTypeId
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevResourceType) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevResourceType),
    })
      .then((response) => response.json())
      .then((data) => {
        resourcesTypes[resourcesTypes.indexOf(editingResourceType)] =
          prevResourceType;
        setResourcesTypes(resourcesTypes);
      })
      .then(() => setEditingResourceType(null))
      .catch((e) => console.log(e));
  }

  return (
    <ResourceTypeContext.Provider
      value={{
        resourcesTypes,
        editingResourceType,
        deleteById,
        create,
        update,
        setEditingResourceType,
      }}
    >
      {props.children}
    </ResourceTypeContext.Provider>
  );
}
