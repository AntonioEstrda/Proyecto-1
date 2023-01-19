import FacultyList from "../../components/faculty/FacultyList";
import FacultyForm from "../../components/faculty/FacultyForm";

export default function AppFaculty() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <FacultyForm />
        <FacultyList />
      </div>
    </main>
  );
}
