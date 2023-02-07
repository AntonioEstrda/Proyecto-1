import { useContext } from "react";
import { EventContext } from "../../context/EventContext";

export default function EventCard({ event }) {
  const { deleteById, setEditingEvent } = useContext(EventContext);

  function defineEditItem() {
    setEditingEvent(event);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{event?.name}</h1>
      <h1 className="text-xl font-bold capitalize">{event?.description}</h1>
      <h1 className="text-xl font-bold capitalize">{event?.code}</h1>
      <h1 className="text-xl font-bold capitalize">{event?.type}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Profesor:</p>
        {event.teacher?.firstName}
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Periodo AC:</p>
        {event.academicPeriod?.name}
      </div>
      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Departamento:</p>
        {event.department?.name}
      </div>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(event?.id, event?.department?.departmentId)}
        >
          Eliminar
        </button>
        <button
          className="bg-boton2 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={defineEditItem}
        >
          Editar
        </button>
      </div>
    </div>
  );
}
