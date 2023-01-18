import ResourceTypeList from "../../components/resourceType/ResourceTypeList";
import ResourceTypeForm from "../../components/resourceType/ResourceTypeForm";

export default function AppResource() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <ResourceTypeForm />
        <ResourceTypeList />
      </div>
    </main>
  );
}
