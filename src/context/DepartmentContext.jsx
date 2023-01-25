import { createContext, useState, useEffect } from "react";

export const DepartmentContext = createContext();

export function DepartmentContextProvider(props) {
  const url = "http://localhost:8080/department/";

  const [editingDepartment, setEditingDepartment] = useState();
  const [departments, setDepartments] = useState([]);
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
      body: JSON.stringify({
        name: department.name,
        initDate: department.initDate,
        finalDate: department.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setDepartments((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(departmentID) {
    await fetch(url + "delete/" + departmentID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setDepartments(
          departments.filter(
            (department) =>
              department.departmentID !== departmentID
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
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        departments[departments.indexOf(editingDepartment)] =
          prevDepartment;
        setDepartments(departments);
      })
      .then(() => setEditingDepartment(null))
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
      }}
    >
      {props.children}
    </DepartmentContext.Provider>
  );
}
