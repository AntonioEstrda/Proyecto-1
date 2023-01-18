import LocationList from "../../components/location/LocationList";
import LocationForm from "../../components/location/LocationForm";

export default function AppLocation() {
  return (
    <main className="bg-paleta2-claro h-full">
      <div className="container mx-auto p-10">
        <LocationForm />
        <LocationList />
      </div>
    </main>
  );
}