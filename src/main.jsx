import React from "react";
import ReactDOM from "react-dom/client";
import FacultyApp from "./apps/AppFaculty";
import ResourceApp from "./apps/AppResource";
import "./index.css";
import { FacultyContextProvider } from "./context/FacultyContext";
import { ResourceContextProvider } from "./context/ResourceContext";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";
import { BrowserRouter, Link, Route, Switch } from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
    <BrowserRouter>
      <Navbar />
      <Switch>
        <Route path="/asd">
          <FacultyContextProvider>
            <FacultyApp />
          </FacultyContextProvider>
        </Route>

        <Route path="/facultades">
          <FacultyContextProvider>
            <FacultyApp />
          </FacultyContextProvider>
        </Route>

        <Route path="/recursos">
          <FacultyContextProvider>
            <ResourceContextProvider>
              <ResourceApp />
            </ResourceContextProvider>
          </FacultyContextProvider>
        </Route>
      </Switch>
      <Footer />
    </BrowserRouter>
  </>
);
