import { Link } from "react-router-dom";

function MenuCard({ link, label }) {
  return (
    <div className="col-span-1 p-3">
      <div className="flex flex-col items-center ">
        <Link to={link}>
          <button className="tr-300">
            <span className="text-paleta2-purpura text-lg font-medium">
              {label}
            </span>
          </button>
        </Link>
      </div>
    </div>
  );
}

export default MenuCard;
