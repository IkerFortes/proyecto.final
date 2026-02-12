import React, { useContext, useEffect } from "react";
import ProductoTarjeta from "./ProductoTarjeta";
import { TiendaContext } from "../../contexts/TiendaContext";

function ProductosContainer() {
  const { productos, sacarProductos } = useContext(TiendaContext);

  useEffect(() => {
    sacarProductos();
  }, []);

  return (
    <>
      <div className="container-wrapper">
        {/* Contenedor Grid para las tarjetas */}
        <div className="product-grid">
          {productos.map((producto) => (
            <ProductoTarjeta
              key={producto.id}
              producto={producto}
            />
          ))}
        </div>
      </div>
    </>
  );
}

export default ProductosContainer;
