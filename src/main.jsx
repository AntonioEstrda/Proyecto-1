import ReactDOM from "react-dom/client";
import AppMenu from "./apps/adminUser/AppMenu";
import AppSchedule from "./apps/adminUser/AppSchedule";
import "./index.css";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";
import { BrowserRouter, Route } from "react-router-dom";
import { ScheduleContextProvider } from "./context/ScheduleContext";
import CMSRoutes from "./components/routes/admin/CMSRoutes";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
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
  </>
);
