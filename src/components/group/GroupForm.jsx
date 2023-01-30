import { useState, useContext, useEffect } from "react";
import { GroupContext } from "../../context/GroupContext";

export default function GroupForm() {
  const {
    create,
    editingGroup,
    update,
    idSubjectSelected,
    setIdSubjectSelected,
    subjects,
    idAcademicPeriodSelected,
    setIdAcademicPeriodSelected,
    academicPeriods,
  } = useContext(GroupContext);

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [capacity, setCapacity] = useState("");

  useEffect(() => {
    if (editingGroup) {
      setName(editingGroup.name);
      setCapacity(editingGroup.capacity);
      setLimpio(false);
      setIdSubjectSelected(editingGroup.subject.subjectID);
      setIdAcademicPeriodSelected(editingGroup.academicPeriod.academicPeriodID);
    }
  }, [editingGroup]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setCapacity("");
    setLimpio(true);
    setIdSubjectSelected(0);
    setIdAcademicPeriodSelected(0);
  }

  function crear(e) {
    e.preventDefault();
    create({
      name,
      capacity,
      subject: subjects.find(
        (subject) => subject.subjectID == idSubjectSelected
      ),
      academicPeriod: academicPeriods.find(
        (academicPeriod) => academicPeriod.academicPeriodID == idAcademicPeriodSelected
      ),
    });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      groupId: editingGroup.groupId,
      name,
      capacity,
      subject: subjects.find(
        (subject) => subject.subjectID == idSubjectSelected
      ),
      academicPeriod: academicPeriods.find(
        (academicPeriod) => academicPeriod.academicPeriodID == idAcademicPeriodSelected
      ),
    });
    limpiarForm();
  }

  if (academicPeriods) {
    return (
      <div className="max-w-md mx-auto ">
        <form onSubmit={handleSubmit} className="bg-paleta2-purpura p-10 mb-4 rounded-lg">
          <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
            Crear un Grupo
          </h1>
          <input
            placeholder="Grupo:"
            onChange={(e) => setName(e.target.value)}
            autoFocus="on"
            className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={name}
          />

          <input
            placeholder="Capacidad:"
            onChange={(e) => setCapacity(e.target.value)}
            autoFocus="on"
            className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
            value={capacity}
          />

          <select
            id="subjectSelected"
            name="subjectID"
            className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
            onChange={(e) => {
              setIdSubjectSelected(e.target.value);
            }}
          >
            {subjects.map((subject) => {
              return (
                <option
                  key={subject.subjectID}
                  value={subject.subjectID}
                  selected={
                    subject.subjectID === editingGroup?.subject.subjectID
                      ? true
                      : false
                  }
                >
                  {subject.name}{" "}
                </option>
              );
            })}
          </select>

          <select
            id="academicPeriodSelected"
            name="academicPeriodID"
            className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
            onChange={(e) => {
              setIdAcademicPeriodSelected(e.target.value);
            }}
          >
            {academicPeriods.map((academicPeriod) => {
              return (
                <option
                  key={academicPeriod.academicPeriodID}
                  value={academicPeriod.academicPeriodID}
                  selected={
                    academicPeriod.academicPeriodID === editingGroup?.academicPeriod.academicPeriodID
                      ? true
                      : false
                  }
                >
                  {academicPeriod.name}{" "}
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

  return <></>
}
