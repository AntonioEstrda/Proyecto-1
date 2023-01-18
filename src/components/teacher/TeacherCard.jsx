import { useContext } from "react";
import { TeacherContext } from "../../context/TeacherContext";

export default function TeacherCard({ teacher }) {
  const { deleteById, setEditingTeacher } = useContext(
    TeacherContext
  );

  function defineEditItem() {
    setEditingTeacher(teacher);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{teacher?.firstName + ' ' + teacher?.lastName}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Identificación: {teacher?.numIden}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Activo: {(teacher?.isDisable) ? "No" : "Sí"}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(teacher?.teacherID)}
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
