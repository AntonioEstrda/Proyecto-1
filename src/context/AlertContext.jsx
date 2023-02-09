import { createContext, useState } from "react";

export const AlertContext = createContext();

export function AlertContextProvider(props) {
  const [alert, setAlert] = useState();

  function closeAlert() {
    setAlert();
  }

  return (
    <AlertContext.Provider
      value={{
        alert,
        setAlert,
        closeAlert,
      }}
    >
      {props.children}
    </AlertContext.Provider>
  );
}
