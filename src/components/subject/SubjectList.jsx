import { useContext } from "react";
import SubjectCard from "./SubjectCard";
import { SubjectContext } from "../../context/SubjectContext";

export default function SubjectList() {
  const { subjects } = useContext(SubjectContext);

  if (subjects.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {subjects.map((subjects) => (
        <SubjectCard
          key={subjects.subjectID}
          subject={subjects}
        />
      ))}
    </div>
  );
}
