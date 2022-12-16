import ReactDOM from "react-dom/client";
import AppFaculty from "./apps/AppFaculty";
import AppResource from "./apps/AppResource";
import AppSchedule from "./apps/AppSchedule";
import "./index.css";
import { ScheduleContextProvider } from "./context/ScheduleContext";
import { FacultyContextProvider } from "./context/FacultyContext";
import { ResourceContextProvider } from "./context/ResourceContext";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";
import { BrowserRouter, Route } from "react-router-dom";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
    <BrowserRouter>
      <Navbar />

      <Route path="/">
        <Route path="/horarios">
          <ScheduleContextProvider>
            <AppSchedule />
          </ScheduleContextProvider>
        </Route>
        <Route path="/facultades">
          <FacultyContextProvider>
            <AppFaculty />
          </FacultyContextProvider>
        </Route>
        <Route path="/recursos">
          <FacultyContextProvider>
            <ResourceContextProvider>
              <AppResource />
            </ResourceContextProvider>
          </FacultyContextProvider>
        </Route>
      </Route>

      <Footer />
    </BrowserRouter>
  </>
);
