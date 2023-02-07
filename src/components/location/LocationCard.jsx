import { useContext } from "react";
import { LocationContext } from "../../context/LocationContext";

export default function LocationCard({ location }) {
  const { deleteById, setEditingLocation } = useContext(LocationContext);

  function defineEditItem() {
    setEditingLocation(location);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <div className="bg-gray text-paleta2-azul-claro p-4 rounded-md">
        <h1 className="text-xl font-bold capitalize">{location?.name}</h1>

        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-lg font-bold text-blue">
            Parent:
          </p>
          {location?.parent}
        </div>

        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-lg font-bold text-blue">City:</p>
          {location?.city}
        </div>

        <div className="grid grid-cols-2 gap-3">
          <button
            className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={() => deleteById(location?.locationId)}
          >
            Eliminar
          </button>
          <button
            className="bg-boton2 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={defineEditItem}
          >
            Editar
          </button>
        </div>
      </div>
    </div>
  );
}
