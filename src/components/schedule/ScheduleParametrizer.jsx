import { useContext, useState, useEffect } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleParametrizer() {
  const {
    grupos,
    idGroupSelected,
    setIdGroupSelected,
    idResourceSelected,
    setIdResourceSelected,
    setRecurso,
    setAlert,
  } = useContext(ScheduleContext);

  const tipoSalonesValidos = ["SALA", "AUDITORIO", "SALON"];

  const [resources, setResources] = useState();
  const [idProgramSelected, setIdProgramSelected] = useState(-1);
  const [idSubjectSelected, setIdSubjectSelected] = useState(-1);

  useEffect(() => {
    setIdSubjectSelected(-1);
    setIdGroupSelected(-1);
    setIdResourceSelected(-1);
  }, [idProgramSelected]);
  useEffect(() => {
    setIdGroupSelected(-1);
    setIdResourceSelected(-1);
  }, [idSubjectSelected]);
  useEffect(() => {
    setIdResourceSelected(-1);
  }, [idGroupSelected]);

  useEffect(() => {
    if (idProgramSelected === -1) return;
    fetch(
      "http://localhost:8080/Resource/all?" +
        new URLSearchParams({
          facultyId: programMap?.get(+idProgramSelected)?.department?.facultad
            ?.facultyId,
        })
    )
      .then((response) => {
        if (response.ok) return response.json();
        return Promise.reject(response);
      })
      .then((data) => {
        data.length === 0 ? setAlert("NO_RES") : setAlert();
        setResources(data);
      })
      .catch((e) => {
        if (e.status === 404) setAlert("empty");
        if (e.status === 400) setAlert(e.headers.get("errors"));
      });
  }, [idProgramSelected]);

  const programMap = new Map();
  grupos.forEach((grupo) => {
    if (!programMap.has(grupo?.subject?.program?.programId)) {
      programMap.set(
        grupo?.subject?.program?.programId,
        grupo?.subject?.program
      );
    }
  });
  const programList = Array.from(programMap.values());

  const programSelectOptions = (program) => {
    return (
      <option key={program?.programId} value={program?.programId}>
        {program?.name} {program?.code ? "- " + program?.code : ""}
      </option>
    );
  };

  const subjectMap = new Map();
  let subjectList = Array.from(subjectMap.values());
  if (subjectList.length === 0 && idProgramSelected === -1) {
    return (
      <div className="bg-paleta2-fondo1 rounded shadow-lg">
        <div className="text-center justify-around p-3">
          <div className=" ">
            <div className="font-bold text-xl mb-2">Programa:</div>
            <select
              id="programSelected"
              name="programId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdProgramSelected(e.target.value);
              }}
            >
              <option key={-1} value={-1}>
                Seleccione un programa
              </option>
              {programList.map(programSelectOptions)}
            </select>
          </div>
        </div>
      </div>
    );
  }

  grupos.forEach((grupo) => {
    if (grupo?.subject?.program?.programId == idProgramSelected) {
      subjectMap.set(grupo?.subject?.subjectID, grupo?.subject);
    }
  });
  const subjectSelectOptions = (subject) => {
    return (
      <option key={subject?.subjectID} value={subject?.subjectID}>
        {subject?.name} {subject?.code ? "- " + subject?.code : ""}
      </option>
    );
  };
  subjectList = Array.from(subjectMap.values());
  if (idSubjectSelected === -1) {
    return (
      <div className="bg-paleta2-fondo1 rounded shadow-lg">
        <div className="text-center justify-around p-3">
          <div className=" ">
            <div className="font-bold text-xl mb-2">Programa:</div>
            <select
              defaultValue={idProgramSelected}
              value={idProgramSelected}
              id="programSelected"
              name="programId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdProgramSelected(e.target.value);
              }}
            >
              {programList.map(programSelectOptions)}
            </select>
          </div>
          <div className="pt-3 ">
            <div className="font-bold text-xl mb-2">Semestre:</div>
            <input
              type="text"
              id="disabled-input-2"
              aria-label="disabled input 2"
              className="bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              value="1"
              disabled
              readOnly
            ></input>
          </div>
          <div className="pt-3">
            <div className="font-bold text-xl mb-2">Materia:</div>
            <select
              id="subjectSelected"
              name="subjectId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdSubjectSelected(e.target.value);
              }}
            >
              <option key={-1} value={-1}>
                Seleccione una materia
              </option>
              {subjectList.map(subjectSelectOptions)}
            </select>
          </div>
        </div>
      </div>
    );
  }

  const groupSelectOptions = (group) => {
    if (group?.subject?.subjectID == idSubjectSelected)
      return (
        <option key={group.groupId} value={group.groupId}>
          {group.name}
        </option>
      );
  };
  if (idGroupSelected === -1) {
    return (
      <div className="bg-paleta2-fondo1 rounded shadow-lg">
        <div className="text-center justify-around p-3">
          <div className=" ">
            <div className="font-bold text-xl mb-2">Programa:</div>
            <select
              defaultValue={idProgramSelected}
              value={idProgramSelected}
              id="programSelected"
              name="programId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdProgramSelected(e.target.value);
              }}
            >
              {programList.map(programSelectOptions)}
            </select>
          </div>
          <div className="pt-3 ">
            <div className="font-bold text-xl mb-2">Semestre:</div>
            <input
              type="text"
              id="disabled-input-2"
              aria-label="disabled input 2"
              className="bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              value="1"
              disabled
              readOnly
            ></input>
          </div>
          <div className="pt-3">
            <div className="font-bold text-xl mb-2">Materia:</div>
            <select
              defaultValue={idSubjectSelected}
              value={idSubjectSelected}
              id="subjectSelected"
              name="subjectId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdSubjectSelected(e.target.value);
              }}
            >
              {subjectList.map(subjectSelectOptions)}
            </select>
          </div>
          <div className="pt-3">
            <div className="font-bold text-xl mb-2">Grupos:</div>
            <select
              id="groupSelected"
              name="groupId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdGroupSelected(e.target.value);
              }}
            >
              <option key={-1} value={-1}>
                Seleccione un grupo
              </option>
              {grupos.map(groupSelectOptions)}
            </select>
          </div>
        </div>
      </div>
    );
  }

  const resourceSelectOptions = (resource) => {
    if (tipoSalonesValidos.includes(resource?.resourceType?.name))
      return (
        <option key={resource.resourceId} value={resource.resourceId}>
          {resource.name} {resource.number ? " - " + resource.number : ""}
        </option>
      );
  };
  if (idResourceSelected === -1)
    return (
      <div className="bg-paleta2-fondo1 rounded shadow-lg">
        <div className="text-center justify-around p-3">
          <div className=" ">
            <div className="font-bold text-xl mb-2">Programa:</div>
            <select
              defaultValue={idProgramSelected}
              value={idProgramSelected}
              id="programSelected"
              name="programId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdProgramSelected(e.target.value);
              }}
            >
              {programList.map(programSelectOptions)}
            </select>
          </div>
          <div className="pt-3 ">
            <div className="font-bold text-xl mb-2">Semestre:</div>
            <input
              type="text"
              id="disabled-input-2"
              aria-label="disabled input 2"
              className="bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
              value="1"
              disabled
              readOnly
            ></input>
          </div>
          <div className="pt-3">
            <div className="font-bold text-xl mb-2">Materia:</div>
            <select
              defaultValue={idSubjectSelected}
              value={idSubjectSelected}
              id="subjectSelected"
              name="subjectId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdSubjectSelected(e.target.value);
              }}
            >
              {subjectList.map(subjectSelectOptions)}
            </select>
          </div>
          <div className="pt-3">
            <div className="font-bold text-xl mb-2">Grupos:</div>
            <select
              defaultValue={idGroupSelected}
              value={idGroupSelected}
              id="groupSelected"
              name="groupId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setIdGroupSelected(e.target.value);
              }}
            >
              {grupos.map(groupSelectOptions)}
            </select>
          </div>
          <div className="pt-3 mb-2">
            <div className="font-bold text-xl mb-2">
              Salones
              {" " +
                programMap?.get(+idProgramSelected)?.department?.facultad
                  ?.facultyName}
              :
            </div>
            <select
              id="resourceSelected"
              name="resourceId"
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
              onChange={(e) => {
                setRecurso(
                  resources.find(
                    (resources) => resources.resourceId == e.target.value
                  )
                );
                setIdResourceSelected(e.target.value);
              }}
            >
              <option key={-1} value={-1}>
                Seleccione un salón
              </option>
              {resources.map(resourceSelectOptions)}
            </select>
          </div>
        </div>
      </div>
    );

  return (
    <div className="bg-paleta2-fondo1 rounded shadow-lg">
      <div className="text-center justify-around p-3">
        <div className=" ">
          <div className="font-bold text-xl mb-2">Programa:</div>
          <select
            defaultValue={idProgramSelected}
            value={idProgramSelected}
            id="programSelected"
            name="programId"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            onChange={(e) => {
              setIdProgramSelected(e.target.value);
            }}
          >
            {programList.map(programSelectOptions)}
          </select>
        </div>
        <div className="pt-3 ">
          <div className="font-bold text-xl mb-2">Semestre:</div>
          <input
            type="text"
            id="disabled-input-2"
            aria-label="disabled input 2"
            className="bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
            value="1"
            disabled
            readOnly
          ></input>
        </div>
        <div className="pt-3">
          <div className="font-bold text-xl mb-2">Materia:</div>
          <select
            defaultValue={idSubjectSelected}
            value={idSubjectSelected}
            id="subjectSelected"
            name="subjectId"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            onChange={(e) => {
              setIdSubjectSelected(e.target.value);
            }}
          >
            {subjectList.map(subjectSelectOptions)}
          </select>
        </div>
        <div className="pt-3">
          <div className="font-bold text-xl mb-2">Grupos:</div>
          <select
            defaultValue={idGroupSelected}
            value={idGroupSelected}
            id="groupSelected"
            name="groupId"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            onChange={(e) => {
              setIdGroupSelected(e.target.value);
            }}
          >
            {grupos.map(groupSelectOptions)}
          </select>
        </div>
        <div className="pt-3 mb-2">
          <div className="font-bold text-xl mb-2">
            Salones
            {" " +
              programMap?.get(+idProgramSelected)?.department?.facultad
                ?.facultyName}{" "}
            :
          </div>
          <select
            defaultValue={idResourceSelected}
            value={idResourceSelected}
            id="resourceSelected"
            name="resourceId"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            onChange={(e) => {
              setRecurso(
                resources.find(
                  (resources) => resources.resourceId == e.target.value
                )
              );
              setIdResourceSelected(e.target.value);
            }}
          >
            {resources.map(resourceSelectOptions)}
          </select>
        </div>
      </div>
    </div>
  );
}

export default ScheduleParametrizer;
