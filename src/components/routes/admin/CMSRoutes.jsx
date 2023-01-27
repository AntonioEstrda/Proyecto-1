import AppAcademicPeriod from "../../../apps/adminUser/AppAcademicPeriod";
import AppAssignmentResource from "../../../apps/adminUser/AppAssignmentResource";
import AppDepartment from "../../../apps/adminUser/AppDepartment";
import AppEvent from "../../../apps/adminUser/AppEvent";
import AppFaculty from "../../../apps/adminUser/AppFaculty";
import AppFacultyResource from "../../../apps/adminUser/AppFacultyResource";
import AppGroup from "../../../apps/adminUser/AppGroup";
import AppHourlyAssignment from "../../../apps/adminUser/AppHourlyAssignment";
import AppLocation from "../../../apps/adminUser/AppLocation";
import AppProgram from "../../../apps/adminUser/AppProgram";
import AppResource from "../../../apps/adminUser/AppResource";
import AppResourceType from "../../../apps/adminUser/AppResourceType";
import AppScheduleCMS from "../../../apps/adminUser/AppScheduleCMS";
import AppSubject from "../../../apps/adminUser/AppSubject";
import AppTeacher from "../../../apps/adminUser/AppTeacher";
import AppTeacherGroup from "../../../apps/adminUser/AppTeacherGroup";

import { AcademicPeriodContextProvider } from "../../../context/AcademicPeriodContext";
import { AssignmentResourceContextProvider } from "../../../context/AssignmentResourceContext";
import { DepartmentContextProvider } from "../../../context/DepartmentContext";
import { EventContextProvider } from "../../../context/EventContext";
import { FacultyContextProvider } from "../../../context/FacultyContext";
import { FacultyResourceContextProvider } from "../../../context/FacultyResourceContext";
import { GroupContextProvider } from "../../../context/GroupContext";
import { HourlyAssignmentContextProvider } from "../../../context/HourlyAssignmentContext";
import { LocationContextProvider } from "../../../context/LocationContext";
import { ProgramContextProvider } from "../../../context/ProgramContext";
import { ResourceContextProvider } from "../../../context/ResourceContext";
import { ResourceTypeContextProvider } from "../../../context/ResourceTypeContext";
import { ScheduleCMSContextProvider } from "../../../context/ScheduleCMSContext";
import { SubjectContextProvider } from "../../../context/SubjectContext";
import { TeacherContextProvider } from "../../../context/TeacherContext";
import { TeacherGroupContextProvider } from "../../../context/TeacherGroupContext";

import { Route } from "react-router-dom";

function CMSRoutes() {
  return (
    <>
      <Route path="/periodosacademicos">
        <AcademicPeriodContextProvider>
          <AppAcademicPeriod />
        </AcademicPeriodContextProvider>
      </Route>
      <Route path="/asignacionrecursos">
        <AssignmentResourceContextProvider>
          <AppAssignmentResource />
        </AssignmentResourceContextProvider>
      </Route>
      <Route path="/departamentos">
        <DepartmentContextProvider>
          <AppDepartment />
        </DepartmentContextProvider>
      </Route>
      <Route path="/eventos">
        <EventContextProvider>
          <AppEvent />
        </EventContextProvider>
      </Route>
      <Route path="/facultades">
        <LocationContextProvider>
          <FacultyContextProvider>
            <AppFaculty />
          </FacultyContextProvider>
        </LocationContextProvider>
      </Route>
      <Route path="/recursosfacultades">
        <FacultyResourceContextProvider>
          <AppFacultyResource />
        </FacultyResourceContextProvider>
      </Route>
      <Route path="/grupos">
        <GroupContextProvider>
          <AppGroup />
        </GroupContextProvider>
      </Route>
      <Route path="/asignacioneshorarias">
        <HourlyAssignmentContextProvider>
          <AppHourlyAssignment />
        </HourlyAssignmentContextProvider>
      </Route>
      <Route path="/ubicaciones">
        <LocationContextProvider>
          <AppLocation />
        </LocationContextProvider>
      </Route>
      <Route path="/programas">
        <ProgramContextProvider>
          <AppProgram />
        </ProgramContextProvider>
      </Route>
      <Route path="/recursos">
        <FacultyContextProvider>
          <ResourceContextProvider>
            <AppResource />
          </ResourceContextProvider>
        </FacultyContextProvider>
      </Route>
      <Route path="/tiposrecursos">
        <ResourceTypeContextProvider>
          <AppResourceType />
        </ResourceTypeContextProvider>
      </Route>
      <Route path="/horarios">
        <ScheduleCMSContextProvider>
          <AppScheduleCMS />
        </ScheduleCMSContextProvider>
      </Route>
      <Route path="/materias">
        <SubjectContextProvider>
          <AppSubject />
        </SubjectContextProvider>
      </Route>
      <Route path="/profesores">
        <TeacherContextProvider>
          <AppTeacher />
        </TeacherContextProvider>
      </Route>
      <Route path="/gruposprofesores">
        <TeacherGroupContextProvider>
          <AppTeacherGroup />
        </TeacherGroupContextProvider>
      </Route>
    </>
  );
}

export default CMSRoutes;
