import { useContext } from "react";
import ResourceTypeCard from "./ResourceTypeCard";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeList() {
  const { resourceTypes } = useContext(ResourceTypeContext);

  if (resourceTypes.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Tipos de recursos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {resourceTypes.map((resourceType) => (
        <ResourceTypeCard
          key={resourceType.resourceTypeID}
          resourceType={resourceType}
        />
      ))}
    </div>
  );
}
