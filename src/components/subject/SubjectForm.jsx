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
  const [code, setCode] = useState("");
  const [isDisable, setIsDisable] = useState("");
  const [isExtern, setIsExtern] = useState("");

  useEffect(() => {
    if (editingSubject) {
      setName(editingSubject.name);
      setRequisits(editingSubject.requisits);
      setSemester(editingSubject.semester);
      setIntensity(editingSubject.intensity);
      setModality(editingSubject.modality);
      setType(editingSubject.type);
      setCode(editingSubject.code);
      setIsDisable(editingSubject.isDisable);
      setIsExtern(editingSubject.isExtern);
      setLimpio(false);
      setIdProgramSelected(editingSubject.program.programId);

      setFormRadioDisable();
      setFormRadioExtern();
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
    setCode("");
    setIsDisable("");
    setIsExtern("");
    setLimpio(true);
    setIdProgramSelected(0);

    cleanRadioDisable();
    cleanRadioExtern();
  }

  function setFormRadioDisable() {
    let ele = document.getElementsByName("isDisable_radio");
    if (editingSubject.isDisable === "1") {
      ele[1].removeAttribute("checked");
      ele[0].setAttribute("checked", "");
    } else {
      ele[0].removeAttribute("checked");
      ele[1].setAttribute("checked", "");
    }
  }
  function cleanRadioDisable() {
    let ele = document.getElementsByName("isDisable_radio");
    ele[0].removeAttribute("checked");
    ele[1].removeAttribute("checked");
  }

  function setFormRadioExtern() {
    let ele = document.getElementsByName("isExtern_radio");
    if (editingSubject.isExtern === "1") {
      ele[1].removeAttribute("checked");
      ele[0].setAttribute("checked", "");
    } else {
      ele[0].removeAttribute("checked");
      ele[1].setAttribute("checked", "");
    }
  }
  function cleanRadioExtern() {
    let ele = document.getElementsByName("isExtern_radio");
    ele[0].removeAttribute("checked");
    ele[1].removeAttribute("checked");
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
      code,
      isDisable,
      isExtern,
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
      code,
      isDisable,
      isExtern,
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
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <input
          placeholder="Requisitos"
          onChange={(e) => setRequisits(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={requisits}
        />
        <input
          placeholder="Semestre"
          onChange={(e) => setSemester(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={semester}
        />
        <input
          placeholder="Intensidad"
          onChange={(e) => setIntensity(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={intensity}
        />

        <input
          placeholder="Modalidad"
          onChange={(e) => setModality(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={modality}
        />

        <input
          placeholder="Tipo de materia"
          onChange={(e) => setType(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
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

        <input
          placeholder="Código"
          onChange={(e) => setCode(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={code}
        />

        <div className="flex flex-wrap">
          <div className="flex items-center mr-4">
            <input
              id="red-radio"
              type="radio"
              value="0"
              onChange={() => setIsDisable("1")}
              name="isDisable_radio"
              className="w-4 h-4 text-red-600 bg-gray-100 border-gray-300 focus:ring-red-500 dark:focus:ring-red-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            ></input>
            <label
              htmlFor="red-radio"
              className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Inactivo
            </label>
          </div>
          <div className="flex items-center mr-4">
            <input
              id="green-radio"
              type="radio"
              value="1"
              onChange={() => setIsDisable("0")}
              name="isDisable_radio"
              className="w-4 h-4 text-green-600 bg-gray-100 border-gray-300 focus:ring-green-500 dark:focus:ring-green-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            ></input>
            <label
              htmlFor="green-radio"
              className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Activo
            </label>
          </div>
        </div>
        <p>. </p>

        <div className="flex flex-wrap">
          <div className="flex2 items-center mr-4">
            <input
              id="red-radio"
              type="radio"
              value="0"
              onChange={() => setIsExtern("1")}
              name="isExtern_radio"
              className="w-4 h-4 text-red-600 bg-gray-100 border-gray-300 focus:ring-red-500 dark:focus:ring-red-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            ></input>
            <label
              htmlFor="red-radio"
              className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Externa
            </label>
          </div>
          <div className="flex items-center mr-4">
            <input
              id="green-radio"
              type="radio"
              value="1"
              onChange={() => setIsExtern("0")}
              name="isExtern_radio"
              className="w-4 h-4 text-green-600 bg-gray-100 border-gray-300 focus:ring-green-500 dark:focus:ring-green-600 dark:ring-offset-gray-800 focus:ring-2 dark:bg-gray-700 dark:border-gray-600"
            ></input>
            <label
              htmlFor="green-radio"
              className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300"
            >
              Interna
            </label>
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
