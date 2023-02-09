import { useContext } from "react";
import { ResourceContext } from "../../context/ResourceContext";

export default function ResourceCard({ resource, facultyId }) {
  const { deleteById, setEditingResource } = useContext(ResourceContext);

  function defineEditItem() {
    setEditingResource(resource);
  }

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{resource?.name}</h1>
      <h1 className="text-xl font-bold capitalize">{resource?.description}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Tipo de Recurso:</p>
        {resource.resourceType?.name}
        {resource.resourceType?.disable
          ? "-" + resource.resourceType.disable
          : ""}
      </div>
      <h1 className="text-xl font-bold capitalize">{resource?.code}</h1>
      <h1 className="text-xl font-bold capitalize">{resource?.number}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Localización:</p>
        {resource.location?.name}
        {resource.location?.city ? "-" + resource.location.city : ""}
      </div>

      <h1 className="text-xl font-bold capitalize">{resource?.capacity}</h1>
      <h1 className="text-xl font-bold capitalize">{resource?.disable}</h1>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-rojo px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(facultyId, resource?.resourceId)}
        >
          Eliminar
        </button>

        <button
          className="bg-amber-700 text-stone-50 px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={defineEditItem}
        >
          Editar
        </button>
      </div>
    </div>
  );
}
