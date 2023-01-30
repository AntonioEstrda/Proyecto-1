import { useContext, useState, useEffect } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleParametrizer() {
  const { grupos } = useContext(ScheduleContext);

  const [idProgramSelected, setIdProgramSelected] = useState(-1);
  const [idSubjectSelected, setIdSubjectSelected] = useState();
  const [idResourceSelected, setIdResourceSelected] = useState();

  const programMap = new Map();
  grupos.forEach((grupo) => {
    if (!programMap.has(grupo.subject?.program?.programId)) {
      programMap.set(grupo.subject.program.programId, grupo.subject.program);
    }
  });
  const programList = Array.from(programMap.values());

  const programSelectOptions = (program) => {
    return (
      <option key={program.programId} value={program.programId}>
        {program.name} {program.code ? "- " + program.code : ""}
      </option>
    );
  };

  console.log(idProgramSelected);

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
    if (grupo.subject?.program?.programId == idProgramSelected) {
      subjectMap.set(grupo.subject.subjectID, grupo.subject);
    }
  });

  const subjectSelectOptions = (subject) => {
    console.log(subject);
    return (
      <option key={subject.subjectID} value={subject.subjectID}>
        {subject.name} {subject.code ? "- " + subject.code : ""}
      </option>
    );
  };
  subjectList = Array.from(subjectMap.values());
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
            {subjectList.map(subjectSelectOptions)}
          </select>
        </div>
        <div className="pt-3 mb-2">
          <div className="font-bold text-xl mb-2">Salon:</div>
          <select
            id="countries"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-paleta2-purpura dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option>201</option>
            <option>202</option>
          </select>
        </div>
      </div>
    </div>
  );
}

export default ScheduleParametrizer;
