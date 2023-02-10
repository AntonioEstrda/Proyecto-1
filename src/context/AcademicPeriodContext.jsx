import { createContext, useState, useEffect } from "react";

export const AcademicPeriodContext = createContext();

export function AcademicPeriodContextProvider(props) {
  const url = "http://localhost:8080/academicperiod/";

  const [editingAcademicPeriod, setEditingAcademicPeriod] = useState();
  const [academicPeriods, setAcademicPeriods] = useState([]);
  useEffect(() => {
    fetch(url + "all")
      .then((response) => response.json())
      .then((data) => {
        setAcademicPeriods(data);
      });
  }, []);

  async function create(academicPeriod) {
    await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: academicPeriod.name,
        initDate: academicPeriod.initDate,
        finalDate: academicPeriod.finalDate,
      }),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        setAcademicPeriods((prevState) => prevState.concat([data]));
      })
      .catch((e) => console.error(e));
  }

  async function deleteById(academicPeriodID) {
    await fetch(url + "delete/" + academicPeriodID, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() =>
        setAcademicPeriods(
          academicPeriods.filter(
            (academicPeriod) =>
              academicPeriod.academicPeriodID !== academicPeriodID
          )
        )
      )
      .catch((e) => console.error(e));
  }

  async function update(prevAcademicPeriod) {
    await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevAcademicPeriod),
    })
      .then((response) => response.json())
      .then((data) => {
        data.initDate = data.initDate.split("T")[0];
        data.finalDate = data.finalDate.split("T")[0];
        academicPeriods[academicPeriods.indexOf(editingAcademicPeriod)] =
          prevAcademicPeriod;
        setAcademicPeriods(academicPeriods);
      })
      .then(() => setEditingAcademicPeriod(null))
      .catch((e) => console.error(e));
  }

  return (
    <AcademicPeriodContext.Provider
      value={{
        academicPeriods,
        editingAcademicPeriod,
        create,
        update,
        deleteById,
        setEditingAcademicPeriod,
      }}
    >
      {props.children}
    </AcademicPeriodContext.Provider>
  );
}
