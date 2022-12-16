import { useState, useContext } from "react";
import { ScheduleContext } from "../../context/ScheduleContext";

function ScheduleCMS() {
  const { setPrograma, setSemestre, setMateria, setSalones } =
    useContext(ScheduleContext);
  return (
    <div className="bg-slate-200 w-full rounded overflow-visible shadow-lg">
      <div className="flex ... text-center justify-around">
        <div className="h-auto w-25 my-7 ...">
          <div className="font-bold text-xl mb-2">Programa:</div>
          <select
            id="programaSeleccionado"
            className="bg-slate-200 p-3 w-full mb-2"
            onChange={(e) => setPrograma(e.target.value)}
            value="computacional"
          >
            <option value="ingSistemas">Ingeniería de Sistemas</option>
            <option value="ingElectrónica">Ingeniería Electrónica</option>
          </select>
        </div>
        <div className="h-auto w-25 my-7 ...">
          <div className="font-bold text-xl mb-2">Semestre:</div>
          <select
            id="semestreSeleccionado"
            className="bg-slate-200 p-3 w-full mb-2"
            onChange={(e) => setSemestre(e.target.value)}
            value="computacional"
          >
            <option value="1">1</option>
            <option value="2">2</option>
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
            <option value="7">7</option>
            <option value="8">8</option>
            <option value="9">9</option>
            <option value="10">10</option>
          </select>
        </div>
        <div className="h-auto w-25 my-7 ...">
          <div className="font-bold text-xl mb-2">Materia:</div>
          <select
            id="materiaSeleccionada"
            className="bg-slate-200 p-3 w-full mb-2"
            onChange={(e) => setMateria(e.target.value)}
            value="computacional"
          >
            <option value="informatica1">Informática 1</option>
            <option value="calculo1">Cálculo 1</option>
          </select>
        </div>
        <div className="h-auto w-25 my-7 ...">
          <div className="font-bold text-xl mb-2">Salon:</div>
          <select
            id="salonSeleccionado"
            className="bg-slate-200 p-3 w-full mb-2"
            onChange={(e) => setSalon(e.target.value)}
            value="computacional"
          >
            <option value="Ing. Sistemas">201</option>
            <option value="Ing. Electrónica">202</option>
          </select>
        </div>
      </div>
    </div>
  );
}

export default ScheduleCMS;
