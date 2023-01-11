import FacultyList from "../components/faculty/FacultyList";
import FacultyForm from "../components/faculty/FacultyForm";

function AppFaculty() {
  return (
    <main className="bg-paleta2-claro h-full">
      <div className="container mx-auto p-10">
        <FacultyForm />
        <FacultyList />
      </div>
    </main>
  );
}

export default AppFaculty;
