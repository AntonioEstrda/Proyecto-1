import { useState, useContext, useEffect } from "react";
import { DepartmentContext } from "../../context/DepartmentContext";

export default function DepartmentForm() {
  const {
    create,
    editingDepartment,
    update,
    idLocationSelected,
    idFacultySelected,
    setIdLocationSelected,
    setIdFacultySelected,
    locations,
    facultys,
  } = useContext(DepartmentContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [code, setCode] = useState("");

  useEffect(() => {
    if (editingDepartment) {
      setName(editingDepartment.name);
      setCode(editingDepartment.code);
      setLimpio(false);
      setIdLocationSelected(editingDepartment.location.locationId);
      setIdFacultySelected(editingDepartment.facultad.facultyId);
    }
  }, [editingDepartment]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setCode("");
    setLimpio(true);
    setIdLocationSelected(0);
    setIdFacultySelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      code,
      location: locations.find(
        (location) => location.locationId == idLocationSelected
      ),
      facultad: facultys.find(
        (facultad) => facultad.facultyId == idFacultySelected
      ),
    });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      departmentId: editingDepartment.departmentId,
      name,
      code,
      location: locations.find(
        (location) => location.locationId == idLocationSelected
      ),
      facultad: facultys.find(
        (facultad) => facultad.facultyId == idFacultySelected
      ),
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
          Crear un Departamento
        </h1>
        <input
          placeholder="Nombre"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />

        <input
          placeholder="Código"
          onChange={(e) => setCode(e.target.value)}
          autoFocus="on"
          className="bg-fondo5 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={code}
        />

        <select
          id="locationSelected"
          name="locationId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdLocationSelected(e.target.value);
          }}
        >
          {locations.map((location) => {
            return (
              <option
                key={location.locationId}
                value={location.locationId}
                selected={
                  location.locationId === editingDepartment?.location.locationId
                    ? true
                    : false
                }
              >
                {location.name} {location.city ? "-" + location.city : ""}
              </option>
            );
          })}
        </select>

        <select
          id="facultySelected"
          name="facultyId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdFacultySelected(e.target.value);
          }}
        >
          {facultys.map((facultad) => {
            return (
              <option
                key={facultad.facultyId}
                value={facultad.facultyId}
                selected={
                  facultad.facultyId === editingDepartment?.facultad.facultyId
                    ? true
                    : false
                }
              >
                {facultad.facultyName}{" "}
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
