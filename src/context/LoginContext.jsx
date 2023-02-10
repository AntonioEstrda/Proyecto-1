import { useCallback, useContext, useRef } from "react";
import { createContext, useState, useEffect } from "react";
import AppMenu from "../apps/adminUser/AppMenu";
import { Link } from "react-router-dom";

export const LoginContext = createContext();

export function LoginContextProvider(props) {


  const [editingLogin, setEditingLogin] = useState();
  const [login, setLogin] = useState(false);
  const [stateLogin, setStateLogin] = useState(false);

  //const var_estado = { stateLogin };
  const var_estado = useRef(false);

  async function session(usuario) {
    console.log(usuario)
    if (usuario.email == "admin" && usuario.password == "admin") {
      alert("Bienvenidos");
      var_estado.current = true;
      setStateLogin(var_estado);
      estado(var_estado);
      window.location.href = "./home";
    } else {
      alert("Credenciales incorrectas");
      var_estado.current = false;
      setStateLogin(var_estado);
      estado(var_estado);
    }
  }

  const estado = (elestado) => {

    if (elestado) {
      return (true);
    } else {
      var_estado.current = true;
      return (false);
    }
  }

  async function loggeo(status) {
    if (status == var_estado) {
      return true;
    } else {
      return false;
    }
  }


  //console.log(groups);
  return (
    <LoginContext.Provider
      value={{
        session,
        setLogin,
        login,
        stateLogin,
        setStateLogin,
        estado,
        loggeo,
      }}
    >
      {props.children}
    </LoginContext.Provider>
  );
}