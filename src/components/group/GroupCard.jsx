import { useContext } from "react";
import { GroupContext } from "../../context/GroupContext";

export default function GroupCard({ group }) {
  const { deleteById, setEditingGroup } = useContext(
    GroupContext
  );

  function defineEditItem() {
    setEditingGroup(group);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{group?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha inicio: {group?.initDate}
      </p>
      <p className="text-paleta2-azul-claro text-sm">
        Fecha final: {group?.finalDate}
      </p>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(group?.groupID)}
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
