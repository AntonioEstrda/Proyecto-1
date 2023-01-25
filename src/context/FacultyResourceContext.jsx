import { createContext, useState, useEffect } from "react";

export const FacultyResourceContext = createContext();

export function FacultyResourceContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingFacultyResource, setEditingFacultyResource] = useState();
  const [facultyResources, setFacultyResources] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setFacultyResources(data);
      });
  }, []);

  async function create(facultyResource) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: facultyResource.name,
        initDate: facultyResource.initDate,
        finalDate: facultyResource.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setFacultyResources((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(facultyResourceID) {
    await fetch(url + "delete/" + facultyResourceID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setFacultyResources(
          facultyResources.filter(
            (facultyResource) =>
              facultyResource.facultyResourceID !== facultyResourceID
          )
        )
      )
      .catch((e) => console.log(e));
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
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        facultyResources[facultyResources.indexOf(editingFacultyResource)] =
          prevFacultyResource;
        setFacultyResources(facultyResources);
      })
      .then(() => setEditingFacultyResource(null))
      .catch((e) => console.log(e));
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
      }}
    >
      {props.children}
    </FacultyResourceContext.Provider>
  );
}
