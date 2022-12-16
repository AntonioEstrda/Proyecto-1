import ScheduleParametrizer from "../components/schedule/ScheduleParametrizer";
import ScheduleGUI from "../components/schedule/ScheduleGUI";
import ScheduleInfo from "../components/schedule/ScheduleInfo";

function AppSchedule() {
  return (
    <main className="bg-slate-500 h-full">
      <div className="container w-full mx-auto p-10">
        <div className="grid justify-items-center">
          <ScheduleParametrizer />
        </div>
        <ScheduleInfo />
        <ScheduleGUI />
      </div>
    </main>
  );
}

export default AppSchedule;
