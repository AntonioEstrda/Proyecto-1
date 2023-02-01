import { useState, useContext, useEffect } from "react";
import { SubjectContext } from "../../context/SubjectContext";

export default function SubjectForm() {
  const {
    create,
    editingSubject,
    update,
    idProgramSelected,
    setIdProgramSelected,
    programs,
  } = useContext(SubjectContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [requisits, setRequisits] = useState("");
  const [semester, setSemester] = useState("");
  const [intensity, setIntensity] = useState("");
  const [modality, setModality] = useState("");
  const [type, setType] = useState("");

  useEffect(() => {
    if (editingSubject) {
      setName(editingSubject.name);
      setRequisits(editingSubject.requisits);
      setSemester(editingSubject.semester);
      setIntensity(editingSubject.intensity);
      setModality(editingSubject.modality);
      setType(editingSubject.type);
      setLimpio(false);
      setIdProgramSelected(editingSubject.program.programId);
    }
  }, [editingSubject]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setRequisits("");
    setSemester("");
    setIntensity("");
    setModality("");
    setType("");
    setLimpio(true);
    setIdProgramSelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      requisits,
      semester,
      intensity,
      modality,
      type,
      program: programs.find(
        (program) => program.programId == idProgramSelected
      ),
    });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      subjectID: editingSubject.subjectID,
      name,
      requisits,
      semester,
      intensity,
      modality,
      type,
      program: programs.find(
        (program) => program.programId == idProgramSelected
      ),
    });
    limpiarForm();
  }

  const modalidades = [
    { label: "Anual", value: "Anual" },
    { label: "Semestral", value: "Semestral" },
  ];

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
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <input
          placeholder="Requisitos"
          onChange={(e) => setRequisits(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={requisits}
        />
        <input
          placeholder="Semestre"
          onChange={(e) => setSemester(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={semester}
        />
        <input
          placeholder="Intensidad"
          onChange={(e) => setIntensity(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={intensity}
        />

        <input
          placeholder="Modalidad"
          onChange={(e) => setModality(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={modality}
        />

        <input
          placeholder="Tipo de materia"
          onChange={(e) => setType(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={type}
        />

        <select
          id="programSelected"
          name="programId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdProgramSelected(e.target.value);
          }}
        >
          {programs.map((program) => {
            return (
              <option
                key={program.programId}
                value={program.programId}
                selected={
                  program.programId === editingSubject?.program.programId
                    ? true
                    : false
                }
              >
                {program.name}{" "}
              </option>
            );
          })}
        </select>
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
