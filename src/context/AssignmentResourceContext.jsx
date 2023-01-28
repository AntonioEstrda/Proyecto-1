import { createContext, useState, useEffect } from "react";

export const AssignmentResourceContext = createContext();

export function AssignmentResourceContextProvider(props) {
  const url = "http://localhost:8080/AssignmentResource/";

  // TODO acomodar para los endpoints y los llamados de assignmentResource
  const [editingAssignmentResource, setEditingAssignmentResource] = useState();
  const [assignmentResources, setAssignmentResources] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setAssignmentResources(data);
      });
  }, []);

  async function create(assignmentResource) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: assignmentResource.name,
        initDate: assignmentResource.initDate,
        finalDate: assignmentResource.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setAssignmentResources((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(assignmentResourceID) {
    await fetch(url + "delete/" + assignmentResourceID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setAssignmentResources(
          assignmentResources.filter(
            (assignmentResource) =>
              assignmentResource.assignmentResourceID !== assignmentResourceID
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevAssignmentResource) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevAssignmentResource),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        assignmentResources[assignmentResources.indexOf(editingAssignmentResource)] =
          prevAssignmentResource;
        setAssignmentResources(assignmentResources);
      })
      .then(() => setEditingAssignmentResource(null))
      .catch((e) => console.log(e));
  }

  return (
    <AssignmentResourceContext.Provider
      value={{
        assignmentResources,
        editingAssignmentResource,
        create,
        update,
        deleteById,
        setEditingAssignmentResource,
      }}
    >
      {props.children}
    </AssignmentResourceContext.Provider>
  );
}
