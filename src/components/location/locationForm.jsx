import { useState, useContext, useEffect } from "react";
import { LocationContext } from "../../context/LocationContext";

export default function LocationForm() {
  const { create, editingLocation, update } = useContext(LocationContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [city, setCity] = useState("");
  const [parent, setParent] = useState("");

  useEffect(() => {
    if (editingLocation) {
      setName(editingLocation.name);
      setCity(editingLocation.city);
      setParent(editingLocation.parent);
      setLimpio(false);
    }
  }, [editingLocation]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setCity("");
    setParent("");
    setLimpio(true);
  }

  function crear(e) {
    e.preventDefault();
    create({ name, city, parent });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      locationId: editingLocation.locationId,
      name,
      city,
      parent,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto ">
      <form
        onSubmit={handleSubmit}
        className="bg-paleta2-purpura p-10 mb-4 rounded-lg"
      >
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Crear una locación
        </h1>
        <input
          placeholder="Nombre "
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <input
          placeholder="City"
          onChange={(e) => setCity(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={city}
        />
        <textarea
          name="Parent"
          placeholder="Parent"
          onChange={(e) => setParent(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-0 rounded-md"
          value={parent}
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
