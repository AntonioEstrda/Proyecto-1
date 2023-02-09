import ReactDOM from "react-dom/client";
import AppMenu from "./apps/adminUser/AppMenu";
import AppSchedule from "./apps/adminUser/AppSchedule";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";
import CMSRoutes from "./components/routes/admin/CMSRoutes";
import { BrowserRouter, Route } from "react-router-dom";
import { ScheduleContextProvider } from "./context/ScheduleContext";
import { AlertContextProvider } from "./context/AlertContext";
import "./index.css";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
    <AlertContextProvider>
      <BrowserRouter>
        <Navbar />

        <Route path="/">
          <Route path="/home">
            <AppMenu />
          </Route>
          <Route path="/ofertaacademica">
            <ScheduleContextProvider>
              <AppSchedule />
            </ScheduleContextProvider>
          </Route>

          <CMSRoutes></CMSRoutes>
        </Route>

        <Footer />
      </BrowserRouter>
    </AlertContextProvider>
  </>
);
