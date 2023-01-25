import { createContext, useState, useEffect } from "react";

export const EventContext = createContext();

export function EventContextProvider(props) {
  const url = "http://localhost:8080/Event/";

  const [editingEvent, setEditingEvent] = useState();
  const [events, setEvents] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setEvents(data);
      });
  }, []);

  async function create(event) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: event.name,
        initDate: event.initDate,
        finalDate: event.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setEvents((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(eventID) {
    await fetch(url + "delete/" + eventID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setEvents(
          events.filter(
            (event) =>
              event.eventID !== eventID
          )
        )
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
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        events[events.indexOf(editingEvent)] =
          prevEvent;
        setEvents(events);
      })
      .then(() => setEditingEvent(null))
      .catch((e) => console.log(e));
  }

  return (
    <EventContext.Provider
      value={{
        events,
        editingEvent,
        create,
        update,
        deleteById,
        setEditingEvent,
      }}
    >
      {props.children}
    </EventContext.Provider>
  );
}
