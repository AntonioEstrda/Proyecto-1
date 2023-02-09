import { createContext, useState, useEffect, useContext } from "react";
import { TeacherContext } from "../context/TeacherContext";
import { AcademicPeriodContext } from "../context/AcademicPeriodContext";
import { DepartmentContext } from "../context/DepartmentContext";

export const EventContext = createContext();

export function EventContextProvider(props) {
  const url = "http://localhost:8080/Event/";

  const { teachers } = useContext(TeacherContext);
  const { departments } = useContext(DepartmentContext);
  const { academicPeriods } = useContext(AcademicPeriodContext);

  const [editingEvent, setEditingEvent] = useState();
  const [events, setEvents] = useState([]);
  const [idTeacherSelected, setIdTeacherSelected] = useState(0);
  const [idDepartmentSelected, setIdDepartmentSelected] = useState(0);
  const [idAPSelected, setIdAPSelected] = useState(0);
  useEffect(() => {
    departments.forEach((department) => {
      fetch(
        url +
          "findByDepartmentOrProgram?" +
          new URLSearchParams({
            departmentId: department.departmentId,
          })
      )
        .then((response) => response.json())
        .then((data) => {
          setEvents(data);
        })
        .catch((e) => console.log(e));
    });
  }, []);

  async function create(event) {
    await fetch(url + "add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(event),
    })
      .then((response) => response.json())
      .then((data) => {
        setEvents((prevState) => prevState.concat([data]));
        setEditingEvent(null);
        setIdTeacherSelected(0);
        setIdDepartmentSelected(0);
        setIdAPSelected(0);
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(eventID, departmentId) {
    await fetch(url + "delete?" + eventID + "&" + departmentId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(
        () => {
          setEvents(events.filter((event) => event.id !== eventID));
        },
        eventID,
        idDepartmentSelected
      )
      .catch((e) => console.log(e));
  }

  async function update(prevEvent) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevEvent),
    })
      .then((response) => response.json())
      .then((data) => {
        events[events.indexOf(editingEvent)] = prevEvent;
        setEvents(events);
        setEditingEvent(null);
        setIdTeacherSelected(0);
        setIdDepartmentSelected(0);
        setIdAPSelected(0);
      })
      .catch((e) => console.log(e));
  }

  console.log(events);
  return (
    <EventContext.Provider
      value={{
        events,
        editingEvent,
        create,
        update,
        deleteById,
        setEditingEvent,
        idTeacherSelected,
        idDepartmentSelected,
        idAPSelected,
        setIdTeacherSelected,
        setIdDepartmentSelected,
        setIdAPSelected,
        teachers,
        departments,
        academicPeriods,
      }}
    >
      {props.children}
    </EventContext.Provider>
  );
}
