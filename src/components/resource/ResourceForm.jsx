import { useState, useContext } from "react";
import { ResourceContext } from "../../context/ResourceContext";

export default function ResourceForm() {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [resourceType, setResourceType] = useState("computacional");
  const [disable, setDisable] = useState(false);
  const [code, setCode] = useState("");
  const [location, setLocation] = useState("");
  const [capacity, setCapacity] = useState();
  const [number, setNumber] = useState();


  const { createResource } = useContext(ResourceContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    createResource({ name, description, resourceType, disable });
    setName("");
    setDescription("");
    setResourceType("computacional");
    setDisable(false);
    setCode("");
    setLocation("");
    setCapacity("");
    setNumber("");
  };

  return (
    <div className="max-w-md mx-auto">
      <form onSubmit={handleSubmit} className="bg-paleta2-purpura p-10 mb-4 rounded-lg">
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Crear un Recurso</h1>
        <input
          placeholder="Nombre del recurso"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <textarea
          name="description"
          placeholder="Descripción del recurso"
          onChange={(e) => setDescription(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-0 rounded-md"
          value={description}
        ></textarea>
        <select
          name="resourceType"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          onChange={(e) => setResourceType(e.target.value)}
          value="computacional"
        >
          <option value="computacional">Computacional</option>
          <option value="utileria">Utileria</option>
        </select>
        <select
          name="disable"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={disable}
          onChange={(e) => setDisable(e.target.value)}
        >
          <option value="false">Activo</option>
          <option value="true">Inactivo</option>
        </select>
        <input
          placeholder="Código del recurso"
          onChange={(e) => setCode(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={code}
        />
        <input
          placeholder="Locación"
          onChange={(e) => setLocation(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={location}
        />
        <input
          placeholder="Capacidad"
          onChange={(e) => setCapacity(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={capacity}
        />
        <input
          placeholder="Número del recurso"
          onChange={(e) => setNumber(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={number}
        />
        <button className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro">Guardar</button>
      </form>
    </div>
  );
}


