import { useContext } from "react";
import TeacherCard from "./TeacherCard";
import { TeacherContext } from "../../context/TeacherContext";

export default function TeacherList() {
  const { teachers } = useContext(TeacherContext);

  if (teachers.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Licenciados registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {teachers.map((teacher) => (
        <TeacherCard
          key={teacher.teacherID}
          teacher={teacher}
        />
      ))}
    </div>
  );
}
