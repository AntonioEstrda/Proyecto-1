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

  async function create(location) {
    await fetch(url + "add", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify({
        name: location.name,
        parent: location.parent,
        city: location.city,
      }),
    })
      .then((response) => response.json())
      .then((data) => setLocations((prevState) => prevState.concat([data])))
      .catch((e) => console.log(e));
  }

  async function deleteById(locationId) {
    await fetch(url + "delete/" + locationId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
    })
      .then(() => {
        setLocations(
          locations.filter((location) => location.locationId !== locationId)
        );
        //console.log(locations);
      })
      .catch((e) => console.log(e.message));
  }

  async function update(prevLocation) {
    (await fetch(url + "update", {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      mode: "cors",
      body: JSON.stringify(prevLocation),
    })) /
      then((response) => response.json())
        .then(() => {
          locations[locations.indexOf(editingLocation)] = prevLocation;
          setLocations(locations);
        })
        .then(() => setEditingLocation(null))
        .catch((e) => console.log(e));
  }

  return (
    <LocationContext.Provider
      value={{
        locations,
        editingLocation,
        deleteById,
        create,
        update,
        setEditingLocation,
      }}
    >
      {props.children}
    </LocationContext.Provider>
  );
}
