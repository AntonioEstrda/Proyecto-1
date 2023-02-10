import { createContext, useState, useEffect } from "react";

export const HourlyAssignmentContext = createContext();

export function HourlyAssignmentContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingHourlyAssignment, setEditingHourlyAssignment] = useState();
  const [hourlyAssignments, setHourlyAssignments] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setHourlyAssignments(data);
      });
  }, []);

  async function create(hourlyAssignment) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: hourlyAssignment.name,
        initDate: hourlyAssignment.initDate,
        finalDate: hourlyAssignment.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setHourlyAssignments((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.error(e));
  }

  async function deleteById(hourlyAssignmentID) {
    await fetch(url + "delete/" + hourlyAssignmentID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setHourlyAssignments(
          hourlyAssignments.filter(
            (hourlyAssignment) =>
              hourlyAssignment.hourlyAssignmentID !== hourlyAssignmentID
          )
        )
      )
      .catch((e) => console.error(e));
  }

  async function update(prevHourlyAssignment) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevHourlyAssignment),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        hourlyAssignments[hourlyAssignments.indexOf(editingHourlyAssignment)] =
          prevHourlyAssignment;
        setHourlyAssignments(hourlyAssignments);
      })
      .then(() => setEditingHourlyAssignment(null))
      .catch((e) => console.error(e));
  }

  return (
    <HourlyAssignmentContext.Provider
      value={{
        hourlyAssignments,
        editingHourlyAssignment,
        create,
        update,
        deleteById,
        setEditingHourlyAssignment,
      }}
    >
      {props.children}
    </HourlyAssignmentContext.Provider>
  );
}
