import { Link } from "react-router-dom";
export default function Navbar() {
  return (
    <nav className="bg-blue text-bone border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-blue">
      <div className="container flex flex-wrap items-center justify-between mx-auto">
        <Link to="/home" className="flex items-center">
          <img
            src="https://revistas.unicauca.edu.co/public/site/images/logofooter__1__a69a7fd2c99533ac11ab53b42ae1d434.png"
            className="h-7 mr-3 sm:h-16"
            alt="Flowbite Logo"
          />
          <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-bone">
            Gestor Horarios
          </span>
        </Link>
        <div className="hidden w-full md:block md:w-auto" id="navbar-default">
          <ul className="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-blue dark:border-gray-700">
            <li>
              <Link
                to="/home"
                className="block py-2 pl-3 pr-4 text-bone bg-blue-700 rounded md:bg-transparent md:text-bone md:p-0 dark:text-white"
                aria-current="page"
              >
                Inicio
              </Link>
            </li>
            <li>
              <Link
                to="/home"
                className="block py-2 pl-3 pr-4 text-gray-700 rounded hover:bg-gray-100 md:hover:bg-transparent md:border-0 md:hover:text-blue-700 md:p-0 dark:text-gray-400 md:dark:hover:text-white dark:hover:bg-gray-700 dark:hover:text-white md:dark:hover:bg-transparent"
              >
                Cerrar sesión
              </Link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
