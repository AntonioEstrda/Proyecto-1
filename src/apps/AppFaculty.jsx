import FacultyList from "../components/faculty/FacultyList";
import FacultyForm from "../components/faculty/FacultyForm";

function FacultyApp() {
  return (
    <main className="bg-zinc-900 h-screen">
      <div className="container mx-auto p-10">
        <FacultyForm />
        <FacultyList />
      </div>
    </main>
  );
}

export default FacultyApp;
