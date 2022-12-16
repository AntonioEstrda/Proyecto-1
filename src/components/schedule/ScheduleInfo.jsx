function ScheduleInfo() {
  return (
    <>
      <div className="bg-slate-200 w-full mt-10 px-6 rounded overflow-visible shadow-lg">
        <div className="font-bold text-xl pt-5 text-center">
          Información horario
        </div>
        <div>
          <div className="flex justify-between m-1">
            <span className="text-base font-medium ">Progreso</span>
            <span className="text-sm font-medium ">45%</span>
          </div>
          <div className="w-full bg-gray-200 rounded-full h-2.5 dark:bg-gray-700">
            <div className="bg-yellow-600 h-2.5 rounded-full w-1/2"></div>
          </div>
        </div>
        <div className="pb-5 text-center items-center ">
          <div className="font-bold text-large pt-5 text-center">Errores</div>
          <table className="table-auto w-full border-collapse border ...">
            <thead>
              <tr>
                <th className="border-b-4 border-t-4 border-r-2 border-slate-600 w-auto ...">
                  Programa
                </th>
                <th className="border-b-4 border-t-4 border-r-2 border-slate-600 w-auto ...">
                  Materia
                </th>
                <th className="border-b-4 border-t-4 border-r-2 border-slate-600 w-40 ...">
                  Día
                </th>
                <th className="border-b-4 border-t-4 border-r-2 border-slate-600 w-32 ...">
                  Hora
                </th>
                <th className="border-b-4 border-t-4 border-slate-600 w-auto ...">
                  razón
                </th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td className="border-b-2 border-slate-700 ...">
                  Ingeniería de Sistemas
                </td>
                <td className="border-b-2 border-slate-700 ...">
                  Programación orientada a objetos
                </td>
                <td className="border-b-2 border-slate-700 ...">Martes</td>
                <td className="border-b-2 border-slate-700 ...">8 am</td>
                <td className="border-b-2 border-slate-700 ...">
                  Salón asignado no cumple con los requisitos de la materia
                </td>
              </tr>
              <tr>
                <td className="border-b-2 border-slate-700 ...">
                  Ingeniería Electrónica
                </td>
                <td className="border-b-2 border-slate-700 ...">
                  Circuitos II
                </td>
                <td className="border-b-2 border-slate-700 ...">Jueves</td>
                <td className="border-b-2 border-slate-700 ...">4 pm</td>
                <td className="border-b-2 border-slate-700 ...">
                  Cruce con materia Inglés I
                </td>
              </tr>
              <tr>
                <td className="border-b-2 border-slate-700 ...">
                  Ingeniería Electrónica
                </td>
                <td className="border-b-2 border-slate-700 ...">Inglés I</td>
                <td className="border-b-2 border-slate-700 ...">Jueves</td>
                <td className="border-b-2 border-slate-700 ...">4 pm</td>
                <td className="border-b-2 border-slate-700 ...">
                  Cruce con materia Circuitos II
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </>
  );
}

export default ScheduleInfo;
