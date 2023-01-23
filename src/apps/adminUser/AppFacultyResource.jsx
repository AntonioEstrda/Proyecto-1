import FacultyResourceList from "../../components/facultyResource/FacultyResourceList";
import FacultyResourceForm from "../../components/facultyResource/FacultyResourceForm";

export default function AppFacultyResource() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <FacultyResourceForm />
        <FacultyResourceList />
      </div>
    </main>
  );
}
