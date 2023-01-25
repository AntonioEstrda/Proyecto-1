import { useContext } from "react";
import { DepartmentContext } from "../../context/DepartmentContext";

export default function DepartmentCard({ department }) {
  const { deleteById, setEditingDepartment } = useContext(
    DepartmentContext
  );

  function defineEditItem() {
    setEditingDepartment(department);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{department?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {department?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {department?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(department?.departmentID)}
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
