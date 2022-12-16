import { useContext } from "react";
import ScheduleCMS from "./ScheduleCMS";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleParametrizer() {
  const { listaProgramas, listaSemestres, listaMaterias, listaSalones } =
    useContext(ScheduleContext);

  console.log(listaProgramas, listaSemestres, listaMaterias, listaSalones);

  return (
    <>
      <ScheduleCMS />
    </>
  );
}

export default ScheduleParametrizer;
