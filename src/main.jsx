import React from "react";
import ReactDOM from "react-dom/client";
import FacultyApp from "./apps/AppFaculty";
import ResourceApp from "./apps/AppResource";
import "./index.css";
import { FacultyContextProvider } from "./context/FacultyContext";
import { ResourceContextProvider } from "./context/ResourceContext";

ReactDOM.createRoot(document.getElementById("root")).render(
  <React.StrictMode>
    <FacultyContextProvider>
      <FacultyApp />
    </FacultyContextProvider>

    <ResourceContextProvider>
      <ResourceApp />
    </ResourceContextProvider>
  </React.StrictMode>
);
