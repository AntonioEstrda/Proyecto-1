import { useState, useContext, useEffect } from "react";
import { LocationContext } from "../../context/LocationContext";

export default function LocationForm() {
  const { create, editingLocation, update } = useContext(LocationContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [parent, setParent] = useState();
  const [city, setCity] = useState("");

  useEffect(() => {
    if (editingLocation) {
      setName(editingLocation.name);
      setParent(editingLocation.parent);
      setCity(editingLocation.city);
      setLimpio(false);
    }
  }, [editingLocation]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setParent("");
    setCity("");
    setLimpio(true);
  }

  function crear(e) {
    e.preventDefault();
    create({ name, parent, city });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      id: editingLocation.locationId,
      name,
      parent,
      city,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto">
      <form onSubmit={handleSubmit} className="bg-blue p-10 mb-4 rounded-lg">
        <h1 className="text-2xl font-bold text-paleta2-red-claro mb-3">
          Crear una locación
        </h1>
        <input
          placeholder="Nombre locación"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <textarea
          name="Parent"
          placeholder="Parent "
          onChange={(e) => setParent(e.target.value)}
          className="bg-paleta2-claro p-3 w-full mb-2 rounded-md"
          value={parent}
        ></textarea>

        <textarea
          placeholder="Ciudad "
          onChange={(e) => setCity(e.target.value)}
          className="bg-paleta2-claro p-3 w-full mb-2 rounded-md"
          value={city}
        ></textarea>

        <div className="grid grid-cols-1">
          <button className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro ">
            Guardar
          </button>
        </div>
      </form>
      <div className="grid grid-cols-1 w-auto px-20 pb-10">
        <button
          className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro "
          onClick={limpiarForm}
        >
          Limpiar
        </button>
      </div>
    </div>
  );
}
