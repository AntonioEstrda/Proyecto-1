import ProgramList from "../../components/program/ProgramList";
import ProgramForm from "../../components/program/ProgramForm";

export default function AppProgram() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <ProgramForm />
        <ProgramList />
      </div>
    </main>
  );
}
