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
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <div className="bg-gray text-paleta2-azul-claro p-4 rounded-md">
        <h1 className="text-xl font-bold capitalize">{academicPeriod?.name}</h1>

        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-paleta2-rojo text-sm">
            Fecha inicio: {academicPeriod?.initDate}
          </p>
        </div>
        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-paleta2-rojo text-sm">
            Fecha final: {academicPeriod?.finalDate}
          </p>
        </div>

        <div className="grid grid-cols-2 gap-3">
          <button
            className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={() => deleteById(academicPeriod?.academicPeriodID)}
          >
            Eliminar
          </button>
          <button
            className="bg-boton2 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={defineEditItem}
          >
            Editar
          </button>
        </div>
      </div>
    </div>
  );
}
