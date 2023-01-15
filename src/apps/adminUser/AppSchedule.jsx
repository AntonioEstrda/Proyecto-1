import ScheduleParametrizer from "../../components/schedule/ScheduleParametrizer";
import ScheduleGUI from "../../components/schedule/ScheduleGUI";
import ScheduleInfo from "../../components/schedule/ScheduleInfo";

function AppSchedule() {
  return (
    <main className="bg-slate-500 h-full">
      <div className="container mx-auto p-4 flex w-full tablet:min-w-full">
        <div className="mx-2 justify-items-center min-w-max w-25">
          <ScheduleParametrizer />
        </div>
        <div className="w-75 mx-2">
          <ScheduleInfo />
          <ScheduleGUI />
        </div>
      </div>
    </main>
  );
}

export default AppSchedule;
