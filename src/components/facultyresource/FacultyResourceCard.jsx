import { useContext } from "react";
import { FacultyResourceContext } from "../../context/FacultyResourceContext";

export default function FacultyResourceCard({ facultyResource }) {
  const { deleteById, setEditingFacultyResource } = useContext(
    FacultyResourceContext
  );

  function defineEditItem() {
    setEditingFacultyResource(facultyResource);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{facultyResource?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {facultyResource?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {facultyResource?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(facultyResource?.facultyResourceID)}
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
