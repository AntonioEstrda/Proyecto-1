import ResourceList from "../components/resource/ResourceList";
import ResourceForm from "../components/resource/ResourceForm";

function ResourceApp() {
  return (
    <main className="bg-zinc-900 h-full">
      <div className="container mx-auto p-10">
        <ResourceForm />
        <ResourceList />
      </div>
    </main>
  );
}

export default ResourceApp;
