import ReactDOM from "react-dom/client";
import AppMenu from "./apps/adminUser/AppMenu";
import AppSchedule from "./apps/adminUser/AppSchedule";
import "./index.css";
import Navbar from "./templates/Navbar";
import Footer from "./templates/Footer";
import { BrowserRouter, Route } from "react-router-dom";
import { ScheduleContextProvider } from "./context/ScheduleContext";
import CMSRoutes from "./components/routes/admin/CMSRoutes";
import Header from "./templates/Header";
import AppLogin from "./apps/adminUser/AppLogin";
import { LoginContext, LoginContextProvider } from "./context/LoginContext";

ReactDOM.createRoot(document.getElementById("root")).render(
  <>
    <BrowserRouter>
      <LoginContextProvider>
        <Header />
      </LoginContextProvider>

      <Route path="/">
        <Route path="/login">
          <LoginContextProvider>
            <AppLogin />
          </LoginContextProvider>
        </Route>
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
