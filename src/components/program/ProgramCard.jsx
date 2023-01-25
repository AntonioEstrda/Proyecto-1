import { useContext } from "react";
import { ProgramContext } from "../../context/ProgramContext";

export default function ProgramCard({ program }) {
  const { deleteById, setEditingProgram } = useContext(
    ProgramContext
  );

  function defineEditItem() {
    setEditingProgram(program);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{program?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {program?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {program?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(program?.programID)}
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
  );
}
