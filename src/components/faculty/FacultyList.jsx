import { useContext } from "react";
import FacultyCard from "./FacultyCard";
import { FacultyContext } from "../../context/FacultyContext";

export default function FacultyList() {
  const { facultys } = useContext(FacultyContext);

  if (facultys.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {facultys.map((faculty) => (
        <FacultyCard key={faculty.facultyId} faculty={faculty} />
      ))}
    </div>
  );
}
