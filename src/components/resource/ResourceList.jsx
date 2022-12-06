import { useContext } from "react";
import ResourceCard from "./ResourceCard";
import { FacultyContext } from "../../context/FacultyContext";

function ResourceList() {
  const { facultys } = useContext(FacultyContext);

  if (facultys.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Recursos registrados aún
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-3 gap-2">
      {facultys.map(({ resources }) => {
        if (resources.length === 0) return null;
        return resources.map((resource) => (
          <ResourceCard key={resource.resourceId} resource={resource} />
        ));
      })}
    </div>
  );
}

export default ResourceList;
