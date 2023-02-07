import { useState, useContext, useEffect } from "react";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeForm() {
  const { create, editingResourceType, update } = useContext(
    ResourceTypeContext
  );

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [parent, setParent] = useState("");
  const [isDisable, setIsDisable] = useState("");

  useEffect(() => {
    if (editingResourceType) {
      setName(editingResourceType.name);
      setParent(editingResourceType.parent);
      setIsDisable(editingResourceType.isDisable);
      setLimpio(false);

      setFormRadioIsDisable();
    }
  }, [editingResourceType]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setParent("");
    setIsDisable("");
    setLimpio(true);
    cleanRadioIsDisable();
  }

  function setFormRadioIsDisable() {
    let ele = document.getElementsByName("isDisable_radio");
    if (editingResourceType.isDisable === "1") {
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
    create({ name, parent, isDisable });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      resourceTypeId: editingResourceType.resourceTypeId,
      name,
      parent,
      isDisable,
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
          Crear Tipo de Recurso
        </h1>
        <input
          placeholder="Nombre"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <input
          placeholder="Parent"
          onChange={(e) => setParent(e.target.value)}
          autoFocus="on"
          className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={parent}
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

        <div className="grid grid-cols-1 mt-2">
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
