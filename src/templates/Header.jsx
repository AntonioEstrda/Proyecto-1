import React, { useContext, useRef } from "react";
import { useState } from "react";
import { Link } from "react-router-dom";
import { LoginContext } from "../context/LoginContext";

export default function Header() {
    const [isLogged, setIsLogged] = useState(false);

    const {
        estado,
        loggeo,

    } = useContext(LoginContext);
    const una_variable = useRef(false);



    if (!isLogged) {
        return (
            <header className="bg-blue text-paleta2-azul-claro border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-blue">

                <div className="container flex flex-wrap items-center justify-between mx-auto">
                    <Link to="/" className="flex items-center">
                        <img
                            src="https://revistas.unicauca.edu.co/public/site/images/logofooter__1__a69a7fd2c99533ac11ab53b42ae1d434.png"
                            className="h-7 mr-3 sm:h-16"
                            alt="Flowbite Logo"
                        />
                        <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-paleta2-fondo1">
                            Gestor Horarios
                        </span>
                    </Link>

                    <div className="hidden w-full md:block md:w-auto" id="navbar-default">
                        <ul className="flex flex-col p-4 mt-4 border border-gray-100 rounded-lg bg-gray-50 md:flex-row md:space-x-8 md:mt-0 md:text-sm md:font-medium md:border-0 md:bg-white dark:bg-gray-800 md:dark:bg-blue dark:border-gray-700">
                            <li>
                                <Link
                                    to="/login"
                                    className="block py-2 pl-3 pr-4 text-paleta2-claro bg-blue-700 rounded md:bg-transparent md:text-paleta2-fondo1 md:p-0 dark:text-white"
                                    aria-current="page"

                                >
                                    Salir

                                </Link>
                            </li>
                        </ul>
                    </div>
                </div>
            </header>
        );
    } else {
        return (

            <header className="bg-blue text-paleta2-azul-claro border-gray-200 px-2 sm:px-4 py-2.5 rounded dark:bg-blue">

                <div className="container flex flex-wrap items-center justify-between mx-auto">
                    <Link to="/" className="flex items-center">
                        <img
                            src="https://revistas.unicauca.edu.co/public/site/images/logofooter__1__a69a7fd2c99533ac11ab53b42ae1d434.png"
                            className="h-7 mr-3 sm:h-16"
                            alt="Flowbite Logo"
                        />
                        <span className="self-center text-xl font-semibold whitespace-nowrap dark:text-paleta2-fondo1">
                            Gestor Horarios
                        </span>
                    </Link>

                </div>
            </header>
        );
    }
}