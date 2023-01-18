import { useState, useContext, useEffect } from "react";
import { TeacherContext } from "../../context/TeacherContext";

export default function TeacherForm() {
  const { create, editingTeacher, update } = useContext(
    TeacherContext
  );

  const [limpio, setLimpio] = useState(true);
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [numIden, setNumIden] = useState("");
  const [isDisable, setIsDisable] = useState(false);

  useEffect(() => {
    if (editingTeacher) {
      setFirstName(editingTeacher.firstName);
      setLastName(editingTeacher.lastName);
      setNumIden(editingTeacher.numIden);
      setIsDisable(editingTeacher.isDisable);
      setLimpio(false);
    }
  }, [editingTeacher]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setFirstName("");
    setLastName("");
    setNumIden("");
    setIsDisable(false);
    setLimpio(true);
  }

  function crear(e) {
    e.preventDefault();
    create({ firstName, lastName, numIden, isDisable });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      teacherID: editingTeacher.teacherID,
      firstName,
      lastName,
      numIden,
      isDisable,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto ">
      <form onSubmit={handleSubmit} className="bg-paleta2-purpura p-10 mb-4 rounded-lg">
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Crear Licenciado
        </h1>
        <input
          placeholder="Nombre periodo académico"
          onChange={(e) => setFirstName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={firstName}
        />
        <input
          placeholder="Nombre periodo académico"
          onChange={(e) => setLastName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={lastName}
        />
        <input
          placeholder="Nombre periodo académico"
          onChange={(e) => setNumIden(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={numIden}
        />
        <div className="flex flex-wrap">
          <div className="flex items-center mr-4">
            <input id="red-radio" type="radio" value="isDisable" name="colored-radio" className="w-4 h-4 text-red-600 bg-gray-100 border-gray-300 focus:ring-red-500 dark:focus:ring-red-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
            <label for="red-radio" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Inactivo</label>
          </div>
          <div className="flex items-center mr-4">
            <input id="green-radio" type="radio" value="isDisable" name="colored-radio" className="w-4 h-4 text-green-600 bg-gray-100 border-gray-300 focus:ring-green-500 dark:focus:ring-green-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"></input>
            <label for="green-radio" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Activo</label>
          </div>
        </div>

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
