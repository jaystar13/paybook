import { createBrowserRouter } from "react-router-dom";
import Index from ".";
import App from "../app/App";
import Login from "../user/login/Login";
import Signup from "../user/signup/Signup";

const callbackLogin = () => {
  console.log("callbackLogin");
};

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { index: true, element: <Index /> },
      {
        path: "/login",
        element: <Login callbackLogin={callbackLogin} />,
      },
      {
        path: "/signup",
        element: <Signup />,
      },
    ],
  },
]);

export default router;
