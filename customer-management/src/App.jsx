
import logo from "./assets/customer.jpg"

import { Outlet, Link } from 'react-router-dom'


function App() {


  return (
    <>
      <nav className="navbar navbar-expand bg-body-tertiary shadow sm">
        <div className="container-fluid">
          <Link className="navbar-brand" to="/">
            <img src={logo} width={60} />
            User-Customer
          </Link>
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="/login">Login</Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link" to="/signup">Sign Up</Link>
            </li>
          </ul>
        </div>
      </nav>
      <div className="container"><Outlet /></div>


    </>
  )
}

export default App
