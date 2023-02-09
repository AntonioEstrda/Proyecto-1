import { useState, useContext, useEffect } from "react";
import { EventContext } from "../../context/EventContext";

export default function EventForm() {
  const {
    create,
    editingEvent,
    update,
    idTeacherSelected,
    idDepartmentSelected,
    idAPSelected,
    setIdTeacherSelected,
    setIdDepartmentSelected,
    setIdAPSelected,
    teachers,
    departments,
    academicPeriods,
  } = useContext(EventContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [code, setCode] = useState("");
  const [type, setType] = useState("");

  useEffect(() => {
    if (editingEvent) {
      setName(editingEvent.name);
      setDescription(editingEvent.description);
      setCode(editingEvent.code);
      setType(editingEvent.type);
      setLimpio(false);
      setIdTeacherSelected(editingEvent.teacher.teacherID);
      setIdDepartmentSelected(editingEvent.department.departmentId);
      setIdAPSelected(editingEvent.academicPeriod.academicPeriodID);
    }
  }, [editingEvent]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setDescription("");
    setCode("");
    setType("");
    setLimpio(true);
    setIdTeacherSelected(0);
    setIdDepartmentSelected(0);
    setIdAPSelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      description,
      code,
      type,
      teacher: teachers.find(
        (teacher) => teacher.teacherID == idTeacherSelected
      ),
      ap: academicPeriods.find(
        (resourceType) => resourceType.academicPeriodID == idAPSelected
      ),
      department: departments.find(
        (department) => department.departmentId == idDepartmentSelected
      ),
    });
    limpiarForm();
  }

  function actualizar(e) {
    e.preventDefault();
    update({
      id: editingEvent.id,
      name,
      description,
      code,
      type,
      teacher: teachers.find(
        (teacher) => teacher.teacherID == idTeacherSelected
      ),
      ap: resourceTypes.find(
        (resourceType) => resourceType.academicPeriodID == idAPSelected
      ),
      department: departments.find(
        (department) => department.departmentId == idDepartmentSelected
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
        <h1 className="text-2xl text-center font-bold text-paleta2-azul-claro mb-3">
          Crear un Evento
        </h1>
        <input
          placeholder="Nombre periodo académico"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={name}
        />

        <textarea
          name="Descripción"
          placeholder="Description"
          onChange={(e) => setDescription(e.target.value)}
          className="bg-paleta2-fondo1 p-3 w-full mb-0 rounded-md"
          value={description}
        ></textarea>

        <input
          placeholder="Código"
          onChange={(e) => setCode(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={code}
        />

        <input
          placeholder="Tipo"
          onChange={(e) => setType(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
          value={type}
        />

        <select
          id="teacherSelected"
          name="teacherId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdTeacherSelected(e.target.value);
          }}
        >
          {teachers.map((teacher) => {
            return (
              <option
                key={teacher.teacherID}
                value={teacher.teacherID}
                selected={
                  teacher.teacherID === editingEvent?.teacher.teacherID
                    ? true
                    : false
                }
              >
                {teacher.firstName}{" "}
              </option>
            );
          })}
        </select>

        <select
          id="academicPeriodSelected"
          name="academicPeriodID"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdAPSelected(e.target.value);
          }}
        >
          {academicPeriods.map((academicPeriod) => {
            return (
              <option
                key={academicPeriod.academicPeriodID}
                value={academicPeriod.academicPeriodID}
                selected={
                  academicPeriod.academicPeriodID ===
                  editingEvent?.academicPeriod.academicPeriodID
                    ? true
                    : false
                }
              >
                {academicPeriod.name}{" "}
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
                  editingEvent?.department.departmentId
                    ? true
                    : false
                }
              >
                {department.name}{" "}
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
