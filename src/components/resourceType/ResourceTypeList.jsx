import { useContext } from "react";
import ResourceTypeCard from "./ResourceCard";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeList() {
  const { resourcesTypes } = useContext(ResourceTypeContext);

  if (resourcesTypes.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Tipos de Recursos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {resourcesTypes.map((resourceType) => (
        <ResourceTypeCard key={resourceType.typeId} resourceType={resourceType} />
      ))}
    </div>
  );
}
