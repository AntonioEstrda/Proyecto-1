import SubjectList from "../../components/subject/SubjectList";
import SubjectForm from "../../components/subject/SubjectForm";

export default function AppSubject() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <SubjectForm />
        <SubjectList />
      </div>
    </main>
  );
}
