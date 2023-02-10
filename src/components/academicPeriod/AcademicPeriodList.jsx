import { useContext } from "react";
import { AcademicPeriodContext } from "../../context/AcademicPeriodContext";
import AcademicPeriodCard from "./AcademicPeriodCard";

export default function AcademicPeriodList() {
  const { academicPeriods } = useContext(AcademicPeriodContext);

  if (academicPeriods.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {academicPeriods.map((academicPeriods) => (
        <AcademicPeriodCard
          key={academicPeriods.academicPeriodID}
          academicPeriod={academicPeriods}
        />
      ))}
    </div>
  );
}
