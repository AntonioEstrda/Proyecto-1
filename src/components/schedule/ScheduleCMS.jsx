import { useState, useContext } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleCMS() {
  const { setPrograma, setSemestre, setMateria, setSalones } =
    useContext(ScheduleContext);
  return (
    <div className="bg-slate-200 rounded shadow-lg">
      <div className="text-center justify-around p-3">
        <div className=" ">
          <div className="font-bold text-xl mb-2">Programa:</div>
          <select
            id="countries"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option>Ingeniería de Sistemas</option>
            <option>Ingeniería Electrónica</option>
          </select>
        </div>
        <div className="pt-3 ">
          <div className="font-bold text-xl mb-2">Semestre:</div>
          <input
            type="text"
            id="disabled-input-2"
            aria-label="disabled input 2"
            className="bg-gray-100 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 cursor-not-allowed dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-gray-400 dark:focus:ring-blue-500 dark:focus:border-blue-500"
            value="1"
            disabled
            readOnly
          ></input>
        </div>
        <div className="pt-3">
          <div className="font-bold text-xl mb-2">Materia:</div>
          <select
            id="countries"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option>Informática 1</option>
            <option>Cálculo 1</option>
          </select>
        </div>
        <div className="pt-3 mb-2">
          <div className="font-bold text-xl mb-2">Salon:</div>
          <select
            id="countries"
            className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
          >
            <option>201</option>
            <option>202</option>
          </select>
        </div>
      </div>
    </div>
  );
}

export default ScheduleCMS;
