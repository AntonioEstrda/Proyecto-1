import ResourceList from "../../components/resource/ResourceList";
import ResourceForm from "../../components/resource/ResourceForm";

function AppResource() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <ResourceForm />
        <ResourceList />
      </div>
    </main>
  );
}

export default AppResource;
