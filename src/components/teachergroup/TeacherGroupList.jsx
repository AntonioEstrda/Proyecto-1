import { useContext } from "react";
import TeacherGroupCard from "./TeacherGroupCard";
import { TeacherGroupContext } from "../../context/TeacherGroupContext";

export default function TeacherGroupList() {
  const { teacherGroups } = useContext(TeacherGroupContext);

  if (teacherGroups.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Grupos de Profesores
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {teacherGroups.map((teacherGroups) => (
        <TeacherGroupCard
          key={teacherGroups.teacherGroupID}
          teacherGroup={teacherGroups}
        />
      ))}
    </div>
  );
}
