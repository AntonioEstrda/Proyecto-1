import { mosaicoMenu } from "../../../values/util_data";
import MenuCard from "../MenuCard";

function RenderMenu() {
  return (
    <>
      {mosaicoMenu.map((value, index) => (
        <MenuCard key={"menuCard_" + index} link={value[1]} label={value[0]} />
      ))}
    </>
  );
}

export default RenderMenu;
