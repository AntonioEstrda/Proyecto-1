import { useState, useContext } from "react";
import { FacultyContext } from "../../context/FacultyContext";

function FacultyForm() {
  const [name, setName] = useState("");
  const [location, setLocation] = useState("");

  const { createFaculty } = useContext(FacultyContext);

  const handleSubmit = (e) => {
    e.preventDefault();
    createFaculty({ name, location });
    setName("");
    setLocation("");
  };

  return (
    <div className="max-w-md mx-auto">
      <form onSubmit={handleSubmit} className="bg-blue p-10 mb-4  rounded-md">
        <h1 className="text-2xl font-bold text-bone mb-3">
          Crear una facultad
        </h1>
        <input
          placeholder="Nombre de la facultad"
          onChange={(e) => setName(e.target.value)}
          value={name}
          autoFocus="on"
          className="bg-bone p-3 w-full mb-2 rounded-md"
        />
        <textarea
          name="location"
          value={location}
          placeholder="Localización de la facultad"
          onChange={(e) => setLocation(e.target.value)}
          className="bg-bone p-3 w-full mb-2 rounded-md"
        ></textarea>
        <button className="bg-cyan-600 px-8 py-3 text-blue rounded-md">Guardar</button>
      </form>
    </div>
  );
}

export default FacultyForm;
