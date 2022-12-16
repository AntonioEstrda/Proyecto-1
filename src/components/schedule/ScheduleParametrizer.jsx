import { useContext } from "react";
import ScheduleGUI from "./ScheduleGUI";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleParametrizer() {
  const { facultys } = useContext(ScheduleContext);

  if (facultys.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay facultades registradas aún
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {facultys.map((faculty) => (
        <ScheduleGUI key={faculty.facultyId} faculty={faculty} />
      ))}
    </div>
  );
}

export default ScheduleParametrizer;
