import { useState, useContext, useEffect } from "react";
import { ResourceContext } from "../../context/ResourceContext";

function ResourceForm() {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [resourceType, setResourceType] = useState("computacional");
  const [disable, setDisable] = useState(false);

  const { createResource } = useContext(ResourceContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    createResource({ name, description, resourceType, disable });
    setName("");
    setDescription("");
    setResourceType("computacional");
    setDisable(false);
  };

  return (
    <div className="max-w-md mx-auto">
      <form onSubmit={handleSubmit} className="bg-slate-800 p-10 mb-4 ">
        <h1 className="text-2xl font-bold text-white mb-3">Crear un Recurso</h1>
        <input
          placeholder="Nombre del recurso"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-slate-200 p-3 w-full mb-2"
          value={name}
        />
        <textarea
          name="description"
          placeholder="Descripción del recurso"
          onChange={(e) => setDescription(e.target.value)}
          className="bg-slate-200 p-3 w-full mb-0"
          value={description}
        ></textarea>
        <select
          name="resourceType"
          className="bg-slate-200 p-3 w-full mb-2"
          onChange={(e) => setResourceType(e.target.value)}
          value="computacional"
        >
          <option value="computacional">Computacional</option>
          <option value="utileria">Utileria</option>
        </select>
        <select
          name="disable"
          className="bg-slate-200 p-3 w-full mb-2"
          value={disable}
          onChange={(e) => setDisable(e.target.value)}
        >
          <option value="false">Activo</option>
          <option value="true">Inactivo</option>
        </select>
        <button className="bg-indigo-500 px-3 py-1 text-white">Guardar</button>
      </form>
    </div>
  );
}

export default ResourceForm;
