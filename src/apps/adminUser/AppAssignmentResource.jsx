import AssignmentResourceList from "../../components/assignmentResource/AssignmentResourceList";
import AssignmentResourceForm from "../../components/assignmentResource/AssignmentResourceForm";

export default function AppAssignmentResource() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <AssignmentResourceForm />
        <AssignmentResourceList />
      </div>
    </main>
  );
}
