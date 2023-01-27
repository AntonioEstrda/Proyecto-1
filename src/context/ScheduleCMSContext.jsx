import { createContext, useState, useEffect } from "react";

export const ScheduleCMSContext = createContext();

export function ScheduleCMSContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingScheduleCMS, setEditingScheduleCMS] = useState();
  const [scheduleCMSs, setScheduleCMSs] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setScheduleCMSs(data);
      });
  }, []);

  async function create(scheduleCMS) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: scheduleCMS.name,
        initDate: scheduleCMS.initDate,
        finalDate: scheduleCMS.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setScheduleCMSs((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.log(e));
  }

  async function deleteById(scheduleCMSID) {
    await fetch(url + "delete/" + scheduleCMSID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setScheduleCMSs(
          scheduleCMSs.filter(
            (scheduleCMS) =>
              scheduleCMS.scheduleCMSID !== scheduleCMSID
          )
        )
      )
      .catch((e) => console.log(e));
  }

  async function update(prevScheduleCMS) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevScheduleCMS),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        scheduleCMSs[scheduleCMSs.indexOf(editingScheduleCMS)] =
          prevScheduleCMS;
        setScheduleCMSs(scheduleCMSs);
      })
      .then(() => setEditingScheduleCMS(null))
      .catch((e) => console.log(e));
  }

  return (
    <ScheduleCMSContext.Provider
      value={{
        scheduleCMSs,
        editingScheduleCMS,
        create,
        update,
        deleteById,
        setEditingScheduleCMS,
      }}
    >
      {props.children}
    </ScheduleCMSContext.Provider>
  );
}
