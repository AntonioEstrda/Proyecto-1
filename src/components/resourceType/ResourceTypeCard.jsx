import { useContext } from "react";
import { ResourceTypeContext } from "../../context/ResourceTypeContext";

export default function ResourceTypeCard({ resourceType }) {
  const { deleteResourceType } = useContext(ResourceTypeContext);

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{resourceType?.name}</h1>
      <p className="text-paleta2-red-claro text-sm">{resourceType?.parent}</p>
      <p className="text-paleta2-red-claro text-sm">
        {resourceType?.disable ? "Inactivo" : "Activo"}
      </p>
      <button
        className="bg-paleta2-rojo px-2 py-1 rounded-md mt-4 hover:bg-red-400"
        onClick={() => deleteResourceType(resourceType?.resourceTypeId)}
      >
        Eliminar Tipo de Recurso
      </button>
    </div>
  );
}
