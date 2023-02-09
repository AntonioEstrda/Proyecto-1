import { useContext } from "react";
import { ResourceContext } from "../../context/ResourceContext";
import { globalErrors } from "../../values/errors/globalErrors";

function ResourceAlert() {
  const { alert, closeAlert } = useContext(ResourceContext);

  function CloseButton() {
    return (
      <div className="text-right">
        <button onClick={closeAlert}>Cerrar</button>
      </div>
    );
  }
  const showAlert_error = (key) => {
    return <p>{globalErrors.get(key)}</p>;
  };

  const alertContainer = (errors) => {
    return (
      <div className="mb-4" role="alert">
        <div className="bg-rose-500 text-white font-bold rounded-t px-4 py-2 columns-2">
          <div>Ocurrió un error</div>
          <CloseButton> </CloseButton>
        </div>
        <div className="border border-t-0 border-rose-400 rounded-b bg-rose-100 px-4 py-3 text-rose-700">
          {errors.map((errorCode) => {
            return showAlert_error(errorCode);
          })}
        </div>
      </div>
    );
  };

  if (alert) {
    const alertList = alert
      .substring(1, alert.length - 1)
      .replace(" ", "")
      .split(",");
    return <>{alertContainer(alertList)}</>;
  }
}

export default ResourceAlert;
