import ScheduleParametrizer from "../components/schedule/ScheduleParametrizer";
import ScheduleCMS from "../components/schedule/ScheduleCMS";

function AppSchedule() {
  return (
    <main className="bg-slate-500 h-full">
      <div className="container mx-auto p-10">
        <ScheduleCMS />
        <ScheduleParametrizer />
      </div>
    </main>
  );
}

export default AppSchedule;
