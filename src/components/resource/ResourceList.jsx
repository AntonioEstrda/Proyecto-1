import { useContext, useEffect, useState } from "react";
import ResourceCard from "./ResourceCard";
import { ResourceContext } from "../../context/ResourceContext";

export default function ResourceList() {
  const { resources } = useContext(ResourceContext);

  if (resources.size === 0) {
    return (
      <h1 className="text-white text-4xl font-bold text-center">
        No hay Recursos registrados
      </h1>
    );
  }
  return (
    <div className="grid lg:grid-cols-4 md:grid-cols-3 sm:grid-cols-2 gap-2">
      {[...resources.keys()].map((facultyId) => {
        return resources.get(facultyId).map((resource) => {
          return (
            <ResourceCard
              key={resource.resourceId}
              resource={resource}
              facultyId={facultyId}
            />
          );
        });
      })}
    </div>
  );
}
