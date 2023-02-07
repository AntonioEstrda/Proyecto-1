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
      <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
        <p className="text-paleta2-rojo text-sm">
          Fecha de registro: {facultyResource?.registrerDate}
        </p>
      </div>

      <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
        <p className="text-paleta2-rojo text-sm">
          Fecha final: {facultyResource?.finalDate}
        </p>
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Facultad:</p>
        {facultyResource.faculty?.facultyName}
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">recurso:</p>
        {facultyResource.resource?.name}
      </div>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() =>
            deleteById(facultyResource?.facultyFR, facultyResource?.resourceFR)
          }
        >
          Actualizar
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
