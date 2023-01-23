import EventList from "../../components/event/EventList";
import EventForm from "../../components/event/EventForm";

export default function AppEvent() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <EventForm />
        <EventList />
      </div>
    </main>
  );
}
