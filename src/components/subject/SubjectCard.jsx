import { useContext } from "react";
import { SubjectContext } from "../../context/SubjectContext";

export default function SubjectCard({ subject }) {
  const { deleteById, setEditingSubject } = useContext(SubjectContext);

  function defineEditItem() {
    setEditingSubject(subject);
  }

  return (
    <div className="bg-paleta2-purpura text-paleta2-azul-claro p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize border-b-2 px-2 pb-2">
        {subject?.name}
      </h1>
      <div className="grid grid-cols-2 text-xl font-bold capitalize">
        <h6>Semestre:</h6>
        <h1>{subject?.semester}</h1>
        <h1>Intensidad:</h1>
        <h1> {subject?.intensity}</h1>
        <h1>Modalidad:</h1>
        <h1> {subject?.modality}</h1>
        <h1>Tipo:</h1>
        <h1> {subject?.type}</h1>
        <h1>Código:</h1>
        <h1>{subject?.code}</h1>
      </div>

      <div className="bg-paleta2-azulverd text-paleta2-naranja p-4 mt-2 rounded-md text-center grid grid-cols-2">
        <p className="text-lg font-bold text-blue">Programa:</p>
        <p className="text-lg font-bold">{subject.program?.name}</p>
      </div>

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
