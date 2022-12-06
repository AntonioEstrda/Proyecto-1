import React from "react";
import ReactDOM from "react-dom/client";
import FacultyApp from "./apps/AppFaculty";
import ResourceApp from "./apps/AppResource";
import "./index.css";
import { FacultyContextProvider } from "./context/FacultyContext";
import { ResourceContextProvider } from "./context/ResourceContext";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
    <Navbar />
    <div className="grid grid-cols-1">
      <FacultyContextProvider>
        <FacultyApp />
        <ResourceContextProvider>
          <ResourceApp />
        </ResourceContextProvider>
      </FacultyContextProvider>
    </div>
    <Footer />
  </>
);
