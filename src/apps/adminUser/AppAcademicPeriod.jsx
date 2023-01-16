import AcademicPeriodList from "../../components/academicPeriod/AcademicPeriodList";
import AcademicPeriodForm from "../../components/academicPeriod/AcademicPeriodForm";

export default function AppResource() {
  return (
    <main className="bg-paleta2-claro h-full">
      <div className="container mx-auto p-10">
        <AcademicPeriodForm />
        <AcademicPeriodList />
      </div>
    </main>
  );
}
