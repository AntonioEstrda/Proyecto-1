import { useContext } from "react";
import { FacultyContext } from "../../context/FacultyContext";

export default function FacultyCard({ faculty }) {
  const { deleteById, setEditingFaculty } = useContext(FacultyContext);

  function defineEditItem() {
    setEditingFaculty(faculty);
  }

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{faculty?.facultyName}</h1>
      <p className="text-paleta2-red-claro text-sm">
        Localización: {faculty?.initDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-rojo px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(faculty?.facultyID)}
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
