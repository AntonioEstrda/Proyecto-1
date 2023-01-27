import { useContext } from "react";
import ScheduleCMSCard from "./ScheduleCMSCard";
import { ScheduleCMSContext } from "../../context/ScheduleCMSContext";

export default function ScheduleCMSList() {
  const { scheduleCMSs } = useContext(ScheduleCMSContext);

  if (scheduleCMSs.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {scheduleCMSs.map((scheduleCMSs) => (
        <ScheduleCMSCard
          key={scheduleCMSs.scheduleCMSID}
          scheduleCMS={scheduleCMSs}
        />
      ))}
    </div>
  );
}
