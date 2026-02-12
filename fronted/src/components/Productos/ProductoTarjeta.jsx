import React from "react";
import "../../css/estilos.css";
import { Link } from "react-router-dom";

function ProductoTarjeta({ producto }) {
  return (
    <div className="card-compact">
      <div className="card-content">
        <h3 className="p-name">{producto.nombre}</h3>
        <div className="p-details">
          <span className="p-price">{producto.precio} €</span>
          <span className="p-stock">Stock: {producto.stock}</span>
        </div>
        {/* Contenedor de acciones */}
        <div className="p-actions">
          <Link
            to={"/productos/" + producto.id}
            className="btn-edit"
          >
            EDITAR
          </Link>
          <Link
            to={"/productos/borrar/" + producto.id}
            className="btn-delete"
          >
            BORRAR
          </Link>
        </div>
      </div>
    </div>
  );
}

export default ProductoTarjeta;
