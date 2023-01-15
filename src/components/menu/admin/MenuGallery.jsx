import adminMenu from "../../../values/AdminMenu";
import MenuCard from "../MenuCard";

function RenderMenu() {
  return (
    <>
      {adminMenu.map((value, index) => (
        <MenuCard key={"menuCard_" + index} link={value[1]} label={value[0]} />
      ))}
    </>
  );
}

export default RenderMenu;
