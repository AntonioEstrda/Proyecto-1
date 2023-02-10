import { createContext, useState } from "react";

export const LoginContext = createContext();

export function LoginContextProvider(props) {
  const [stateLogin, setLogin] = useState(false);

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
        stateLogin,
      }}
    >
      {props.children}
    </LoginContext.Provider>
  );
}
