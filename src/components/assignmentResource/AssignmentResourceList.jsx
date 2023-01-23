import { useContext } from "react";
import AssignmentResourceCard from "./AssignmentResourceCard";
import { AssignmentResourceContext } from "../../context/AssignmentResourceContext";

export default function AssignmentResourceList() {
  const { assignmentResources } = useContext(AssignmentResourceContext);

  if (assignmentResources.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {assignmentResources.map((assignmentResources) => (
        <AssignmentResourceCard
          key={assignmentResources.assignmentResourceID}
          assignmentResource={assignmentResources}
        />
      ))}
    </div>
  );
}
