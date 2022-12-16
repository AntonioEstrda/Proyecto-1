import Ceil from "../Components/Ceil";

import "../Assets/css/ceils.css"

export default function Ceils() {

	let itemList = [];

	for (let i = 0; i < 25; i++) {
		itemList.push(<Ceil key={i} ceil={{ number: i + 1 }} />)
	}

	return (
		<div className="container">
			<h1>Celdas</h1>
			<div className="celdas">
				{itemList}
			</div>
		</div>
	)
}