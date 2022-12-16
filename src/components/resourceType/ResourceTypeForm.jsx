import { useState, useContext } from "react";
import {ResourceTypeContext} from '../../context/ResourceTypeContext'

export default function ResourceTypeForm() {
  const [name, setName] = useState("");
  const [parent, setParent] = useState();
  const [disable, setDisable] = useState(false);

  const { createResourceType } = useContext(ResourceTypeContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    createResourceType({ name, parent, disable });
    setName("");
    setParent("");
    setDisable(false);
  };

  return (
    <div className="max-w-md mx-auto">
      <form onSubmit={handleSubmit} className="bg-slate-800 p-10 mb-4 ">
        <h1 className="text-2xl font-bold text-white mb-3">Crear un Tipo de Recurso</h1>
        <input
          placeholder="Nombre del tipo"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-slate-200 p-3 w-full mb-2"
          value={name}
        />
        <textarea
          name="Parent"
          placeholder="Parent del tipo de recurso"
          onChange={(e) => setParent(e.target.value)}
          className="bg-slate-200 p-3 w-full mb-0"
          value={parent}
        ></textarea>
        <select
          name="disable"
          className="bg-slate-200 p-3 w-full mb-2"
          value={disable}
          onChange={(e) => setDisable(e.target.value)}
        >
          <option value="false">Activo</option>
          <option value="true">Inactivo</option>
        </select>
    
        <button className="bg-indigo-500 px-8 py-3 text-white ">Guardar</button>
      </form>
    </div>
  );
}

