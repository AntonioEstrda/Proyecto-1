import { useContext } from "react";
import { FacultyResourceContext } from "../../context/FacultyResourceContext";

export default function FacultyResourceForm() {
  const {
    create,
    idResourceSelected,
    idFacultySelected,
    setIdResourceSelected,
    setIdFacultySelected,
    resources,
    facultys,
  } = useContext(FacultyResourceContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    create(
      {
        name: "Periodo 2022-2",
        initDate: "2022-05-19T00:00:00.000+00:00",
        finalDate: "2022-12-31T00:00:00.000+00:00",
        facultyId: idFacultySelected,
        resourceId: idResourceSelected,
      },
      idFacultySelected,
      idResourceSelected
    );
  };

  return (
    <div className="max-w-md mx-auto ">
      <form
        onSubmit={handleSubmit}
        className="bg-paleta2-purpura p-10 mb-4 rounded-lg"
      >
        <h1 className="text-2xl font-bold text-paleta2-azul-claro mb-3">
          Asignar un recurso
        </h1>

        <select
          id="facultySelected"
          name="facultyId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdFacultySelected(e.target.value);
          }}
        >
          {facultys?.map((faculty) => {
            return (
              <option key={faculty.facultyId} value={faculty.facultyId}>
                {faculty.facultyName}
              </option>
            );
          })}
        </select>

        <select
          id="resourceSelected"
          name="resourceId"
          className="bg-paleta2-azul-claro w-full text-lg text-paleta2-rojo rounded-md p-4 mb-2"
          onChange={(e) => {
            setIdResourceSelected(e.target.value);
          }}
        >
          {[...resources?.keys()].map((facultyId) => {
            return resources?.get(+facultyId).map((resource) => {
              return (
                <option key={resource.resourceId} value={resource.resourceId}>
                  {resource.name}
                </option>
              );
            });
          })}
        </select>

        <div className="grid grid-cols-1">
          <button className="bg-paleta2-azulverd rounded-md px-8 py-3 text-paleta2-claro ">
            Guardar
          </button>
        </div>
      </form>
    </div>
  );
}
