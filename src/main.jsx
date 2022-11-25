import React from "react";
import ReactDOM from "react-dom/client";
import FacultyApp from "./apps/AppFaculty";
import ResourceApp from "./apps/AppResource";
import "./index.css";
import { FacultyContextProvider } from "./context/FacultyContext";
import { ResourceContextProvider } from "./context/ResourceContext";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <div>
      <div className="grid grid-cols-2">
        <FacultyContextProvider>
          <FacultyApp />
        </FacultyContextProvider>
        <ResourceContextProvider>
          <ResourceApp />
        </ResourceContextProvider>
      </div>
    </div>
  </React.StrictMode>
);
