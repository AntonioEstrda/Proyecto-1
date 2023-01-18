import TeacherList from "../../components/teacher/TeacherList";
import TeacherForm from "../../components/teacher/TeacherForm";

export default function AppTeacher() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <TeacherForm />
        <TeacherList />
      </div>
    </main>
  );
}
