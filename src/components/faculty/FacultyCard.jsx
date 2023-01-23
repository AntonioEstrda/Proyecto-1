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

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-l text-paleta2-naranja">
          Localización: {faculty.location?.name}
        </p>
        <p className="text-paleta2-red-claro text-sm">
          {faculty.location?.parent}
        </p>
        <p className="text-paleta2-red-claro text-sm">
          {faculty.location?.city}
        </p>
      </div>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-rojo px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(faculty?.facultyId)}
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
