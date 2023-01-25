import { useContext } from "react";
import LocationCard from "./LocationCard";
import { LocationContext } from "../../context/LocationContext";

export default function LocationList() {
  const { locations } = useContext(LocationContext);

  if (locations.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {locations.map((locations) => (
        <LocationCard key={locations.locationId} location={locations} />
      ))}
    </div>
  );
}
