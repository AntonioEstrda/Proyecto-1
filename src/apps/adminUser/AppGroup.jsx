import GroupList from "../../components/group/GroupList";
import GroupForm from "../../components/group/GroupForm";

export default function AppGroup() {
  return (
    <main className="bg-fondo2 h-full">
      <div className="container mx-auto p-10">
        <GroupForm />
        <GroupList />
      </div>
    </main>
  );
}
