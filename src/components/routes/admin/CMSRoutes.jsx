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
import AppLogin from "../../../apps/adminUser/AppLogin";
import { LoginContextProvider } from "../../../context/LoginContext";

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
        <TeacherContextProvider>
          <LocationContextProvider>
            <FacultyContextProvider>
              <DepartmentContextProvider>
                <ProgramContextProvider>
                  <SubjectContextProvider>
                    <AcademicPeriodContextProvider>
                      <GroupContextProvider>
                        <FacultyContextProvider>
                          <DepartmentContextProvider>
                            <AppDepartment />
                          </DepartmentContextProvider>
                        </FacultyContextProvider>
                      </GroupContextProvider>
                    </AcademicPeriodContextProvider>
                  </SubjectContextProvider>
                </ProgramContextProvider>
              </DepartmentContextProvider>
            </FacultyContextProvider>
          </LocationContextProvider>
        </TeacherContextProvider>
      </Route>
      <Route path="/eventos">
        <ResourceTypeContextProvider>
          <TeacherContextProvider>
            <LocationContextProvider>
              <FacultyContextProvider>
                <DepartmentContextProvider>
                  <ProgramContextProvider>
                    <SubjectContextProvider>
                      <AcademicPeriodContextProvider>
                        <GroupContextProvider>
                          <FacultyContextProvider>
                            <ResourceContextProvider>
                              <EventContextProvider>
                                <AppEvent />
                              </EventContextProvider>
                            </ResourceContextProvider>
                          </FacultyContextProvider>
                        </GroupContextProvider>
                      </AcademicPeriodContextProvider>
                    </SubjectContextProvider>
                  </ProgramContextProvider>
                </DepartmentContextProvider>
              </FacultyContextProvider>
            </LocationContextProvider>
          </TeacherContextProvider>
        </ResourceTypeContextProvider>
      </Route>
      <Route path="/facultades">
        <TeacherContextProvider>
          <LocationContextProvider>
            <FacultyContextProvider>
              <DepartmentContextProvider>
                <ProgramContextProvider>
                  <SubjectContextProvider>
                    <AcademicPeriodContextProvider>
                      <GroupContextProvider>
                        <FacultyContextProvider>
                          <AppFaculty />
                        </FacultyContextProvider>
                      </GroupContextProvider>
                    </AcademicPeriodContextProvider>
                  </SubjectContextProvider>
                </ProgramContextProvider>
              </DepartmentContextProvider>
            </FacultyContextProvider>
          </LocationContextProvider>
        </TeacherContextProvider>
      </Route>
      <Route path="/recursosfacultades">
        <ResourceTypeContextProvider>
          <TeacherContextProvider>
            <LocationContextProvider>
              <FacultyContextProvider>
                <DepartmentContextProvider>
                  <ProgramContextProvider>
                    <SubjectContextProvider>
                      <AcademicPeriodContextProvider>
                        <GroupContextProvider>
                          <FacultyContextProvider>
                            <ResourceContextProvider>
                              <FacultyResourceContextProvider>
                                <AppFacultyResource />
                              </FacultyResourceContextProvider>
                            </ResourceContextProvider>
                          </FacultyContextProvider>
                        </GroupContextProvider>
                      </AcademicPeriodContextProvider>
                    </SubjectContextProvider>
                  </ProgramContextProvider>
                </DepartmentContextProvider>
              </FacultyContextProvider>
            </LocationContextProvider>
          </TeacherContextProvider>
        </ResourceTypeContextProvider>
      </Route>
      <Route path="/grupos">
        <LocationContextProvider>
          <FacultyContextProvider>
            <DepartmentContextProvider>
              <ProgramContextProvider>
                <SubjectContextProvider>
                  <AcademicPeriodContextProvider>
                    <GroupContextProvider>
                      <AppGroup />
                    </GroupContextProvider>
                  </AcademicPeriodContextProvider>
                </SubjectContextProvider>
              </ProgramContextProvider>
            </DepartmentContextProvider>
          </FacultyContextProvider>
        </LocationContextProvider>
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
        <TeacherContextProvider>
          <LocationContextProvider>
            <FacultyContextProvider>
              <DepartmentContextProvider>
                <ProgramContextProvider>
                  <SubjectContextProvider>
                    <AcademicPeriodContextProvider>
                      <GroupContextProvider>
                        <FacultyContextProvider>
                          <ProgramContextProvider>
                            <AppProgram />
                          </ProgramContextProvider>
                        </FacultyContextProvider>
                      </GroupContextProvider>
                    </AcademicPeriodContextProvider>
                  </SubjectContextProvider>
                </ProgramContextProvider>
              </DepartmentContextProvider>
            </FacultyContextProvider>
          </LocationContextProvider>
        </TeacherContextProvider>
      </Route>
      <Route path="/recursos">
        <ResourceTypeContextProvider>
          <TeacherContextProvider>
            <LocationContextProvider>
              <FacultyContextProvider>
                <DepartmentContextProvider>
                  <ProgramContextProvider>
                    <SubjectContextProvider>
                      <AcademicPeriodContextProvider>
                        <GroupContextProvider>
                          <FacultyContextProvider>
                            <ResourceContextProvider>
                              <AppResource />
                            </ResourceContextProvider>
                          </FacultyContextProvider>
                        </GroupContextProvider>
                      </AcademicPeriodContextProvider>
                    </SubjectContextProvider>
                  </ProgramContextProvider>
                </DepartmentContextProvider>
              </FacultyContextProvider>
            </LocationContextProvider>
          </TeacherContextProvider>
        </ResourceTypeContextProvider>
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
        <TeacherContextProvider>
          <LocationContextProvider>
            <FacultyContextProvider>
              <DepartmentContextProvider>
                <ProgramContextProvider>
                  <SubjectContextProvider>
                    <AppSubject />
                  </SubjectContextProvider>
                </ProgramContextProvider>
              </DepartmentContextProvider>
            </FacultyContextProvider>
          </LocationContextProvider>
        </TeacherContextProvider>
      </Route>
      <Route path="/profesores">
        <TeacherContextProvider>
          <AppTeacher />
        </TeacherContextProvider>
      </Route>
      <Route path="/gruposprofesores">
        <TeacherContextProvider>
          <LocationContextProvider>
            <FacultyContextProvider>
              <DepartmentContextProvider>
                <ProgramContextProvider>
                  <SubjectContextProvider>
                    <AcademicPeriodContextProvider>
                      <GroupContextProvider>
                        <TeacherGroupContextProvider>
                          <AppTeacherGroup />
                        </TeacherGroupContextProvider>
                      </GroupContextProvider>
                    </AcademicPeriodContextProvider>
                  </SubjectContextProvider>
                </ProgramContextProvider>
              </DepartmentContextProvider>
            </FacultyContextProvider>
          </LocationContextProvider>
        </TeacherContextProvider>
      </Route>
      <Route path="/login">
        <TeacherContextProvider>
          <LoginContextProvider>
            <AppLogin />
          </LoginContextProvider>
        </TeacherContextProvider>
      </Route>
    </>
  );
}

export default CMSRoutes;
