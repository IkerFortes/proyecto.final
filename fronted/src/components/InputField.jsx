import React from "react";
import { useState } from "react";
import "../css/estilos.css";

const InputField = ({ name, type, placeholder, icon }) => {
  // State to toggle password visibility
  const [isPasswordShown, setIsPasswordShown] = useState(false);
  return (
    <div className="input-wrapper">
      <input
        name={name}
        type={isPasswordShown ? "text" : type}
        placeholder={placeholder}
        className="input-field"
        required
      />
      <span class="material-icons ">{icon}</span>

      {type === "password" && (
        <span
          onClick={() => setIsPasswordShown((prevState) => !prevState)}
          className="material-symbols-rounded eye-icon"
        >
          {isPasswordShown ? "visible" : "no visible"}
        </span>
      )}
    </div>
  );
};

export default InputField;
