import { useState, useContext, useEffect } from "react";
import { FacultyResourceContext } from "../../context/FacultyResourceContext";

export default function FacultyResourceForm() {
  const {
    create,
    update,
    editingFacultyResource,
    idResourceSelected,
    idFacultySelected,
    setIdResourceSelected,
    setIdFacultySelected,
    resources,
    facultys,
  } = useContext(FacultyResourceContext);

  const [limpio, setLimpio] = useState(true);
  const [registrerDate, setRegistrerDate] = useState(
    new Date().toISOString().split("T")[0]
  );
  const [finalDate, setFinalDate] = useState(
    new Date().toISOString().split("T")[0]
  );
  const [isDisable, setDisable] = useState("");

  useEffect(() => {
    if (editingFacultyResource) {
      setRegistrerDate(editingFacultyResource.registrerDate);
      setFinalDate(editingFacultyResource.finalDate);
      setDisable(editingFacultyResource.isDisable);
      setLimpio(false);
      setIdResourceSelected(editingFacultyResource.resource.resourceId);
      setIdFacultySelected(editingFacultyResource.faculty.facultyId);

      setFormRadioIsDisable();
    }
  }, [editingFacultyResource]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setRegistrerDate(new Date().toISOString().split("T")[0]);
    setFinalDate(new Date().toISOString().split("T")[0]);
    setDisable("");
    setLimpio(true);
    setIdResourceSelected(0);
    setIdFacultySelected(0);

    cleanRadioIsDisable();
  }

  function setFormRadioIsDisable() {
    let ele = document.getElementsByName("isDisable_radio");
    if (editingResource.isDisable === "1") {
      ele[1].removeAttribute("checked");
      ele[0].setAttribute("checked", "");
    } else {
      ele[0].removeAttribute("checked");
      ele[1].setAttribute("checked", "");
    }
  }
  function cleanRadioIsDisable() {
    let ele = document.getElementsByName("isDisable_radio");
    ele[0].removeAttribute("checked");
    ele[1].removeAttribute("checked");
  }

  function crear(e) {
    e.preventDefault();
    create(
      {
        registrerDate,
        finalDate,
        isDisable,
        facultyFR: facultys.find(
          (faculty) => faculty.facultyId == idFacultySelected
        ),
        resourceFR: resources.find(
          (resource) => resource.resourceId == idResourceSelected
        ),
      },
      idFacultySelected,
      idResourceSelected
    );
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      facultyResourceID: editingFacultyResource.facultyResourceID,
      name,
      registrerDate,
      finalDate,
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
          Asignar un recurso
        </h1>

        <label className="text-paleta2-azul-claro">
          Fecha de registro:
          <input
            type="date"
            name="registrerDate"
            s
            className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
            value={registrerDate}
            onChange={(e) => setRegistrerDate(e.target.value)}
          />
          <span className="validity"></span>
        </label>

        <label className="text-paleta2-azul-claro">
          Fecha final:
          <input
            type="date"
            name="finalDate"
            className="bg-paleta2-fondo1 p-3 w-full mb-4 rounded-md"
            value={finalDate}
            onChange={(e) => setFinalDate(e.target.value)}
          />
          <span className="validity"></span>
        </label>

        <select
          id="facultySelected"
          name="facultyId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdFacultySelected(e.target.value);
          }}
        >
          {facultys.map((faculty) => {
            return (
              <option
                key={faculty.facultyId}
                value={faculty.facultyId}
                selected={
                  faculty.facultyId ===
                  editingFacultyResource?.faculty.facultyId
                    ? true
                    : false
                }
              >
                {faculty.facultyName}{" "}
              </option>
            );
          })}
        </select>

        <select
          id="resourceSelected"
          name="resourceId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdResourceSelected(e.target.value);
          }}
        >
          {resources.map((resource) => {
            return (
              <option
                key={resource.resourceId}
                value={resource.resourceId}
                selected={
                  resource.resourceId ===
                  editingFacultyResource?.resource.resourceId
                    ? true
                    : false
                }
              >
                {resource.name}{" "}
              </option>
            );
          })}
        </select>

        <div className="flex flex-wrap">
          <div className="flex items-center mr-4">
            <input
              id="red-radio"
              type="radio"
              value="0"
              onChange={() => setDisable("1")}
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
              onChange={() => setDisable("0")}
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
