import ScheduleCMSList from "../../components/scheduleCMS/ScheduleCMSList";
import ScheduleCMSForm from "../../components/scheduleCMS/ScheduleCMSForm";

export default function AppScheduleCMS() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <ScheduleCMSForm />
        <ScheduleCMSList />
      </div>
    </main>
  );
}
