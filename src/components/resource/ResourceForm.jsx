import { useState, useContext, useEffect } from "react";
import { ResourceContext } from "../../context/ResourceContext";

export default function ResourceForm() {
  const {
    create,
    editingResource,
    update,
    idLocationSelected,
    idResourceTypeSelected,
    idFacultySelected,
    setIdLocationSelected,
    setIdResourceTypeSelected,
    setIdFacultySelected,
    locations,
    resourceTypes,
    facultys,
  } = useContext(ResourceContext);

  const [limpio, setLimpio] = useState(true);
  const [resourceName, setResourceName] = useState("");
  const [resourceDescription, setResourceDescription] = useState("");
  const [resourceCode, setResourceCode] = useState("");
  const [resourceNumber, setResourceNumber] = useState("");
  const [resourceCapacity, setResourceCapacity] = useState("");
  const [isDisable, setResourceDisable] = useState("");

  useEffect(() => {
    if (editingResource) {
      setResourceName(editingResource.resourceName);
      setResourceDescription(editingResource.resourceDescription);
      setResourceCode(editingResource.resourceCode);
      setResourceNumber(editingResource.resourceNumber);
      setResourceCapacity(editingResource.resourceCapacity);
      setResourceDisable(editingResource.isDisable);
      setLimpio(false);
      setIdResourceTypeSelected(editingResource.resourceType.resourceTypeId);
      setIdLocationSelected(editingResource.location.locationId);
      setIdFacultySelected(editingResource.faculty.facultyId);

      setFormRadioIsDisable();
    }
  }, [editingResource]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setResourceName("");
    setResourceDescription("");
    setResourceCode("");
    setResourceNumber("");
    setResourceCapacity("");
    setResourceDisable("");
    setLimpio(true);
    setIdLocationSelected(0);
    setIdResourceTypeSelected(0);
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
        name: resourceName,
        description: resourceDescription,
        resourceType: resourceTypes.find(
          (resourceType) =>
            resourceType.resourceTypeId == idResourceTypeSelected
        ),
        code: resourceCode,
        number: resourceNumber,
        location: locations.find(
          (location) => location.locationId == idLocationSelected
        ),
        capacity: resourceCapacity,
        isDisable: isDisable === "0" ? "FALSE" : "TRUE",
      },
      idFacultySelected
    );
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update(
      {
        resourceId: editingResource.resourceId,
        name: resourceName,
        description: resourceDescription,
        resourceType: resourceTypes.find(
          (resourceType) =>
            resourceType.resourceTypeId == idResourceTypeSelected
        ),
        code: resourceCode,
        number: resourceNumber,
        location: locations.find(
          (location) => location.locationId == idLocationSelected
        ),
        capacity: resourceCapacity,
        isDisable: isDisable === "0" ? "FALSE" : "TRUE",
      },
      idFacultySelected
    );
    limpiarForm();
  }

  if (locations) {
    return (
      <div className="max-w-md mx-auto ">
        <form onSubmit={handleSubmit} className="bg-blue p-10 mb-4 rounded-lg">
          <h1 className="text-2xl font-bold text-paleta2-red-claro mb-3">
            Crear un Recurso
          </h1>
          <input
            placeholder="Nombre del recurso"
            onChange={(e) => setResourceName(e.target.value)}
            autoFocus="on"
            className="bg-slate-500 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={resourceName}
          />

          <textarea
            name="Description"
            placeholder="Description"
            onChange={(e) => setResourceDescription(e.target.value)}
            className="bg-paleta2-fondo1 p-3 w-full mb-0 rounded-md"
            value={resourceDescription}
          ></textarea>

          <select
            id="facultySelected"
            name="facultyId"
            className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
            value={idFacultySelected}
            onChange={(e) => setIdFacultySelected(e.target.value)}
          >
            {facultys.map((faculty) => {
              return (
                <option key={faculty.facultyId} value={faculty.facultyId}>
                  {faculty.facultyName}{" "}
                </option>
              );
            })}
          </select>

          <select
            id="resourceTypeSelected"
            name="resourceTypeId"
            className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
            onChange={(e) => {
              setIdResourceTypeSelected(e.target.value);
            }}
            defaultValue={editingResource?.resourceType.resourceTypeId}
            value={editingResource?.resourceType.resourceTypeId}
          >
            {resourceTypes.map((resourceType) => {
              return (
                <option
                  key={resourceType.resourceTypeId}
                  value={resourceType.resourceTypeId}
                >
                  {resourceType.name}{" "}
                  {resourceType.disable ? "-" + resourceType.disable : ""}
                </option>
              );
            })}
          </select>

          <input
            placeholder="Código del recurso"
            onChange={(e) => setResourceCode(e.target.value)}
            className="bg-slate-500 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={resourceCode}
          />

          <input
            placeholder="Número del recurso"
            onChange={(e) => setResourceNumber(e.target.value)}
            className="bg-slate-500 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={resourceNumber}
          />

          <select
            id="locationSelected"
            name="locationId"
            className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
            onChange={(e) => {
              setIdLocationSelected(e.target.value);
            }}
            value={editingResource?.location.locationId}
            defaultValue={editingResource?.location.locationId}
          >
            {locations.map((location) => {
              return (
                <option key={location.locationId} value={location.locationId}>
                  {location.name} {location.city ? "-" + location.city : ""}
                </option>
              );
            })}
          </select>

          <input
            placeholder="Capacidad del recurso"
            onChange={(e) => setResourceCapacity(e.target.value)}
            className="bg-slate-500 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={resourceCapacity}
          />

          <div className="flex flex-wrap">
            <div className="flex items-center mr-4">
              <input
                id="red-radio"
                type="radio"
                value="0"
                onChange={() => setResourceDisable("1")}
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
                onChange={() => setResourceDisable("0")}
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

  return <></>;
}
