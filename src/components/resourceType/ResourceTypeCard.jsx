import { useContext } from "react";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeCard({ resourceType }) {
  const { deleteById, setEditingResourceType } =
    useContext(ResourceTypeContext);

  function defineEditItem() {
    setEditingResourceType(resourceType);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{resourceType?.name}</h1>
      <p className="text-paleta2-azul-claro text-sm">{resourceType?.parent}</p>
      <p className="text-paleta2-azul-claro text-sm">
        {resourceType?.disable ? "Inactivo" : "Activo"}
      </p>
      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(resourceType?.resourceTypeId)}
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
