import RenderMenu from "./MenuGallery";

function DataCMSMenu() {
  return (
    <div className="m-4 mb-7 bg-slate-900 rounded-2xl py-1">
      <div className="text-2xl font-bold dark:text-paleta2-red-claro m-5">
        Gestionar datos
      </div>
      <div className="grid divide-y divide-x divide-dashed hover:divide-solid rounded-md justify-evenly bg-gray-50 dark:bg-paleta2-claro m-5 grid-cols-3">
        <RenderMenu></RenderMenu>
      </div>
    </div>
  );
}

export default DataCMSMenu;
