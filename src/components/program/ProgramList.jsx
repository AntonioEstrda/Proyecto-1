import { useContext } from "react";
import ProgramCard from "./ProgramCard";
import { ProgramContext } from "../../context/ProgramContext";

export default function ProgramList() {
  const { programs } = useContext(ProgramContext);

  if (programs.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {programs.map((programs) => (
        <ProgramCard
          key={programs.programID}
          program={programs}
        />
      ))}
    </div>
  );
}
