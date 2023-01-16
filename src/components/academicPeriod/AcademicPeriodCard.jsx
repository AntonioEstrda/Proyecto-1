import { useContext } from "react";
import { AcademicPeriodContext } from "../../context/AcademicPeriodContext";

export default function AcademicPeriodCard({ academicPeriod }) {
  const { deleteById, setEditingAcademicPeriod } = useContext(
    AcademicPeriodContext
  );

  function defineEditItem() {
    setEditingAcademicPeriod(academicPeriod);
  }

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{academicPeriod?.name}</h1>
      <p className="text-paleta2-red-claro text-sm">
        Fecha inicio: {academicPeriod?.initDate}
      </p>
      <p className="text-paleta2-red-claro text-sm">
        Fecha final: {academicPeriod?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-rojo px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(academicPeriod?.academicPeriodID)}
        >
          Eliminar
        </button>
        <button
          className="bg-amber-700 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={defineEditItem}
        >
          Editar
        </button>
      </div>
    </div>
  );
}
