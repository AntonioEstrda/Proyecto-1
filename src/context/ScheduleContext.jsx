import { createContext, useState, useEffect } from "react";

export const ScheduleContext = createContext();

export function ScheduleContextProvider(props) {
  const [grupos, setGrupos] = useState();
  useEffect(() => {
    fetch("http://localhost:8080/groupt/all")
      .then((response) => response.json())
      .then((data) => {
        setGrupos(data);
      })
      .catch((e) => console.log(e));
  }, []);

  if (!grupos) return null;

  return (
    <ScheduleContext.Provider value={{ grupos }}>
      {props.children}
    </ScheduleContext.Provider>
  );
}
