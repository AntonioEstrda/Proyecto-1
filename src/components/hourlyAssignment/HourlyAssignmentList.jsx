import { useContext } from "react";
import HourlyAssignmentCard from "./HourlyAssignmentCard";
import { HourlyAssignmentContext } from "../../context/HourlyAssignmentContext";

export default function HourlyAssignmentList() {
  const { hourlyAssignments } = useContext(HourlyAssignmentContext);

  if (hourlyAssignments.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {hourlyAssignments.map((hourlyAssignments) => (
        <HourlyAssignmentCard
          key={hourlyAssignments.hourlyAssignmentID}
          hourlyAssignment={hourlyAssignments}
        />
      ))}
    </div>
  );
}
