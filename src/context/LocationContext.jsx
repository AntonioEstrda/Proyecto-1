import { createContext, useState, useEffect } from "react";

export const LocationContext = createContext();

export function LocationContextProvider(props) {
    const url = "http://localhost:8080/location/";

      const [editingLocation, setEditingLocation] = useState();
      const [locations, setLocations] = useState([]);
      useEffect(() => {
        fetch(url + "all")
          .then((response) => response.json())
          .then((data) => {
            setLocations(data);
          });
      }, []);

    async function createLocation(location) {
        await fetch(url, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          mode: "cors",
          body: JSON.stringify({
            name: location.name,
            parent: location.parentId,
            city: location.city,
          }),
        })
          .then((response) => response.json())
          .then((data) =>
            setLocations((prevState) => prevState.concat([data]))
          )
          .catch((e) => console.log(e));
    }  


    async function deleteLocation(locationId) {

          await fetch(url + "delete/" + locationId, {
          method: "DELETE",
          headers: {
              "Content-Type": "application/json",
          },
          mode: "cors",
        })
        .then(() => 
            setLocations(locations.filter((location) => location?.id !== locationId)) 
        )
        .catch((e) => console.log(e.message));
    }

      return (
        <LocationContext.Provider
          value={{
            locations,
            editingLocation, 
            deleteLocation,
            createLocation,
            setEditingLocation,
          }}
        >
          {props.children}
        </LocationContext.Provider>
      );

}