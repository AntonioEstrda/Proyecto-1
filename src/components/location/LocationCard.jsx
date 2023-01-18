import { useContext } from "react";
import { LocationContext } from "../../context/LocationContext";

export default function LocationCard({ location }) {
  const { deleteById, setEditingLocation } = useContext(LocationContext);

  function defineEditItem() {
    setEditingLocation(location);
  }

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{location?.name}</h1>
      <p className="text-paleta2-red-claro text-sm">{location?.parent}</p>
      <p className="text-paleta2-red-claro text-sm">{location?.city}</p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(academicPeriod?.academicPeriodID)}
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
  );
}
