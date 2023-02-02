import { useContext } from "react";
import { ProgramContext } from "../../context/ProgramContext";

export default function ProgramCard({ program }) {
  const { deleteById, setEditingProgram } = useContext(ProgramContext);

  function defineEditItem() {
    setEditingProgram(program);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{program?.name}</h1>
      <h1 className="text-xl font-bold capitalize">{program?.code}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Localización:</p>
        {program.location?.name}
        {program.location?.city ? "-" + resource.location.city : ""}
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Departamento:</p>
        {program.department?.name}
      </div>

      <h1 className="text-xl font-bold capitalize">{program?.color}</h1>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(program?.programId)}
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
