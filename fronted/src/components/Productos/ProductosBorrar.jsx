import { useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { con } from "../../conexsions/conxion";

export function BorrarProducto() {
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const eliminar = async () => {
      try {
        await con.delete(`productos/${id}`);
        // alert("Producto eliminado con éxito");
        navigate("/productos"); // Redirige a la lista tras borrar
      } catch (error) {
        console.error("Error al borrar:", error);
      }
    };

    if (id) eliminar();
  }, [id, navigate]);

  return <p>Eliminando producto {id}...</p>;
}
