import { useState, useContext, useEffect } from "react";
import { ProgramContext } from "../../context/ProgramContext";

export default function ProgramForm() {
  const {
    create,
    editingProgram,
    update,
    idLocationSelected,
    idDepartmentSelected,
    setIdLocationSelected,
    setIdDepartmentSelected,
    locations,
    departments,
  } = useContext(ProgramContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [color, setColor] = useState("");

  useEffect(() => {
    if (editingProgram) {
      setName(editingProgram.name);
      setCode(editingProgram.code);
      setColor(editingProgram.color);
      setLimpio(false);
      setIdLocationSelected(editingProgram.location.locationId);
      setIdDepartmentSelected(editingProgram.department.departmentId);
    }
  }, [editingProgram]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setCode("");
    setColor("");
    setLimpio(true);
    setIdLocationSelected(0);
    setIdDepartmentSelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      code,
      location: locations.find(
        (location) => location.locationId == idLocationSelected
      ),
      department: departments.find(
        (department) => department.departmentId == idDepartmentSelected
      ),
      color,
    });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      programId: editingProgram.programId,
      name,
      code,
      location: locations.find(
        (location) => location.locationId == idLocationSelected
      ),
      department: departments.find(
        (department) => department.departmentId == idDepartmentSelected
      ),
      color,
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
          Crear un Programa
        </h1>
        <input
          placeholder="Nombre del Programa"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={name}
        />

        <input
          placeholder="Código"
          onChange={(e) => setCode(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
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
                  location.locationId === editingProgram?.location.locationId
                    ? true
                    : false
                }
              >
                {location.name}{" "}
              </option>
            );
          })}
        </select>

        <select
          id="departmentSelected"
          name="departmentId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdDepartmentSelected(e.target.value);
          }}
        >
          {departments.map((department) => {
            return (
              <option
                key={department.departmentId}
                value={department.departmentId}
                selected={
                  department.departmentId ===
                  editingProgram?.department.departmentId
                    ? true
                    : false
                }
              >
                {department.name}{" "}
              </option>
            );
          })}
        </select>

        <input
          placeholder="Color"
          onChange={(e) => setColor(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={color}
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
