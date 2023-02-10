import React, { useState, useContext } from "react";
import AppMenu from "../../apps/adminUser/AppMenu";
import { LoginContext } from "../../context/LoginContext";

export default function Login() {
  const { session } = useContext(LoginContext);

  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    if (username.length === 0 || password.length === 0) {
      alert("Ingrese el usuario y su contraseña");
    } else {
      sessionUser(username, password);
    }
  };

  function sessionUser(u, p) {
    session(username, password);
  }
  return (
    <div className="max-w-md mx-auto ">
      <form
        id="formulario"
        onSubmit={handleSubmit}
        className="bg-paleta2-purpura p-10 mb-4 rounded-lg"
      >
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Login
        </h1>
        <div>
          <label className="text-paleta2-azul-claro">Usuario:</label>
          <input
            id="user"
            name="username"
            autoComplete="off"
            placeholder="Paquito"
            onChange={(e) => setUsername(e.target.value)}
            className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={username}
          />
          <label for="password" className="text-paleta2-azul-claro">
            Contraseña:
          </label>
          <input
            id="password"
            name="password"
            placeholder="********"
            onChange={(e) => setPassword(e.target.value)}
            type="password"
            className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={password}
          />
        </div>
        <div className="grid grid-cols-1">
          <button className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro ">
            Entrar
          </button>
        </div>
      </form>
    </div>
  );
}
