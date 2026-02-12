import { Route, Routes } from 'react-router-dom'
import { BorrarProducto } from './components/Productos/ProductosBorrar'
import Pag1 from './components/Pages/Pag1'
import Pag2 from './components/Pages/Pag2'
import Pag3 from './components/Pages/Pag3'
import Pag4 from './components/Pages/Pag4'

function App() {

  return (
    <>
      <Routes>
        <Route path='productos/:id?' element={<Pag1 />} />
        <Route path='productos/crear' element={<Pag2 />} />
        <Route path='login' element={<Pag3 />} />
        <Route path='compras' element={<Pag4 />} />
        <Route path='productos/borrar/id'  element={<BorrarProducto />} />
        <Route path='*' element={<p>ERROR</p>} />
      </Routes>
    </>
  )
}

export default App
