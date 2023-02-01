import { createContext, useState, useEffect, useContext } from "react";
import { LocationContext } from "../context/LocationContext";
import { FacultyContext } from "../context/FacultyContext";

export const DepartmentContext = createContext();

export function DepartmentContextProvider(props) {
  const url = "http://localhost:8080/department/";

  const { locations } = useContext(LocationContext);
  const { facultys } = useContext(FacultyContext);

  const [editingDepartment, setEditingDepartment] = useState();
  const [departments, setDepartments] = useState([]);
  const [idLocationSelected, setIdLocationSelected] = useState(0);
  const [idFacultySelected, setIdFacultySelected] = useState(0);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setDepartments(data);
      });
  }, []);

  async function create(department) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(department),
    })
      .then((response) => response.json())
      .then((data) => {
        setDepartments((prevState) => prevState.concat([data]));
        setEditingDepartment(null);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(departmentId) {
    await fetch(url + "delete/" + departmentId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setDepartments(
          departments.filter(
            (department) => department.departmentId !== departmentId
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevDepartment) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevDepartment),
    })
      .then((response) => response.json())
      .then((data) => {
        departments[departments.indexOf(editingDepartment)] = prevDepartment;
        setDepartments(departments);
        setEditingDepartment(null);
        setIdLocationSelected(0);
        setIdFacultySelected(0);
      })
      .catch((e) => console.log(e));
  }

  return (
    <DepartmentContext.Provider
      value={{
        departments,
        editingDepartment,
        create,
        update,
        deleteById,
        setEditingDepartment,
        setIdLocationSelected,
        idLocationSelected,
        locations,
        idFacultySelected,
        setIdFacultySelected,
        facultys,
      }}
    >
      {props.children}
    </DepartmentContext.Provider>
  );
}
