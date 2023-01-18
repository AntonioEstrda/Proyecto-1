function ScheduleGUI() {
  return (
    <div className="mt-4 w-full bg-slate-200 rounded overflow-hidden shadow-lg grid grid-cols-1 divide-y-8">
      <div className="p-4 text-center">
        <table className="w-full table-fixed">
          <thead>
            <tr>
              <th className="border-b-2 border-r-2 rounded border-red">
                Horas / Días
              </th>
              <th className="border-b-2 rounded border-red">Lunes</th>
              <th className="border-b-2 rounded border-red">Martes</th>
              <th className="border-b-2 rounded border-red">Miércoles</th>
              <th className="border-b-2 rounded border-red">Jueves</th>
              <th className="border-b-2 rounded border-red">Viernes</th>
              <th className="border-b-2 rounded border-red">Sábado</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">7 am</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">8 am</td>
              <td></td>
              <td className="bg-boton3 text-gray-50 text-m rounded border-red">
                Programación Orientada a objetos
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">9 am</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">
                10 am
              </td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td className="bg-boton3 text-indigo-100 text-m rounded border-red">
                Estructuras de datos I
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">
                11 am
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">
                12 am
              </td>
              <td className="bg-boton3 text-indigo-100 text-m rounded border-red">
                Bases de datos I
              </td>
              <td></td>
              <td className="bg-boton3 text-indigo-100 text-m rounded border-red">
                Calidad de Software
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">1 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">2 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">3 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">4 pm</td>
              <td></td>
              <td></td>
              <td></td>
              <td className="bg-boton3 text-gray-200 text-m rounded border-red">
                Circuitos II
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">5 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-red">6 pm</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ScheduleGUI;
