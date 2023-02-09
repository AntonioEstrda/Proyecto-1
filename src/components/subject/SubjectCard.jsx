import { useContext } from "react";
import { SubjectContext } from "../../context/SubjectContext";

export default function SubjectCard({ subject }) {
  const { deleteById, setEditingSubject } = useContext(SubjectContext);

  function defineEditItem() {
    setEditingSubject(subject);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{subject?.name}</h1>
      <h1 className="text-xl font-bold capitalize">{subject?.requisits}</h1>
      <h1 className="text-xl font-bold capitalize">{subject?.semester}</h1>
      <h1 className="text-xl font-bold capitalize">{subject?.intensity}</h1>
      <h1 className="text-xl font-bold capitalize">{subject?.modality}</h1>
      <h1 className="text-xl font-bold capitalize">{subject?.type}</h1>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md">
        <p className="text-lg font-bold text-blue">Programa:</p>
        {subject.program?.name}
      </div>

      <h1 className="text-xl font-bold capitalize">{subject?.code}</h1>

      <div className="grid grid-cols-2 gap-3">
        <button
          className="bg-paleta2-morado px-2 py-1 rounded-md mt-4 hover:bg-red-400"
          onClick={() => deleteById(subject?.subjectID)}
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
