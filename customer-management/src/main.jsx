import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import "./style.scss";
// import App from './App.jsx';
import { SignUp } from './pages/SignUp/index.jsx';
import { Login } from './pages/Login/index.jsx';
import { createBrowserRouter, RouterProvider } from "react-router-dom";
import { Home } from './pages/Home/index.jsx';
import App from './App.jsx';

const router = createBrowserRouter([

  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Home />
      },
      {
        path: "/signup",
        element: <SignUp />
      },
      {
        path: "/login",
        element: <Login />
      },
    ]
  }

]);

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router} />
  </StrictMode>
);
