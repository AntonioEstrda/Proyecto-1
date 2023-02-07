import { useContext } from "react";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeCard({ resourceType }) {
  const { deleteById, setEditingResourceType } = useContext(
    ResourceTypeContext
  );

  function defineEditItem() {
    setEditingResourceType(resourceType);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-naranja p-4 rounded-md">
      <div className="bg-gray text-paleta2-azul-claro p-4 rounded-md">
        <h1 className="text-xl font-bold capitalize">
          Recurso: {resourceType?.name}
        </h1>

        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-lg font-bold text-blue">
            Parent:
          </p>
          {resourceType?.parent}
        </div>

        <div className="bg-fondo3 text-paleta2-rojo p-4 mt-2 rounded-md">
          <p className="text-lg font-bold text-blue">
            Activo:
          </p>
          {resourceType?.isDisable === "1" ? "No" : "Sí"}
        </div>

        <div className="grid grid-cols-2 gap-3">
          <button
            className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={() => deleteById(resourceType?.resourceTypeId)}
          >
            Deshabilitar
          </button>

          <button
            className="bg-boton2 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
            onClick={defineEditItem}
          >
            Editar
          </button>
        </div>
      </div>
    </div>
  );
}

/**
 * El botón eliminar que no está funcionando :v
 * 
 */