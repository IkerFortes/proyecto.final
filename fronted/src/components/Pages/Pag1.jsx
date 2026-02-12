import React from "react";
import { useParams } from "react-router-dom";
import Nav from "../Nav";
import ProductosContainer from "../Productos/ProductosContainer";
import ProductosFormulario from "../Productos/ProductosFormulario";

function Pag1() {
  const { id } = useParams();

  return (
    <>
      <Nav />
      <ProductosContainer />
      {id ? <ProductosFormulario id={id} /> : null}
    </>
  );
}

export default Pag1;
