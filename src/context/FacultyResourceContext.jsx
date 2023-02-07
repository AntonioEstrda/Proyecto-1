import { createContext, useState, useEffect, useContext } from "react";
import { FacultyContext } from "../context/FacultyContext";
import { ResourceContext } from "../context/ResourceContext";

export const FacultyResourceContext = createContext();

export function FacultyResourceContextProvider(props) {
  const url = "http://localhost:8080/FacultyResource/";

  const { facultys } = useContext(FacultyContext);
  const { resources } = useContext(ResourceContext);

  const [editingFacultyResource, setEditingFacultyResource] = useState();
  const [facultyResources, setFacultyResources] = useState([]);
  const [idResourceSelected, setIdResourceSelected] = useState(0);
  const [idFacultySelected, setIdFacultySelected] = useState(0);
  useEffect(() => {
    facultys.forEach((faculty) => {
      fetch(
        url +
          "findByType?" +
          new URLSearchParams({
            facultyId: faculty.facultyId,
          })
      )
        .then((response) => response.json())
        .then((data) => {
          setFacultyResources(...facultyResources, data);
        })
        .catch((e) => console.log(e));
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
