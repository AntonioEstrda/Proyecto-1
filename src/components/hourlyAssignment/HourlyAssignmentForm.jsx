import { useState, useContext, useEffect } from "react";
import { HourlyAssignmentContext } from "../../context/HourlyAssignmentContext";

export default function HourlyAssignmentForm() {
  const { create, editingHourlyAssignment, update } = useContext(
    HourlyAssignmentContext
  );

  const [limpio, setLimpio] = useState(true);
  const [name, setName] = useState("");
  const [initDate, setInitDate] = useState(
    new Date().toISOString().split("T")[0]
  );
  const [finalDate, setFinalDate] = useState(
    new Date().toISOString().split("T")[0]
  );

  useEffect(() => {
    if (editingHourlyAssignment) {
      setName(editingHourlyAssignment.name);
      setInitDate(editingHourlyAssignment.initDate);
      setFinalDate(editingHourlyAssignment.finalDate);
      setLimpio(false);
    }
  }, [editingHourlyAssignment]);

  const handleSubmit = (e) => {
    limpio ? crear(e) : actualizar(e);
  };

  function limpiarForm() {
    setName("");
    setInitDate(new Date().toISOString().split("T")[0]);
    setFinalDate(new Date().toISOString().split("T")[0]);
    setLimpio(true);
  }

  function crear(e) {
    e.preventDefault();
    create({ name, initDate, finalDate });
    limpiarForm();
  }
  function actualizar(e) {
    e.preventDefault();
    update({
      hourlyAssignmentID: editingHourlyAssignment.hourlyAssignmentID,
      name,
      initDate,
      finalDate,
    });
    limpiarForm();
  }

  return (
    <div className="max-w-md mx-auto ">
      <form onSubmit={handleSubmit} className="bg-paleta2-purpura p-10 mb-4 rounded-lg">
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Crear un Periodo Académico
        </h1>
        <input
          placeholder="Nombre periodo académico"
          onChange={(e) => setName(e.target.value)}
          autoFocus="on"
          className="bg-paleta2-fondo1 text-neutral-200 p-3 w-full mb-2 rounded-md"
          value={name}
        />
        <label className="text-paleta2-azul-claro">
          Fecha de inicio:
          <input
            type="date"
            name="initDate"
            className="bg-paleta2-fondo1 p-3 w-full mb-2 rounded-md"
            value={initDate}
            onChange={(e) => setInitDate(e.target.value)}
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
