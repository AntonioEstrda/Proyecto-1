import { createContext, useState, useEffect } from "react";

export const ResourceTypeContext = createContext();

export function ResourceTypeContextProvider(props) {
  const url = "http://localhost:8080/ResourceType/";

  const [editingResourceType, setEditingResourceType] = useState();
  const [resourceTypes, setResourceTypes] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setResourceTypes(data);
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
        parent: resourceType.parent,
        isDisable: resourceType.isDisable,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        setResourceTypes((prevState) => prevState.concat([data]));
      })
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
        setResourceTypes(
          resourceTypes.filter((resourceType) => resourceType.resourceTypeId !== resourceTypeId)
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
      .then(() => {
        resourceTypes[resourceTypes.indexOf(editingResourceType)] = prevResourceType;
        setResourceTypes(resourceTypes);
      })
      .then(() => setEditingResourceType(null))
      .catch((e) => console.log(e));
  }

  return (
    <ResourceTypeContext.Provider
      value={{
        resourceTypes,
        editingResourceType,
        create,
        update,
        deleteById,
        setEditingResourceType,
      }}
    >
      {props.children}
    </ResourceTypeContext.Provider>
  );
}

