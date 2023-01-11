import { useContext } from "react";
import { FacultyContext } from "../../context/FacultyContext";

function FacultyCard({ faculty }) {
  const { deleteFaculty } = useContext(FacultyContext);

  return (
    <div className="bg-blue text-paleta2-naranja p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{faculty.facultyName}</h1>
      <p className=" text-sm">{faculty.location.name}</p>
      <button
        className="bg-paleta2-rojo p-2 py-1 rounded-md mt-4"
        onClick={() => deleteFaculty(faculty.facultyId)}
      >
        Eliminar Facultad
      </button>
    </div>
  );
}

export default FacultyCard;
