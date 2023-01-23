import { useContext } from "react";
import EventCard from "./EventCard";
import { EventContext } from "../../context/EventContext";

export default function EventList() {
  const { events } = useContext(EventContext);

  if (events.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {events.map((events) => (
        <EventCard
          key={events.eventID}
          event={events}
        />
      ))}
    </div>
  );
}
