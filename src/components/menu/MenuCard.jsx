import { Link } from "react-router-dom";

function MenuCard({ link, label }) {
  return (
    <div className="col-span-1 p-3">
      <div className="flex flex-col items-center ">
        <Link to={link}>
          <button className="tr-300">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              className="h-14 w-14 text-paleta2-red-claro"
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
            <span className="text-paleta2-red-claro text-lg font-medium">
              {label}
            </span>
          </button>
        </Link>
      </div>
    </div>
  );
}

export default MenuCard;
