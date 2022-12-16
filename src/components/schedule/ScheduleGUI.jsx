import { useContext } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleGUI({ faculty }) {
  const { deleteSchedule } = useContext(ScheduleContext);

  return (
    <div className="bg-gray-800 text-white p-4 rounded-md">
      <h1 className="text-xl font-bold capitalize">{faculty.facultyName}</h1>
      <p className="text-gray-500 text-sm">{faculty.location.name}</p>
      <button
        className="bg-red-500 p-2 py-1 rounded-md mt-4"
        onClick={() => deleteSchedule(faculty.facultyId)}
      >
        Eliminar Facultad
      </button>
    </div>
  );
}

export default ScheduleGUI;
