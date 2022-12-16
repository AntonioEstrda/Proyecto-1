import ResourceTypeList from "../components/resourcetype/ResourceTypeList";
import ResourceTypeForm from "../components/resourceType/ResourceTypeForm";

export default function AppResource() {
  return (
    <main className="bg-zinc-900 h-full">
      <div className="container mx-auto p-10">
        <ResourceTypeForm />
        <ResourceTypeList />
      </div>
    </main>
  );
}
