import { useContext } from "react";
import { ScheduleCMSContext } from "../../context/ScheduleCMSContext";

export default function ScheduleCMSCard({ scheduleCMS }) {
  const { deleteById, setEditingScheduleCMS } = useContext(
    ScheduleCMSContext
  );

  function defineEditItem() {
    setEditingScheduleCMS(scheduleCMS);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{scheduleCMS?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {scheduleCMS?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {scheduleCMS?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(scheduleCMS?.scheduleCMSID)}
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
