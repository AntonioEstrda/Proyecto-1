import { useState, useContext, useEffect } from "react";
import { FacultyContext } from "../../context/FacultyContext";

export default function FacultyForm() {
  const { create, editingFaculty, update } = useContext(FacultyContext);

  const [limpio, setLimpio] = useState(true);
  const [facultyName, setFacultyName] = useState("");

  useEffect(() => {
    if (editingFaculty) {
      setFacultyName(editingFaculty.facultyName);
      setLimpio(false);
    }
  }, [editingFaculty]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setFacultyName("");
    setLimpio(true);
  }

  function crear(e) {
    e.preventDefault();
    create({ facultyName });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      facultyId: editingFaculty.facultyId,
      facultyName,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto ">
      <form onSubmit={handleSubmit} className="bg-blue p-10 mb-4 rounded-lg">
        <h1 className="text-2xl font-bold text-paleta2-red-claro mb-3">
          Crear una Facultad
        </h1>
        <input
          placeholder="Nombre de la facultad"
          onChange={(e) => setFacultyName(e.target.value)}
          autoFocus="on"
          className="bg-slate-500 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={facultyName}
        />
        <div className="grid grid-cols-1">
          <button className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro ">
            Guardar
          </button>
        </div>
      </form>

      <div className="grid grid-cols-1 w-auto px-20 pb-10">
        <button
          className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro "
          onClick={limpiarForm}
        >
          Limpiar
        </button>
      </div>
    </div>
  );
}
