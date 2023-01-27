import TeacherGroupList from "../../components/teacherGroup/TeacherGroupList";
import TeacherGroupForm from "../../components/teacherGroup/TeacherGroupForm";

export default function AppTeacherGroup() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <TeacherGroupForm />
        <TeacherGroupList />
      </div>
    </main>
  );
}
