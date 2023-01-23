import DepartmentList from "../../components/department/DepartmentList";
import DepartmentForm from "../../components/department/DepartmentForm";

export default function AppDepartment() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <DepartmentForm />
        <DepartmentList />
      </div>
    </main>
  );
}
