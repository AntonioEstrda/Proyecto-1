import { useContext } from "react";
import { DepartmentContext } from "../../context/DepartmentContext";

export default function DepartmentCard({ department }) {
  const { deleteById, setEditingDepartment } = useContext(DepartmentContext);

  function defineEditItem() {
    setEditingDepartment(department);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{department?.name}</h1>
      <h1 className="text-xl font-bold capitalize">{department?.code}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Localización:</p>
        {department.location?.name}
        {department.location?.city ? "-" + department.location.city : ""}
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Facultad:</p>
        {department.facultad?.facultyName}
      </div>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(department?.departmentId)}
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
