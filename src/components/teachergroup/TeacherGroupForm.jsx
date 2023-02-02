import { useState, useContext, useEffect } from "react";
import { TeacherGroupContext } from "../../context/TeacherGroupContext";

export default function TeacherGroupForm() {
  const {
    create,
    editingTeacherGroup,
    update,
    idTeacherSelected,
    idGroupSelected,
    setIdTeacherSelected,
    setIdGroupSelected,
    teachers,
    groups,
  } = useContext(TeacherGroupContext);

  const [limpio, setLimpio] = useState(true);

  useEffect(() => {
    if (editingTeacherGroup) {
      setLimpio(false);
      setIdTeacherSelected(editingTeacherGroup.teacher.teacherID);
      setIdGroupSelected(editingTeacherGroup.group.groupId);
    }
  }, [editingTeacherGroup]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setLimpio(true);
    setIdTeacherSelected(0);
    setIdGroupSelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      teacher: teachers.find(
        (teacher) => teacher.teacherID == idTeacherSelected
      ),
      group: groups.fnd((group) => group.groupId == idGroupSelected),
    });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      teacherGroupID: editingTeacherGroup.teacherGroupID,
      teacher: teachers.find(
        (teacher) => teacher.teacherID == idTeacherSelected
      ),
      group: groups.fnd((group) => group.groupId == idGroupSelected),
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
          Crear un Grupo de Profesores
        </h1>

        <select
          id="teacherSelected"
          name="teacherID"
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
                  teacher.locationId === editingTeacherGroup?.teacher.teacherID
                    ? true
                    : false
                }
              >
                {teacher.fisrtName}{" "}
              </option>
            );
          })}
        </select>

        <select
          id="groupSelected"
          name="groupId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdGroupSelected(e.target.value);
          }}
        >
          {groups.map((group) => {
            return (
              <option
                key={group.groupId}
                value={group.groupId}
                selected={
                  group.groupId === editingTeacherGroup?.group.groupId
                    ? true
                    : false
                }
              >
                {group.name}{" "}
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
