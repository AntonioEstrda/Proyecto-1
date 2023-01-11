import { useContext } from "react";
import { ResourceContext } from "../../context/ResourceContext";

function ResourceCard({ resource }) {
  const { deleteResource } = useContext(ResourceContext);

  return (
    <div className="bg-slate-900 text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{resource?.name}</h1>
      <p className="text-paleta2-claro text-sm">{resource?.description}</p>
      <p className="text-paleta2-claro text-sm">{resource?.resourceType.name}</p>
      <p className="text-paleta2-claro text-sm">
        {resource?.disable ? "Inactivo" : "Activo"}
      </p>
      <button
        className="bg-paleta2-rojo p-2 py-1 rounded-md mt-4"
        onClick={() => deleteResource(resource?.resourceId)}
      >
        Eliminar Recurso
      </button>
    </div>
  );
}

export default ResourceCard;
