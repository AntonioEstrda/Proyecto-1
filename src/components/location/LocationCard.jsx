import { useContext } from "react";
import { LocationContext } from "../../context/LocationContext";

export default function LocationCard({ location}) {
    const { deleteLocation } = useContext(LocationContext);
  
    return (
      <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
        <h1 className="text-xl font-bold capitalize">{location?.name}</h1>
        <p className="text-paleta2-red-claro text-sm">{location?.parent}</p>
        <p className="text-paleta2-red-claro text-sm">{location?.city}</p>

        <div className="grid grid-cols-1 gap-3">
          <button
            className="bg-paleta2-rojo px-2 py-3 rounded-md mt-4 hover:bg-red-400"
            onClick={() => deleteLocation(location?.id)}
          >
            Eliminar Location
          </button>
        </div>  
      </div>
    );
  }