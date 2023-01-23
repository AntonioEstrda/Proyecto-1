import { useContext } from "react";
import { EventContext } from "../../context/EventContext";

export default function EventCard({ event }) {
  const { deleteById, setEditingEvent } = useContext(
    EventContext
  );

  function defineEditItem() {
    setEditingEvent(event);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{event?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {event?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {event?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(event?.eventID)}
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
