import { Link } from "react-router-dom";

function AppMenu() {
  return (
    <>
      <div className="h-full flex flex-col bg-gray-100 dark:bg-gray-700 shadow-xl">
        <div className="bg-green-300 shadow-lg pb-3 rounded-b-3xl">
          <div className="flex  rounded-b-3xl bg-gray-100 dark:bg-gray-700 space-y-5 flex-col items-center py-7">
            <img
              className="h-28 w-28 rounded-full"
              src="https://api.lorem.space/image/face?w=120&h=120&hash=bart89fe"
              alt="User"
            ></img>
          </div>
          <div className="grid px-7 pt-2  items-center justify-around grid-cols-2 gap-4 divide-x divide-solid">
            <div className="col-span-1 flex flex-col items-center  text-center">
              <Link
                to="/home"
                className="text-2xl font-bold dark:text-gray-500"
              >
                Tu Perfil
              </Link>
            </div>
            <div className="col-span-1 px-3 flex flex-col items-center ">
              <Link
                to="/home"
                className="text-2xl font-bold dark:text-gray-500"
              >
                Configuraciones
              </Link>
            </div>
          </div>
        </div>
        <div className="md:mx-10 lg:mx-24 xl:mx-40">
          <div className="m-4 mt-7 bg-slate-900 rounded-2xl py-1">
            <div className="text-2xl font-bold dark:text-slate-300 m-5">
              Gestionar horario
            </div>
            <div className="grid divide-y divide-x divide-dashed hover:divide-solid  justify-evenly bg-gray-50 dark:bg-gray-300 m-5 grid-cols-2">
              <div className="col-span-1 p-3">
                <div className="flex flex-col items-center ">
                  <Link to="/home">
                    <button className="tr-300 text-center">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-14 w-14 text-gray-500"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        strokeWidth="2"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                        />
                      </svg>
                      <span className="text-lg font-medium">
                        Importar horario
                      </span>
                    </button>
                  </Link>
                </div>
              </div>
              <div className="col-span-1  p-3">
                <div className="flex flex-col items-center ">
                  <Link to="/horarios">
                    <button className="tr-300">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-14 w-14 text-gray-500"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        strokeWidth="2"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                        />
                      </svg>
                      <span className="text-lg font-medium">
                        Horarios cargados
                      </span>
                    </button>
                  </Link>
                </div>
              </div>
            </div>
          </div>

          <div className="m-4 mb-7 bg-slate-900 rounded-2xl py-1">
            <div className="text-2xl font-bold dark:text-slate-300 m-5">
              Gestionar datos
            </div>
            <div className="grid divide-y divide-x divide-dashed hover:divide-solid  justify-evenly bg-gray-50 dark:bg-gray-300 m-5 grid-cols-3">
              <div className="col-span-1 p-3">
                <div className="flex flex-col items-center ">
                  <Link to="/facultades">
                    <button className="tr-300">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-14 w-14 text-gray-500"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        strokeWidth="2"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                        />
                      </svg>
                      <span className="text-lg font-medium">Facultades</span>
                    </button>
                  </Link>
                </div>
              </div>
              <div className="col-span-1  p-3">
                <div className="flex flex-col items-center ">
                  <Link to="/recursos">
                    {" "}
                    <button className="tr-300">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-14 w-14 text-gray-500"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        strokeWidth="2"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                        />
                      </svg>
                      <span className="text-lg font-medium">Recursos</span>
                    </button>
                  </Link>
                </div>
              </div>
              <div className="col-span-1  p-3">
                <div className="flex flex-col items-center ">
                  <Link to="/recursosTypes">
                    {" "}
                    <button className="tr-300">
                      <svg
                        xmlns="http://www.w3.org/2000/svg"
                        className="h-14 w-14 text-gray-500"
                        fill="none"
                        viewBox="0 0 24 24"
                        stroke="currentColor"
                        strokeWidth="2"
                      >
                        <path
                          strokeLinecap="round"
                          strokeLinejoin="round"
                          d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"
                        />
                      </svg>
                      <span className="text-lg font-medium">
                        Tipos de recursos
                      </span>
                    </button>
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default AppMenu;
