import { Link } from "react-router-dom";

function HeaderAdmin() {
  return (
    <div className="bg-paleta2-orange-claro shadow-lg pb-3 rounded-b-3xl">
      <div className="flex  rounded-b-3xl bg-gray-100 dark:bg-paleta2-rojo space-y-5 flex-col items-center py-7">
        <img
          className="h-28 w-28 rounded-full"
          src="https://api.lorem.space/image/face?w=120&h=120&hash=bart89fe"
          alt="User"
        ></img>
      </div>
      <div className="grid px-7 pt-2  items-center justify-around grid-cols-2 gap-4 divide-x divide-solid">
        <div className="col-span-1 flex flex-col items-center  text-center">
          <Link to="/home" className="text-2xl font-bold dark:text-gray-500">
            Tu Perfil
          </Link>
        </div>
        <div className="col-span-1 px-3 flex flex-col items-center ">
          <Link to="/home" className="text-2xl font-bold dark:text-gray-500">
            Configuraciones
          </Link>
        </div>
      </div>
    </div>
  );
}

export default HeaderAdmin;
