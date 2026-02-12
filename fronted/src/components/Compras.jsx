import React, { useContext, useEffect, useState } from 'react'
import { TiendaContext } from '../contexts/TiendaContext'

function Compras() {
  const [compras, setCompras] = useState([])
  const { sacarCompras } = useContext(TiendaContext)

  useEffect(() => {
    const data = sacarCompras()
    setCompras(data)
  })

  return (
    <>
    <div>Compras</div>
    <table>
      <tr>
        <th>ID</th>
        <th>USUARIO</th>
        <th>FECHA</th>
      </tr>
      {compras.map((compra) => (
        <tr>
          <td>{compra.id}</td>
          <td>{compra.nombre}</td>
          <td>{compra.fecha}</td>
        </tr>
      ))}
    </table>
    </>
  )
}

export default Compras