import ScheduleMenu from "../../components/menu/admin/ScheduleMenu";
import DataCMSMenu from "../../components/menu/admin/DataCMSMenu";
import HeaderAdmin from "../../components/menu/admin/HeaderAdmin";

function AppMenu() {
  return (
    <>
      <div className="h-full flex flex-col bg-gray-100 dark:bg-fondo2 shadow-xl">
        <HeaderAdmin></HeaderAdmin>
        <div className="md:mx-10 lg:mx-24 xl:mx-40">
          <ScheduleMenu></ScheduleMenu>
          <DataCMSMenu></DataCMSMenu>
        </div>
      </div>
    </>
  );
}

export default AppMenu;
