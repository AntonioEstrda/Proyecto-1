import { useContext } from "react";
import GroupCard from "./GroupCard";
import { GroupContext } from "../../context/GroupContext";

export default function GroupList() {
  const { groups } = useContext(GroupContext);

  if (groups.length === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Periodos Académicos registrados
      </h1>
    );
  }

  return (
    <div className="grid grid-cols-4 gap-2">
      {groups.map((groups) => (
        <GroupCard
          key={groups.groupID}
          group={groups}
        />
      ))}
    </div>
  );
}
