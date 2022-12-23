function ScheduleGUI() {
  return (
    <div className="mt-4 w-full bg-slate-200 rounded overflow-hidden shadow-lg grid grid-cols-1 divide-y-8">
      <div className="p-4 text-center">
        <table className="w-full table-fixed">
          <thead>
            <tr>
              <th className="border-b-2 border-r-2 rounded border-amber-600">
                Horas / Días
              </th>
              <th className="border-b-2 rounded border-amber-600">Lunes</th>
              <th className="border-b-2 rounded border-amber-600">Martes</th>
              <th className="border-b-2 rounded border-amber-600">Miércoles</th>
              <th className="border-b-2 rounded border-amber-600">Jueves</th>
              <th className="border-b-2 rounded border-amber-600">Viernes</th>
              <th className="border-b-2 rounded border-amber-600">Sábado</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">7 am</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">8 am</td>
              <td></td>
              <td className="bg-green-600 text-gray-50 text-m rounded border-amber-600">
                Programación Orientada a objetos
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">9 am</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">
                10 am
              </td>
              <td></td>
              <td></td>
              <td></td>
              <td></td>
              <td className="bg-green-600 text-indigo-100 text-m rounded border-amber-600">
                Estructuras de datos I
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">
                11 am
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">
                12 am
              </td>
              <td className="bg-green-600 text-indigo-100 text-m rounded border-amber-600">
                Bases de datos I
              </td>
              <td></td>
              <td className="bg-green-600 text-indigo-100 text-m rounded border-amber-600">
                Calidad de Software
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">1 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">2 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">3 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">4 pm</td>
              <td></td>
              <td></td>
              <td></td>
              <td className="bg-green-600 text-gray-200 text-m rounded border-amber-600">
                Circuitos II
              </td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">5 pm</td>
            </tr>
            <tr>
              <td className="h-10 border-r-2 rounded border-amber-600">6 pm</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default ScheduleGUI;
