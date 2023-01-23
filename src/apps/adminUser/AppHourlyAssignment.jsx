import HourlyAssignmentList from "../../components/hourlyAssignment/HourlyAssignmentList";
import HourlyAssignmentForm from "../../components/hourlyAssignment/HourlyAssignmentForm";

export default function AppHourlyAssignment() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <HourlyAssignmentForm />
        <HourlyAssignmentList />
      </div>
    </main>
  );
}
