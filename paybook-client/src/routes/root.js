import { createBrowserRouter } from "react-router-dom";
import Index from ".";
import App from "../app/App";
import Login from "../user/login/Login";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { index: true, element: <Index /> },
      {
        path: "/login",
        element: <Login />,
      },
    ],
  },
]);

export default router;
