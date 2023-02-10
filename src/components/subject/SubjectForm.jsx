import { useState, useContext, useEffect } from "react";
import { SubjectContext } from "../../context/SubjectContext";

export default function SubjectForm() {
  const {
    create,
    editingSubject,
    update,
    idProgramSelected,
    setIdProgramSelected,
    setEditingSubject,
    programs,
  } = useContext(SubjectContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [semester, setSemester] = useState("");
  const [intensity, setIntensity] = useState("");
  const [modality, setModality] = useState("");
  const [type, setType] = useState("");
  const [code, setCode] = useState("");
  const [isDisable, setIsDisable] = useState(true);
  const [isExtern, setIsExtern] = useState(true);

  useEffect(() => {
    if (editingSubject) {
      setName(editingSubject.name);
      setSemester(editingSubject.semester);
      setIntensity(editingSubject.intensity);
      setModality(editingSubject.modality);
      setType(editingSubject.type);
      setCode(editingSubject.code);
      setIsDisable(!editingSubject.isDisable);
      setIsExtern(!editingSubject.isExtern);
      setLimpio(false);
      setIdProgramSelected(editingSubject.program.programId);
    }
  }, [editingSubject]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setSemester("");
    setIntensity("");
    setModality("");
    setType("");
    setCode("");
    setIsDisable(true);
    setIsExtern(true);
    setLimpio(true);
    setIdProgramSelected(0);
    setEditingSubject();
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      requisits: '{"7":null}',
      semester,
      intensity,
      modality,
      type,
      program: programs.find(
        (program) => program.programId == idProgramSelected
      ),
      code,
      disable: isDisable,
      extern: isExtern,
    });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      subjectID: editingSubject.subjectID,
      name,
      requisits: '{"7":null}',
      semester,
      intensity,
      modality,
      type,
      program: programs.find(
        (program) => program.programId == idProgramSelected
      ),
      code,
      disable: isDisable,
      extern: isExtern,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto ">
      <form
        onSubmit={handleSubmit}
        className="bg-paleta2-purpura p-10 mb-4 rounded-lg"
      >
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Crear una Materia
        </h1>
        <input
          placeholder="Nombre de la materia"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <input
          placeholder="Semestre"
          onChange={(e) => setSemester(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={semester}
        />
        <input
          placeholder="Intensidad"
          onChange={(e) => setIntensity(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={intensity}
        />

        <select
          id="programSelected"
          name="programId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setModality(e.target.value);
          }}
          value={editingSubject?.modality}
        >
          <option value="">Selecciona una modalidad</option>
          <option value="Semestral">Semestral</option>
          <option value="Anual">Anual</option>
        </select>

        <select
          id="programSelected"
          name="programId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setType(e.target.value);
          }}
          value={editingSubject?.type}
        >
          <option value="">Selecciona el tipo de Asignatura</option>
          <option value="TEORICA">TEORICA</option>
          <option value="PRACTICA">PRACTICA</option>
          <option value="HIBRIDA">HIBRIDA</option>
          <option value="FISH">FISH</option>
        </select>

        <select
          id="programSelected"
          name="programId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdProgramSelected(e.target.value);
          }}
          value={editingSubject?.program?.programId}
        >
          <option value="">Selecciona el programa</option>
          {programs.map((program) => {
            return (
              <option key={program.programId} value={program.programId}>
                {program.name}{" "}
              </option>
            );
          })}
        </select>

        <input
          placeholder="Código"
          onChange={(e) => setCode(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={code}
        />

        <div className="grid grid-cols-2 grid-flow-col-dense mt-1 mb-3">
          <label className="relative inline-flex items-center mr-5 cursor-pointer">
            <input
              type="checkbox"
              className="sr-only peer"
              onChange={(e) => setIsDisable(!e.target.checked)}
            ></input>
            <div className="w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-green-300 dark:peer-focus:ring-green-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-green-600"></div>
            <span className="ml-3 text-sm font-medium text-gray-900 dark:text-gray-300">
              Activa
            </span>
          </label>
          <label className="relative inline-flex items-center mr-5 cursor-pointer">
            <input
              type="checkbox"
              className="sr-only peer"
              onChange={(e) => setIsExtern(!e.target.checked)}
            ></input>
            <div className="w-11 h-6 bg-gray-200 rounded-full peer dark:bg-gray-700 peer-focus:ring-4 peer-focus:ring-green-300 dark:peer-focus:ring-green-800 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-green-600"></div>
            <span className="ml-3 text-sm font-medium text-gray-900 dark:text-gray-300">
              Interna
            </span>
          </label>
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
