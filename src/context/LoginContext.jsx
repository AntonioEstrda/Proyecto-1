import { useCallback, useContext } from "react";
import { createContext, useState, useEffect } from "react";
import AppMenu from "../apps/adminUser/AppMenu";
import { TeacherContext } from "../context/TeacherContext";


export const LoginContext = createContext();

export function LoginContextProvider(props) {
  const url = "http://localhost:8080/teacher/";

  const { teachers } = useContext(TeacherContext);

  const [editingLogin, setEditingLogin] = useState();
  const [stateLogin, setLogin] = useState(false);

  const login = useCallback();

  async function session(password) {
    await fetch(url + password, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    });
  }

  async function session(username, password) {
    if (password == "admin" && username == "admin") {
      alert("Bienvenidos");
      setLogin(true);
      window.location.href = "./home";
    } else {
      alert("Credenciales incorrectas");
      setLogin(false);
    }
    return stateLogin;
  }

  return (
    <LoginContext.Provider
      value={{
        session,
        teachers,
        setLogin,
        stateLogin,
      }}
    >
      {props.children}
    </LoginContext.Provider>
  );
}
