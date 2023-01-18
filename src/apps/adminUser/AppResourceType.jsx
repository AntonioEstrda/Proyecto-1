import ResourceTypeList from "../../components/resourceType/ResourceTypeList";
import ResourceTypeForm from "../../components/resourceType/ResourceTypeForm";

export default function AppResourceType() {
  return (
    <main className="bg-paleta2-claro h-full">
      <div className="container mx-auto p-10">
        <ResourceTypeForm />
        <ResourceTypeList />
      </div>
    </main>
  );
}
