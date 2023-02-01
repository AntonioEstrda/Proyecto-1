import { useContext } from "react";
import DepartmentCard from "./DepartmentCard";
import { DepartmentContext } from "../../context/DepartmentContext";

export default function DepartmentList() {
  const { departments } = useContext(DepartmentContext);

  if (departments.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Departamentos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {departments.map((departments) => (
        <DepartmentCard
          key={departments.departmentId}
          department={departments}
        />
      ))}
    </div>
  );
}
