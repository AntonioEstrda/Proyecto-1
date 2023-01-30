import { useContext } from "react";
import ResourceCard from "./ResourceCard";
import { ResourceContext } from "../../context/ResourceContext";

export default function ResourceList() {
  const { resources } = useContext(ResourceContext);

  if (resources.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Recursos registrados
      </h1>
    );
  }

  return (
    <div className="grid lg:grid-cols-4 md:grid-cols-3 sm:grid-cols-2 gap-2">
      {resources.map((resource) => (
        <ResourceCard key={resource.resourceId} resource={resource} />
      ))}
    </div>
  );
}
