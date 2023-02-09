import { useContext } from "react";
import FacultyResourceCard from "./FacultyResourceCard";
import { FacultyResourceContext } from "../../context/FacultyResourceContext";

export default function FacultyResourceList() {
  const { facultyResources } = useContext(FacultyResourceContext);

  if (facultyResources.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Recursos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {facultyResources.map((facultyResource) => (
        <FacultyResourceCard
          key={facultyResource.facResId}
          facultyResource={facultyResource}
        />
      ))}
    </div>
  );
}
